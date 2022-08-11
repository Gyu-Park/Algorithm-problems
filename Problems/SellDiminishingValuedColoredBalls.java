/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 * 
 * The customer weirdly values the colored balls. 
 * Each colored ball's value is the number of balls of that color you currently have in your inventory. 
 * For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. 
 * After the transaction, there are only 5 yellow balls left, 
 * so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 * 
 * You are given an integer array, inventory, 
 * where inventory[i] represents the number of balls of the ith color that you initially own. 
 * You are also given an integer orders, which represents the total number of balls that the customer wants. 
 * You can sell the balls in any order.
 * 
 * Return the maximum total value that you can attain after selling orders colored balls. 
 * As the answer may be too large, return it modulo 109 + 7.
 */
package Problems;

import java.util.Arrays;

public class SellDiminishingValuedColoredBalls {
    // Math Formula: (x+1) + (x+2) + ... + (n-1) + n => (1 + 2 + 3 + ...+ (n-1) + n) - (1 + 2 + 3 + ... + (x-1) + x) => (n * (n+1)/2) - (x * (x+1)/2)
    // ex) when orders >= count * (inventory[4]-inventory[3]) => (22 >= 2 * (9-6)),
    //     we can sell at (2 * (9 + 8 + 7)) => 2 * ((9*10)/2 - (6*7)/2) => 2 * 24 => 48
    // greedy algo solution
    // time complexity: O(nlogn)
    // space complexity: O(1)
    public static int maxProfit(int[] inventory, int orders) {
        long res = 0;
        Arrays.sort(inventory);
        int len = inventory.length - 1;
        int count = 1;
        while (orders > 0) {
            if (len > 0 && inventory[len] > inventory[len - 1] && orders >= count * (inventory[len] - inventory[len - 1])) {
                res += count * sumFromNtoX(inventory[len], inventory[len - 1]);
                orders -= count * (inventory[len] - inventory[len - 1]);
            } else if (len == 0 || inventory[len] > inventory[len - 1]) {
                long a = orders / count;
                res += count * sumFromNtoX(inventory[len], inventory[len] - a);
                long b = orders % count;
                res += b * (inventory[len] - a);
                orders = 0;
            }
            res %= 1000000007;
            len --;
            count ++;
        }
        return (int) res;
    }
    
    private static long sumFromNtoX(long n, long x) {
        return (n * (n + 1)) / 2 - (x * (x + 1)) / 2;
    }
    
    public static void main(String[] args) {
        int orders = 4;
        int[] inventory = {2, 5};
        System.out.println(maxProfit(inventory, orders));
    }
}
