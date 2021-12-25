/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 */
package Problems;

import java.util.*;

public class PascalsTriangle2 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            row.add(0, 1);
            for (int j = 1; j < i; j++) {
                row.set(j, row.get(j) + row.get(j + 1));
            }
        }
        return row;
    }

    // better solution (faster)
    public static List<Integer> getRow2(int rowIndex) {
        Integer[] arr = new Integer[rowIndex + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;

        for (int i = 1; i <= rowIndex; i++)
            for (int j = i; j > 0; j--)
                arr[j] = arr[j] + arr[j - 1];

        return Arrays.asList(arr);
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<Integer> result = getRow(numRows);
        for (int result1 : result) {
            System.out.print(result1 + " ");
        }

        List<Integer> result2 = getRow2(numRows);
        for (int result1 : result2) {
            System.out.print(result1 + " ");
        }
    }
}
