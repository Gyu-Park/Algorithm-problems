/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 */
package Problems;

import java.util.*;

public class LongestValidParentheses {
    public static int longestValidParentheses(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.empty() || s.charAt(stack.peek()) == ')') {
                    stack.push(i);
                } else {
                    stack.pop();
                }
            }
        }
        int res = 0;
        int j = s.length();
        while (!stack.empty()) {
            int i = stack.pop();
            j -= i;
            j--;
            res = Math.max(res, j);
            j = i;
        }
        res = Math.max(res, j);
        return res;
    }

    public static int anotherLongestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0) {
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = ")()())((()))";
        System.out.println(longestValidParentheses(s));
        System.out.println(anotherLongestValidParentheses(s));
    }
}
