/**
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted 
 * without changing the relative order of the remaining characters.
 * 
 * For example, "ace" is a subsequence of "abcde".
 * 
 * A common subsequence of two strings is a subsequence that is common to both strings.
 */
package Problems;

public class LongestCommonSubsequence {
    // time complexity: O(nm), where n and m are the length of text1 and text2
    // space complexity: O(nm)
    public static int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j < dp[i].length; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence(text1, text2));

    }
}
