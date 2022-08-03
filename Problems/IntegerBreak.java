/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * Return the maximum product you can get.
 *  
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * 
 * Example 2:
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *  
 * Constraints:
 * 2 <= n <= 58
 */
package Problems;

public class IntegerBreak {
    // math solution
    // time complexity: O(1)
    // spcae complexity: O(1)
    public static int integerBreak(int n) {
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        if (n == 4)
            return 4;
        
        int res = 0;
        int  pow3 = n % 3 == 1 ? n / 3 - 1 : n / 3;
        int  pow2 = (n - 3 * pow3) / 2;
        res = (int) Math.pow(3, pow3);
        res *= (int) Math.pow(2, pow2);
        return res;
    }

    // dp solution
    // time complexity: O(n^2)
    // space complexity: O(n)
    public static int anotherIntegerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }

        return dp[n];
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
        System.out.println(anotherIntegerBreak(n));
    }
}
