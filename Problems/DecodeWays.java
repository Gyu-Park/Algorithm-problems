/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped 
 * then mapped back into letters using the reverse of the mapping above (there may be multiple ways). 
 * For example, "11106" can be mapped into:
 * 
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * 
 * Given a string s containing only digits, return the number of ways to decode it.
 * 
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * 
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 * 
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 * 
 */
package Problems;

public class DecodeWays {
    // recursive solution
    // time complexity: O(2^n) roughly -> time limit exceeded
    // spcae complexity: O(n)
    static int res;
    public static int numDecodings(String s) {
        res = 0;
        helper(s, 0);
        return res;
    }

    private static void helper(String s, int index) {
        if (index > s.length())
            return;
        if (index == s.length()) {
            res++;
            return;
        }
        if (s.charAt(index) == '0')
            return;
        helper(s, index + 1);
        if (s.charAt(index) > '2' || index + 1 >= s.length() || (s.charAt(index) == '2' && s.charAt(index + 1) > '6'))
            return;
        helper(s, index + 2);
    }

    // dp solution
    // time complexity: O(n)
    // spcae complexity: O(n)
    public static int anotherNumDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0')
                dp[i] = 0;
            else {
                dp[i] = dp[i + 1]; // keep updating previous results
                if (i + 1 < s.length() && (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                    dp[i] += dp[i + 2]; // add when two substrings can be an alphabet
                }
            }
        }
        return dp[0];
    }
    
    public static void main(String[] args) {
        String s = "2611055971756562";
        System.out.println(numDecodings(s));
        System.out.println(anotherNumDecodings(s));
    }
}
