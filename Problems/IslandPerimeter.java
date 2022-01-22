/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * 
 * Grid cells are connected horizontally/vertically (not diagonally). 
 * The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * 
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. 
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
 * Determine the perimeter of the island.
 */
package Problems;

import java.util.*;

public class IslandPerimeter {
    public static int islandPerimeter(int[][] grid) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<String> isVisted = new HashSet<>();
        int PerimeterOfIsland = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                isVisted.add(i + "-" + j);
                if (grid[i][j] == 1) {
                    queue1.add(i);
                    queue2.add(j);
                    break;
                }
            }
            if (!queue1.isEmpty())
                break;
        }

        while (!queue1.isEmpty()) {
            PerimeterOfIsland += 4;
            int i = queue1.poll();
            int j = queue2.poll();
            if (j > 0 && grid[i][j - 1] == 1) {
                PerimeterOfIsland--;
                if (isVisted.add(i + "-" + (j - 1))) {
                    queue1.add(i);
                    queue2.add(j - 1);
                }
            }
            if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                PerimeterOfIsland--;
                if (isVisted.add(i + "-" + (j + 1))) {
                    queue1.add(i);
                    queue2.add(j + 1);
                }
            }
            if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                PerimeterOfIsland--;
                if (isVisted.add((i + 1) + "-" + j)) {
                    queue1.add(i + 1);
                    queue2.add(j);
                }
            }
            if (i > 0 && grid[i - 1][j] == 1) {
                PerimeterOfIsland--;
                if (isVisted.add((i - 1) + "-" + j)) {
                    queue1.add(i - 1);
                    queue2.add(j);
                }
            }
        }
        return PerimeterOfIsland;
    }

    // simpler solution
    public static int anotherIslandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 1) {
                    continue;
                } else {
                    if (i - 1 < 0 || grid[i - 1][j] == 0)
                        perimeter++;
                    if (i + 1 >= grid.length || grid[i + 1][j] == 0)
                        perimeter++;
                    if (j + 1 >= grid[0].length || grid[i][j + 1] == 0)
                        perimeter++;
                    if (j - 1 < 0 || grid[i][j - 1] == 0)
                        perimeter++;

                }
            }
        }
        return perimeter;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 }
        };
        System.out.println(islandPerimeter(grid));
        System.out.println(anotherIslandPerimeter(grid));
    }
}