package com.ckj.base.algorithm.dp;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/26
 * @Time 1:43 PM
 **/
public class UniquePathsCore {


    public static int call(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        } else {
            return call(m - 1, n) + call(m, n - 1);
        }
    }

    public static int callDP(int m, int n) {

        int[][] ints = new int[m][n];
        ints[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j> 0) {
                    ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
                }
                if (i == 0 || j == 0) {
                    ints[i][j] = 1;
                }
            }
        }
        return ints[m - 1][n - 1];
    }


    /**
     * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
     * <p>
     * 问总共有多少条不同的路径？
     *
     * @param args
     */
    public static void main(String[] args) {
        int call = callDP(7, 3);
        System.out.println(call);
    }
}
