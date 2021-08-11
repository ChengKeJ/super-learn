package com.ckj.base.algorithm;

import java.util.HashSet;

/**
 * @author c.kj
 * @Description 无重复字符的最长子串
 * @Date 2021/7/3
 * @Time 9:07 PM
 **/
public class lengthOfLongestSubstringCore {


    public int lengthOfLongestSubstring(String s) {
        int rk  = -1;
        int ans =  0;
        HashSet<Character> set= new HashSet<>();
        for(int i=0;i<s.length();i++){
            if(i!=0){
                set.remove(s.charAt(i-1));
            }
            while(rk+1<s.length() && ! set.contains(s.charAt(rk+1))){
                set.add(s.charAt(rk+1));
                rk++;
            }
            ans=Math.max(ans,rk-i+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        lengthOfLongestSubstringCore core = new lengthOfLongestSubstringCore();
        int i = core.lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
