package Problems;

import java.util.Arrays;

public class MinimumWindowSubsequence {
    // bottom-up dp solution
    // time complexity: O(nm)
    // space complexity: O(nm)
    public String minWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int j = 0; j < m; j++) {
            if (s1.charAt(j) == s2.charAt(0)) {
                dp[0][j] = j;
                if (n == 1)
                    return s2;
            }
        }

        int start = -1;
        int len = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int startIndex = -1;
            for (int j = 0; j < m; j++) {
                if (startIndex != -1 && s2.charAt(i) == s1.charAt(j)) {
                    dp[i][j] = startIndex;
                    if (i == n - 1 && j - startIndex + 1 < len) {
                        len = j - startIndex + 1;
                        start = startIndex;
                    }
                }
                if (dp[i - 1][j] > -1)
                    startIndex = dp[i - 1][j];
            }
        }

        return start == -1 ? "" : s1.substring(start, start + len);
    }
}
