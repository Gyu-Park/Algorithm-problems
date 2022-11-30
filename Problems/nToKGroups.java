package Problems;

import java.util.*;

public class nToKGroups {

    public static int anToKGroups(int n, int k) {
        if (n < k) {
            return 0;
        }
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - i];
                }
            }
        }
        return dp[k][n];
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        int remaining = count.size();
        int occur = 1;
        int[] occurrenceCount = new int[arr.length + 1];
        for (int v : count.values())
            ++occurrenceCount[v];
        while (k > 0) {
            if (k - occur * occurrenceCount[occur] >= 0) {
                k -= occur * occurrenceCount[occur];
                remaining -= occurrenceCount[occur++];
            } else {
                return remaining - k / occur;
            }
        }
        return remaining;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 3, 1, 1, 3, 3, 2 };
        System.out.println(anToKGroups(8, 4));
        System.out.println(findLeastNumOfUniqueInts(arr, 3));
    }

}
