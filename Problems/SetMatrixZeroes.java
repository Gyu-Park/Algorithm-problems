/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * 
 * You must do it in place.
 */
package Problems;

import java.util.*;

public class SetMatrixZeroes {
    // time complexity O(mn)
    // space complexity O(n)
    public static void anotherSetZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1 && n == 1)
            return;
        Set<Integer> zeroCol = new HashSet<>();
        boolean zeroInRow = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroCol.add(j);
                    zeroInRow = true;
                }
                if (zeroInRow && j == n - 1) {
                    zeroInRow = false;
                    Arrays.fill(matrix[i], 0);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    break;
                if (zeroCol.contains(j))
                    matrix[i][j] = 0;
            }
        }
    }

    // better solution in terms of space complexity
    // time complexity O(mn)
    // space complexity O(1)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int firstRowZero = 0;

        while (firstRowZero < n && matrix[0][firstRowZero] != 0)
            firstRowZero++;

        // Use first row/column as marker, scan the matrix
        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;

        // Set the zeros
        for (int i = 1; i < m; i++)
            for (int j = n - 1; j >= 0; j--)
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;

        // Set the zeros for the first row
        if (firstRowZero < n)
            Arrays.fill(matrix[0], 0);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 0, 1, 2, 0 },
                { 3, 4, 5, 2 },
                { 1, 3, 1, 5 }
        };
        anotherSetZeroes(matrix);
        for (int[] array : matrix) {
            for (int i : array) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
