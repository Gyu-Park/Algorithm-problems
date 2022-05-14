/**
 * You are given an m x n integer array grid. 
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]). 
 * The robot tries to move to the bottom-right corner (i.e., grid[m-1][n-1]). 
 * The robot can only move either down or right at any point in time.
 * 
 * An obstacle and space are marked as 1 or 0 respectively in grid. 
 * A path that the robot takes cannot include any square that is an obstacle.
 * 
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * 
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 */
package Problems;

public class UniquePathsII {
    // dp solution
    // time complexity O(mn)
    // space complexity O(mn)
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m * n == 1)
            return obstacleGrid[0][0] == 0 ? 1 : 0;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    continue;
                if (i == 0 && j == 0)
                    dp[i][j] = 1;
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1];
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // optimized solution in terms of space complexity
    // time complexity O(mn)
    // space complexity O(n)
    public static int anotherUniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < n; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                { 0, 0, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
