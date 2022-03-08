/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). 
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). 
 * The robot can only move either down or right at any point in time.
 * 
 * Given the two integers m and n, return the number of possible unique paths 
 * that the robot can take to reach the bottom-right corner.
 * 
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */
package Problems;

public class UniquePaths {
    // recursive solution, but it is slow.
    public static int uniquePaths(int m, int n) {
        return recursion(n - 1, m - 1, 0);
    }

    private static int recursion(int right, int down, int totalPath) {
        if (right > 0)
            totalPath = recursion(right - 1, down, totalPath);
        if (down > 0)
            totalPath = recursion(right, down - 1, totalPath);
        if (right == 0 && down == 0)
            totalPath++;
        return totalPath;
    }

    // memorization solution. Much faster
    public static int memorizationUniquePaths(int m, int n) {
        return memo(m - 1, n - 1, new int[n][m]);
    }

    private static int memo(int m, int n, int[][] memo) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (m == 0 || n == 0) {
            return 1;
        } else if (memo[n][m] > 0) {
            return memo[n][m];
        } else {
            memo[n][m] = memo(m - 1, n, memo) + memo(m, n - 1, memo);
            return memo[n][m];
        }
    }
    
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(uniquePaths(m, n));
        System.out.println(memorizationUniquePaths(m, n));
    }
}
