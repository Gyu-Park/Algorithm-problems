/**
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 */
package Problems;

import java.util.*;

public class MinimumPathSum {

    static List<Integer> sums = new ArrayList<>();

    // Basic recursive solution
    public static int minPathSum(int[][] grid) {
        recursion(grid, 0, 0, 0);
        int res = Integer.MAX_VALUE;
        for (int i : sums) {
            res = Math.min(res, i);
        }
        return res;
    }

    private static void recursion(int[][] grid, int col, int row, int sum) {
        sum += grid[col][row];
        if (row < grid[col].length - 1) {
            recursion(grid, col, row + 1, sum);
        }
        if (col < grid.length - 1) {
            recursion(grid, col + 1, row, sum);
        }
        if (col == grid.length - 1 && row == grid[col].length - 1) {
            sums.add(sum);
        }
        sum -= grid[col][row];
    }

    // DP solution
    public static int anotherMinPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = grid[i][j];
                } else if (i != 0 && j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else if (i == 0 && j != 0) {
                    grid[i][j] += grid[i][j - 1];
                } else {
                    grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[row - 1][col - 1];
    }
    
    public static void main(String[] args) {
        int[][] grid = { {1, 3, 1}, {1, 5, 1}, {4, 2, 1} };
        System.out.println(minPathSum(grid));
        System.out.println(anotherMinPathSum(grid));
    }
}
