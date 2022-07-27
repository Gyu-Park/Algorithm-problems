/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below. 
 * More formally, if you are on index i on the current row, 
 * you may move to either index i or index i + 1 on the next row.
 *  --------------------------------------------------------------------------------
 * 
 * Example 1:
 * 
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * 
 * --------------------------------------------------------------------------------
 * 
 * Example 2:
 * 
 * Input: triangle = [[-10]]
 * Output: -10
 * 
 *  --------------------------------------------------------------------------------
 * 
 * Constraints:
 * 
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 */
package Problems;

import java.util.*;

public class Triangle {
    // DP solution
    // time complexity O(n), where n is the total length of the elements in triangle
    // space complexity O(2n)
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1)
            return triangle.get(0).get(0);
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= triangle.size(); i++) {
            for (int j = 1; j <= triangle.get(i - 1).size(); j++) {
                dp[i][0] = Integer.MAX_VALUE;
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i - 1).get(j - 1);
                if (j == triangle.get(i - 1).size() && i < triangle.size())
                    dp[i][j + 1] = Integer.MAX_VALUE;
                if (i == triangle.size())
                    res = Math.min(res, dp[i][j]);
            }
        }

        return res;
    }

    // Optimized DP solution in terms of space complexity
    // time complexity: O(m), where m is the total number of elements in triangle
    // space complexity: O(n), where n is the size of the triangle list.
    public static int anotherMinimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1)
            return triangle.get(0).get(0);
        int[] dp = new int[triangle.size()];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == triangle.size() - 1)
                    dp[j] = triangle.get(i).get(j);
                else
                    dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
    
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        triangle.add(list1);
        triangle.add(list2);
        triangle.add(list3);
        triangle.add(list4);
        // { {2}, {3,4}, {6,5,7}, {4,1,8,3} }
        System.out.println(anotherMinimumTotal(triangle));
    }
}
