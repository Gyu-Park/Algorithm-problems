/**
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
 * Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
 * For example, there will not be input like 3a or 2[4].
 */
package Problems;

import java.util.*;

public class DecodeString {
    // a solution using recursion
    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 47 && c < 58) {
                num.append(c);
            } else if (c == '[') {
                i = helper(s, res, i + 1, Integer.parseInt(num.toString()) - 1);
                num.delete(0, num.length());
            } else if (c != ']') {
                res.append(c);
            }
        }
        return res.toString();
    }

    private static int helper(String s, StringBuilder res, int index, int loopNum) {
        StringBuilder num = new StringBuilder();
        int indexOfOpenBracket = index - 1;
        int i;
        for (i = index; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 47 && c < 58) {
                num.append(c);
            } else if (c == '[') {
                i = helper(s, res, i + 1, Integer.parseInt(num.toString()) - 1);
                num.delete(0, num.length());
            } else if (c == ']' && loopNum != 0) {
                i = indexOfOpenBracket;
                loopNum--;
            } else if (c == ']' && loopNum == 0) {
                return i;
            } else {
                res.append(c);
            }
        }
        return i;
    }

    // a recursive solution but more concise and readable
    // than the first recursive solution.
    public String conciseDecodeString(String s) {
        Deque<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray())
            queue.offer(c);
        return helper(queue);
    }

    public String helper(Deque<Character> queue) {
        StringBuilder res = new StringBuilder();
        int num = 0;
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                String sub = helper(queue);
                for (int i = 0; i < num; i++)
                    res.append(sub);
                num = 0;
            } else if (c == ']') {
                break;
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

    // slower soultion using a stack
    public String anotherDecodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "100[leetcode]";
        System.out.println(decodeString(s));
    }
}
