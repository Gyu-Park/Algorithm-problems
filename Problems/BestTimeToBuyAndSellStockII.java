/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * 
 * On each day, you may decide to buy and/or sell the stock. 
 * You can only hold at most one share of the stock at any time. 
 * However, you can buy it then immediately sell it on the same day.
 * 
 * Find and return the maximum profit you can achieve.
 * 
 * 
 * Example 1:
 * 
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * 
 * 
 * Example 2:
 * 
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * 
 * 
 * Example 3:
 * 
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 *  
 * 
 * Constraints:
 * 
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
package Problems;

public class BestTimeToBuyAndSellStockII {
    // dp solution with only 4 variables (without an array)
    // time complexity: O(n)
    // space complexity: O(1)
    public static int maxProfit(int[] prices) {
        int sold = 0;
        int buy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int curSold = Math.max(sold, buy + prices[i]);
            int curBuy = Math.max(buy, sold - prices[i]);
            sold = curSold;
            buy = curBuy;
        }
        return sold;
    }

    // another solution with while loops
    // time complexity: O(n)
    // space complexity: O(1)
    public static int anotherMaxProfit1(int[] prices) {
        int i = 0;
        int buy, sell, profit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i + 1] <= prices[i])
                i++;
            buy = prices[i];

            while (i < prices.length - 1 && prices[i + 1] > prices[i])
                i++;
            sell = prices[i];
            profit += sell - buy;
        }
        return profit;
    }
    
    public static void main(String[] args) {
        int[] prices = {6, 1, 3, 2, 4, 7};
        System.out.println(maxProfit(prices));
        System.out.println(anotherMaxProfit1(prices));
    }
}
