/**
 * You are given an integer array coins representing coins of different denominations 
 * and an integer amount representing a total amount of money.
 * 
 * Return the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * 
 * You may assume that you have an infinite number of each kind of coin.
 */
package Problems;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        return helper(coins, amount, new int[amount]);
    }

    private static int helper(int[] coins, int rem, int[] count) {
        if (rem < 0)
            return -1;
        if (rem == 0)
            return 0;
        if (count[rem - 1] != 0)
            return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    // solution using dp
    public static int anotherCoinChange(int[] coins, int amount) {
        if (amount < 1)
            return 0;
        int[] dp = new int[amount];
        for (int i = dp.length - 1; i >= 0; i--) {
            int step = 1;
            int sum = i;
            dp[i] = amount + 1;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > amount)
                    continue;
                if (coins[j] + sum == amount) {
                    dp[i] = Math.min(dp[i], step);
                } else if (coins[j] + sum > amount) {
                    continue;
                } else if (sum + coins[j] < amount) {
                    dp[i] = Math.min(dp[i], step + dp[sum + coins[j]]);
                }
            }
        }
        return dp[0] > amount ? -1 : dp[0];
    }

    public static void main(String[] args) {
        int[] coins = { 2147483647 };
        int amount = 20;
        System.out.println(anotherCoinChange(coins, amount));
    }
}
