/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), 
 * return the number of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 */
package Problems;

public class NumberOfIslands {

    static int[][] arrVisited;

    public static int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        arrVisited = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arrVisited[i][j] == 1 || grid[i][j] == '0')
                    continue;
                res++;
                helper(grid, i, j);
            }
        }
        return res;
    }

    private static void helper(char[][] grid, int m, int n) {
        arrVisited[m][n] = 1;
        if (n < grid[m].length - 1 && grid[m][n + 1] == '1' && arrVisited[m][n + 1] == 0)
            helper(grid, m, n + 1);
        if (n > 0 && grid[m][n - 1] == '1' && arrVisited[m][n - 1] == 0)
            helper(grid, m, n - 1);
        if (m > 0 && grid[m - 1][n] == '1' && arrVisited[m - 1][n] == 0)
            helper(grid, m - 1, n);
        if (m < grid.length - 1 && grid[m + 1][n] == '1' && arrVisited[m + 1][n] == 0)
            helper(grid, m + 1, n);
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '1', '1', '0' },
                { '1', '1', '0', '1', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '0', '0', '0' }
        };
        System.out.println(numIslands(grid));
    }
}
