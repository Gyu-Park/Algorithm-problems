/**
 * You are given an m x n grid where each cell can have one of three values:
 * 
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * 
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
 * If this is impossible, return -1.
 */
package Problems;

import java.util.*;

public class RottingOranges {
    // bfs solution
    // time complexity: O(nm), where n is row and m is col of grid
    // space complexity: O(nm)
    public static int orangesRotting(int[][] grid) {
        Queue<Integer> iQueue = new LinkedList<>();
        Queue<Integer> jQueue = new LinkedList<>();
        int countOne = 0;
        for (int i = 0; i < grid.length; i++) { // O(nm)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    iQueue.offer(i);
                    jQueue.offer(j);
                } else if (grid[i][j] == 1)
                    countOne++;
            }
        }

        if (countOne == 0 && iQueue.isEmpty())
            return 0;
        
        int minutes = helper(grid, iQueue, jQueue); // O(nm)

        for (int i = 0; i < grid.length; i++) { // O(nm)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && minutes > 0) {
                    return -1;
                } else if (grid[i][j] == 1 && minutes == 0) {
                    return -1;
                }
            }
        }
        
        return minutes;
    }

    private static int helper (int[][] grid, Queue<Integer> iQueue, Queue<Integer> jQueue) {
        int minutes = 0;
        int round = iQueue.size();
        while (!iQueue.isEmpty()) {
            int i = iQueue.poll();
            int j = jQueue.poll();
            
            if (i > 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = 2;
                iQueue.offer(i - 1);
                jQueue.offer(j);
            }
            if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                grid[i + 1][j] = 2;
                iQueue.offer(i + 1);
                jQueue.offer(j);
            }
            if (j > 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = 2;
                iQueue.offer(i);
                jQueue.offer(j - 1);
            }
            if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                grid[i][j + 1] = 2;
                iQueue.offer(i);
                jQueue.offer(j + 1);
            }
            round--;
            if (round == 0 && !iQueue.isEmpty()) {
                round = iQueue.size();
                minutes++;
            }
        }
        return minutes;
    }
    
    public static void main(String[] args) {
        int[][] grid = { {2, 1, 1}, {1, 1, 0}, {0, 1, 1} };
        System.out.println(orangesRotting(grid));
    }
}
