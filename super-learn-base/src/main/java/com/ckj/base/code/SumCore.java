package com.ckj.base.code;

import java.util.HashMap;

public class SumCore {


    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i <= nums.length - 1; i++) {
            System.out.println("-----i---" + i);
            for (int j = i + 1; j <= nums.length - 1; j++) {
                System.out.println("-----j---" + j);
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoSumHash(int[] nums, int target) {
        // value  key
        HashMap<Integer, Integer> keys = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            boolean b = keys.containsKey(i1);
            if (b) {
                return new int[]{keys.get(i1), i};
            }
            keys.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int[] nums2 = new int[]{3, 2, 4};
        int[] ints = twoSumHash(nums2, 6);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }

    }
}


