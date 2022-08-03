/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * 
 * Find the maximum profit you can achieve. 
 * You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) 
 * with the following restrictions:
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *  
 * Example 1:
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 * Example 2:
 * Input: prices = [1]
 * Output: 0
 *  
 * Constraints:
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
package Problems;

public class BestTimeToBuyAndSellStockWithCooldown {
    // dp solution
    // time complexity: O(n)
    // space complexity: O(n)
    public static int maxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;
        // // buy[i]: max profit if the first "i" days end with a "sell or buy" day
        int[] buy = new int[prices.length + 1];
        int[] sell = new int[prices.length + 1];
        buy[1] = -prices[0];
        
        for (int i = 2; i <= prices.length; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i - 1]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i - 1]);
        }

        return sell[prices.length];
    }

    // optimized dp solution
    // time complexity: O(n)
    // space complexity: O(1)
    public static int optimizedMaxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;
            
        int buy = -prices[0];
        int preBuy = buy;
        int sell = 0, preSell1 = 0, preSell2 = 0;
        
        for (int i = 1; i < prices.length; i++) {
            /* Because of preSell2, which means we sell a stock two days ago, 
             * we can rest one day for the cooldown constraint.
             */
            buy = Math.max(preBuy, preSell2 - prices[i]);
            sell = Math.max(preSell1, preBuy + prices[i]);
            preBuy = buy;
            preSell2 = preSell1;
            preSell1 = sell;
        }

        return sell;
    }
    
    public static void main(String[] args) {
        int[] prices = {1, 2, 0, 1, 2};
        System.out.println(maxProfit(prices));
        System.out.println(optimizedMaxProfit(prices));
    }
}
