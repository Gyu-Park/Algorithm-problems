/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, 
 * and return the result of the evaluation.
 * 
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 */
package Problems;

import java.util.Stack;

public class BasicCalculator {
    // time complexity: O(n^2)
    // space complexity: O(n)
    public static int calculate(String s) {
        s = s.trim();
        Stack<String> stack = new Stack<>();
        StringBuilder num = new StringBuilder();
        char lastSymbol = '+';
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if (Character.isDigit(c))
                num.insert(0, c);
            if (c == ')') {
                stack.add(")");
            }
            if (c == '+' || c == '-') {
                if (lastSymbol == '(')
                    signChange(stack, c);
                else
                    stack.add(String.valueOf(c) + num.toString());
                num.delete(0, num.length());
                lastSymbol = c;
            }
            if (c == '(') {
                // when c == '('
                int intNum = num.length() > 0 ? Integer.parseInt(num.toString()) : 0;
                while (stack.peek() != ")") {
                    Integer lastNum = Integer.parseInt(stack.pop());
                    intNum = lastNum + intNum;
                }
                stack.pop();
                stack.add(String.valueOf(intNum));
                num.delete(0, num.length());
                lastSymbol = c;
            }
            if (Character.isDigit(c) && i == 0)
                stack.add(num.toString());
        }
        Integer lastNum = 0;
        while (!stack.isEmpty()) {
            lastNum += Integer.parseInt(stack.pop());
        }
        return lastNum;
    }

    private static void signChange(Stack<String> stack, char c) {
        if (c == '+')
            return;
        String st = stack.pop();
        if (st.charAt(0) == '+') {
            st = st.replace('+', '-');
        } else if (st.charAt(0) == '-') {
            st = st.replace('-', '+');
        } else {
            st = "-" + st;
        }
        stack.add(st);
    }

    // better solution
    // time complexity: O(n)
    // space complexity: O(n/2)
    public static int anotherCalculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            } else if (c == '+'){
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;   
                result = 0;
            } else if (c == ')'){
                result += sign * number;  
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        if (number != 0) result += sign * number;
        return result;
    }
    
    public static void main(String[] args) {
        String s = "- (3 + (4 + 5))"; // 1+11-3= 9-14= -5
        System.out.println(calculate(s));
    }
}
