/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * 
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * --------------------------------------------------------------------
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * --------------------------------------------------------------------
 * Example 2:
 * 
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. 
 *              Therefore, by repeating 'a' once, it becomes "aa".
 * ---------------------------------------------------------------------
 * Example 3:
 * 
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * ---------------------------------------------------------------------
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
package Problems;

public class RegularExpressionMatching {
    // DP solution
    // time complexity: O(n^2)
    // space complexity: O(n^2)
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int j = 2; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2])
                dp[0][j] = true;
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 2] || ((p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) && dp[i][j-1]);
            }
        }

        return dp[s.length()][p.length()];
    }
    
    public static void main(String[] args) {
        String s = "aacdefghij", p = "a*a*cdeff*gh..";
        System.out.println(isMatch(s, p));
    }
}
