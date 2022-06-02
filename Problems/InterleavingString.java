/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * 
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * 
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
package Problems;

import java.util.*;

public class InterleavingString {
    // dp solution
    // time complexity O(s1Len * s2Len)
    // space complexity O(s1Len+1 * s2Len+1)
    public static boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();

        if (s1Len + s2Len != s3Len) {
            return false;
        }

        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1Len; i++) {
            dp[i][0] = dp[i - 1][0] && s3.charAt(i - 1) == s1.charAt(i - 1);
        }

        for (int j = 1; j <= s2Len; j++) {
            dp[0][j] = dp[0][j - 1] && s3.charAt(j - 1) == s2.charAt(j - 1);
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                int c1 = s1.charAt(i - 1);
                int c2 = s2.charAt(j - 1);
                int c3 = s3.charAt(i + j - 1);

                // If c3 matches c1, then is every char before c1 in s1 valid?
                // If c3 matches c2, then is every char before c2 in s2 valid?
                dp[i][j] = (c1 == c3 && dp[i - 1][j]) || (c2 == c3 && dp[i][j - 1]);
            }
        }

        return dp[s1Len][s2Len];
    }

    // dfs with memorization
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        HashSet<Integer> cache = new HashSet<Integer>();
        return helper(s1, s2, s3, 0, 0, cache);
    }

    private static boolean helper(String s1, String s2, String s3, int p1, int p2, HashSet<Integer> cache) {
        if (p1 + p2 == s3.length())
            return true;
        if (cache.contains(p1 * s3.length() + p2))
            return false;

        // no need to store actual result.
        // if we found the path, we have already terminated.
        cache.add(p1 * s3.length() + p2);
        boolean match1 = p1 < s1.length() && s3.charAt(p1 + p2) == s1.charAt(p1);
        boolean match2 = p2 < s2.length() && s3.charAt(p1 + p2) == s2.charAt(p2);
        if (match1 && match2)
            return helper(s1, s2, s3, p1 + 1, p2, cache) ||
                    helper(s1, s2, s3, p1, p2 + 1, cache);
        else if (match1)
            return helper(s1, s2, s3, p1 + 1, p2, cache);
        else if (match2)
            return helper(s1, s2, s3, p1, p2 + 1, cache);
        else
            return false;
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbcacc";
        System.out.println(isInterleave2(s1, s2, s3));
    }
}
