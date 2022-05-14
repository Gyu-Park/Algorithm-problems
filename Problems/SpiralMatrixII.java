/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 */
package Problems;

public class SpiralMatrixII {
    // time complexity O(n^2)
    // space complexity O(1)
    public static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        if (n == 1) {
            res[0][0] = 1;
            return res;
        }
        int count = 0;
        int row = 0;
        int col = 0;
        while (count < n * n) {

            while (res[row][col] == 0) {
                res[row][col++] = ++count;
                if (col == n)
                    break;
            }
            col--;
            row++;

            while (res[row][col] == 0) {
                res[row++][col] = ++count;
                if (row == n)
                    break;
            }
            col--;
            row--;

            while (res[row][col] == 0) {
                res[row][col--] = ++count;
                if (col == -1)
                    break;
            }
            col++;
            row--;

            while (res[row][col] == 0) {
                res[row--][col] = ++count;
                if (row == -1)
                    break;
            }
            col++;
            row++;

        }
        return res;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] res = generateMatrix(n);
        for (int[] i : res) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
