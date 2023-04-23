package Problems;

public class RestoreTheArray {
    final int MOD = 1_000_000_007;
    int n;
    int[] memo;

    // top-down dp solution
    // time complexity: O(nlogk)
    // space complexity: O(n)
    private int helper(String s, int k, int index) {
        if (index == n)
            return 1;

        if (memo[index] > 0)
            return memo[index];

        int ret = 0;
        long num = 0;
        for (int i = index; i < n; i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (s.charAt(index) != '0' && num <= k)
                ret += helper(s, k, i + 1);
            else
                break;
            ret %= MOD;
        }

        memo[index] = ret;
        return memo[index];
    }

    public int numberOfArrays(String s, int k) {
        n = s.length();
        memo = new int[n];
        return helper(s, k, 0);
    }

    // bottom-up dp solution
    // time complexity: O(nlogk)
    // spcae complexity: O(n)
    public int numberOfArrays2(String s, int k) {
        int MOD = 1_000_000_007;
        int n = s.length();
        int[] dp = new int[n + 1];
        // base case
        dp[0] = 1;

        // inductive case
        for (int i = 1; i <= n; i++) { // n iteration
            int index = i - 1;
            if (s.charAt(index) == '0')
                continue;
            long num = 0;
            for (int j = index; j < n; j++) { // iterate up to the number of digits of k, which log k
                num = num * 10 + s.charAt(j) - '0';
                if (num > k)
                    break;
                dp[j + 1] += dp[i - 1];
                dp[j + 1] %= MOD;
            }
        }
        return dp[n];
    }
}
