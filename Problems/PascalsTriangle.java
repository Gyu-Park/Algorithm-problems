/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *         1
 *       1   1
 *      1  2  1
 *     1  3 3  1
 *    1 4  6  4 1
 *   1 5 10 10 5 1
 */
package Problems;

import java.util.*;

public class PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        int count = 0;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        while (numRows != count) {
            List<Integer> preRow = null;
            List<Integer> row = new ArrayList<>();
            if (list.size() > 0) {
                preRow = list.get(count - 1);
            }

            if (preRow == null) {
                row.add(1);
                list.add(row);
                count++;
            } else if (preRow.size() == 1) {
                row.add(1);
                row.add(1);
                list.add(row);
                count++;
            } else {
                row.add(1);
                for (int i = 0; i < count - 1; i++) {
                    row.add(preRow.get(i) + preRow.get(i + 1));
                }
                row.add(1);
                list.add(row);
                count++;
            }
        }

        return list;
    }

    // better solution
    public static List<List<Integer>> shorterGenerate(int numRows) {
        int count = 0;
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> row = new ArrayList<>();
        while (numRows != count) {
            row.add(0, 1);
            for (int i = 1; i < count; i++) {
                row.set(i, row.get(i) + row.get(i + 1));
            }
            list.add(new ArrayList<Integer>(row));
            count++;
        }
        return list;
    }

    public static void main(String[] args) {
        int numRows = 5;
        List<List<Integer>> result = generate(numRows);
        for (List<Integer> list : result) {
            System.out.println(" ");
            for (Integer a : list) {
                System.out.print(a);
            }
        }

        result = shorterGenerate(numRows);
        for (List<Integer> list : result) {
            System.out.println(" ");
            for (Integer a : list) {
                System.out.print(a);
            }
        }
    }
}
