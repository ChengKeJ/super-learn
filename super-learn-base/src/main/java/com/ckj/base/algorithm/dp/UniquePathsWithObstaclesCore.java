package com.ckj.base.algorithm.dp;

/**
 * @author c.kj
 * @Description
 * @Date 2021/8/26
 * @Time 3:58 PM
 **/
public class UniquePathsWithObstaclesCore {

    /**
     * m ==obstacleGrid.length
     * n ==obstacleGrid[i].length
     * 1 <= m, n <= 100
     * obstacleGrid[i][j] 为 0 或 1
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;

        int n = obstacleGrid[0].length;

        int[][] ints = new int[m][n];

        ints[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] != 1) {
                    if (i > 0 && j > 0) {
                        ints[i][j] = ints[i - 1][j] + ints[i][j - 1];
                    }else
                    if (i > 0 ) {
                        ints[i][j] = ints[i - 1][j];
                    }else
                    if (j > 0) {
                        ints[i][j] = ints[i][j - 1];
                    }
                }
            }
        }
        return ints[m - 1][n - 1];
    }


    public static void main(String[] args) {
        int[][] obstacleGrid = new int[2][1];
        obstacleGrid[1][0] = 1;
        int i = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(i);

    }
}
