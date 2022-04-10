/**
 * Given an m x n binary matrix filled with 0's and 1's, 
 * find the largest square containing only 1's and return its area.
 */
package Problems;

public class MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] newMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 && matrix[i][j] == '1') {
                    newMatrix[i][j] = 1;
                } else if (matrix[i][j] == '1') {
                    newMatrix[i][j] = newMatrix[i][j - 1] + 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m - res; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (newMatrix[i][j] > res) {
                    res = Math.max(res, checkColumn(newMatrix, i, j, newMatrix[i][j]));
                }
            }
        }

        return res * res;
    }

    private static int checkColumn(int[][] matrix, int row, int col, int len) {
        int count = 2;
        int ret = 1;
        for (int i = row + 1; i < matrix.length; i++) {
            if (matrix[i][col] > count && ret < len) {
                ret++;
                len = Math.min(len, matrix[i][col]);
            } else if (matrix[i][col] == count && ret < len) {
                return ++ret;
            } else {
                return count - 1;
            }
            count++;
        }
        return ret;
    }

    public static int anotherMaximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                { '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0' },
                { '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1' },
                { '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1' },
                { '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0' },
                { '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1' },
                { '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '0', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1' },
                { '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1' },
                { '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1' }
        };
        System.out.println(anotherMaximalSquare(matrix));
    }
}
