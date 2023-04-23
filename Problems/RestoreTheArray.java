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

        long ret = 0;
        for (int i = index + 1; i <= n; i++) {
            if (s.charAt(index) != '0' && Long.parseLong(s.substring(index, i)) <= k)
                ret += helper(s, k, i);
            else
                break;
            ret %= MOD;
        }

        memo[index] = (int) ret;
        return memo[index];
    }

    public int numberOfArrays(String s, int k) {
        n = s.length();
        memo = new int[n];
        return helper(s, k, 0);
    }
}
