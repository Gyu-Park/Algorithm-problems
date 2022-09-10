/**
 * Given a string s which represents an expression, evaluate this expression and return its value. 
 * 
 * The integer division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * 
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * 
 * 
 * Example 1:
 * 
 * Input: s = "3+2*2"
 * Output: 7
 * 
 * 
 * Example 2:
 * 
 * Input: s = " 3/2 "
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 */
package Problems;

import java.util.*;

public class BasicCalculatorII {
    // time complexity: O(n)
    // space complexity: O(n/2)
    public static int calculate(String s) {
        // 1. instaciate a stack
        // 2. using a for loop, build a number in the first if statement
        // 3. in the second if when char is sign or the last index, stack.push() with a corresponding sign
        // 4. using a while loop, calculate all the numbers in the stack.
        // 5. return the result.
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c - '0' >= 0 && c - '0' <= 9) { // Chracter.isDigit(c)
                num = num * 10 + (c - '0');
            } 
            if ((!Character.isDigit(c) && ' ' != c) || i == s.length() - 1) {
                if (lastSign == '+') {
                    stack.push(num);
                } else if (lastSign == '-') {
                    stack.push(-num);
                } else if (lastSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (lastSign == '/') {
                    stack.push(stack.pop() / num);
                }
                lastSign = c;
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty())
            res += stack.pop();
        return res;
    }

    // optimized solution in terms of space complexity
    // time complexity: O(n)
    // spcae complexity: O(1)
    public static int anotherCalculate(String s) {
        int sum = 0;
        int tempSum = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) 
                num = num * 10 + c - '0';
            if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
                switch(lastSign) {
                    case '+':
                        sum += tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum += tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum *= num;
                        break;
                    case '/':
                        tempSum /= num;
                        break;
                }
                lastSign = c;
                num = 0;
            }
        }
        sum += tempSum;
        return sum;
    }
    
    public static void main(String[] args) {
        String s = "999/111+999/111";
        System.out.println(anotherCalculate(s));
    }
}
