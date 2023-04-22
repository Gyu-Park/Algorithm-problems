package Problems;

import java.util.Arrays;

public class MinimumInsertionStepsToMakeStringPalindrome {
    int[][] memo;
    int n;

    // top-bottom dp approach
    // time complexity: O(n^2)
    // space complexity: O(n^2)
    private int helper(String s, int start, int end) {
        if (start >= end)
            return 0;

        if (memo[start][end] != Integer.MAX_VALUE)
            return memo[start][end];

        if (s.charAt(start) == s.charAt(end)) {
            memo[start][end] = helper(s, start + 1, end - 1);
        } else {
            memo[start][end] = Math.min(helper(s, start + 1, end), helper(s, start, end - 1)) + 1;
        }

        return memo[start][end];
    }

    public int minInsertions(String s) {
        n = s.length();
        memo = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(memo[i], Integer.MAX_VALUE);

        return helper(s, 0, n - 1);
    }

    // bottom-up dp solution
    // find longest common sequence between s and its reverse
    // time complexity: O(n^2)
    // space complexity: O(n^2)
    public int minInsertions2(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == s.charAt(n - 1 - j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    continue;
                }
                dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
            }
        }

        return n - dp[n][n];
    }
}
