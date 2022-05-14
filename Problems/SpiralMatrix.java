/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 */
package Problems;

import java.util.*;

public class SpiralMatrix {
    // time and space complexity O(m * n)
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rowLen = -1;
        int colLen = 0;
        int direction = 1;
        List<Integer> res = new ArrayList<>();

        while (res.size() < matrix.length * matrix[0].length) {
            switch (direction) {
                case 1:
                    rowLen++;
                    int nCopy = n;
                    while (nCopy > 0) {
                        res.add(matrix[colLen][rowLen++]);
                        nCopy--;
                    }
                    rowLen--;
                    break;
                case 2:
                    colLen++;
                    int mCopy = m;
                    while (mCopy > 0) {
                        res.add(matrix[colLen++][rowLen]);
                        mCopy--;
                    }
                    colLen--;
                    break;
                case 3:
                    rowLen--;
                    nCopy = n;
                    while (nCopy > 0) {
                        res.add(matrix[colLen][rowLen--]);
                        nCopy--;
                    }
                    rowLen++;
                    break;
                case 4:
                    colLen--;
                    mCopy = m;
                    while (mCopy > 0) {
                        res.add(matrix[colLen--][rowLen]);
                        mCopy--;
                    }
                    colLen++;
                    break;
            }

            if (direction == 1 || direction == 3)
                m--;
            else
                n--;
            if (direction == 4)
                direction = 1;
            else
                direction++;

        }

        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        List<Integer> res = spiralOrder(matrix);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
