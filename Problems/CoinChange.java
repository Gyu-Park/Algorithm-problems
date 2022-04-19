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

import java.util.*;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        Arrays.sort(coins);
        List<Integer> list = new ArrayList<>();
        long sum = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            list.add(coins[i]);
            sum += coins[i];
            if (sum < amount) {
                sum = helper(coins, amount, sum, list, i);
            }
            if (sum == amount) {
                break;
            }
            sum -= coins[i];
            list.remove(list.size() - 1);
        }
        return list.size() == 0 ? -1 : list.size();
    }

    private static long helper(int[] coins, int amount, long sum, List<Integer> list, int j) {
        if (sum == amount)
            return sum;
        if (sum > amount) {
            sum -= list.get(list.size() - 1);
            list.remove(list.size() - 1);
            return sum;
        }
        for (int i = j; i >= 0; i--) {
            list.add(coins[i]);
            sum += coins[i];
            sum = helper(coins, amount, sum, list, i);
            if (sum == amount)
                return sum;
        }
        sum -= list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return sum;
    }

    public static int anotherCoinChange(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount]);
    }
    
    // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
    private static int helper(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1; // not valid
        if (rem == 0) return 0; // completed
        if (count[rem - 1] != 0) return count[rem - 1]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        int amount = 11;
        System.out.println(anotherCoinChange(coins, amount));
    }
}
