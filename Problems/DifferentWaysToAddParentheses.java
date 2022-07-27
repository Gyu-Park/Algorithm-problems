/**
 * Given a string expression of numbers and operators, 
 * return all possible results from computing all the different possible ways to group numbers and operators. 
 * You may return the answer in any order.
 * The test cases are generated such that the output values fit in a 32-bit integer 
 * and the number of different results does not exceed 104.
 *  --------------------------------------------------------------------------------
 * 
 * Example 1:
 * 
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0 
 * (2-(1-1)) = 2
 * 
 * --------------------------------------------------------------------------------
 * 
 * Example 2:
 * 
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34 
 * ((2*3)-(4*5)) = -14 
 * ((2*(3-4))*5) = -10 
 * (2*((3-4)*5)) = -10 
 * (((2*3)-4)*5) = 10
 * 
 *  --------------------------------------------------------------------------------
 * 
 * Constraints:
 * 
 * 1 <= expression.length <= 20
 * expression consists of digits and the operator '+', '-', and '*'.
 * All the integer values in the input expression are in the range [0, 99].
 */
package Problems;

import java.util.*;

public class DifferentWaysToAddParentheses {

    static Map<String, List<Integer>> map = new HashMap<>();

    // solution using a hash map (divide and conquer)
    public static List<Integer> diffWaysToCompute(String expression) {
        if (map.containsKey(expression))
            return map.get(expression);
        
        List<Integer> res = new LinkedList<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // if char is operation, then divide both sides and save those in String objects
            if (c == '+' || c == '-' || c == '*') {
                String s1 = expression.substring(0, i);
                String s2 = expression.substring(i + 1, expression.length());

                // recursive call for divide and conquer
                List<Integer> l1 = diffWaysToCompute(s1);
                List<Integer> l2 = diffWaysToCompute(s2);

                // calculate
                for (Integer i1 : l1) {
                    for (Integer i2 : l2) {
                        int r = 0;
                        if (c == '+') {
                            r = i1 + i2;
                        } else if (c == '-') {
                            r = i1 - i2;
                        } else if (c == '*') {
                            r = i1 * i2;
                        }
                        res.add(r);
                    }
                }
            }
        }

        // if the size of result list is 0, that means that the expression is just a number
        // so just add the value into the result list
        if (res.size() == 0)
            res.add(Integer.valueOf(expression));
        
        // save the result list in a map
        map.put(expression, res);
        return res;
    }
    
    public static void main(String[] args) {
        String expression = "2*3-4*5";
        List<Integer> res = diffWaysToCompute(expression);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
