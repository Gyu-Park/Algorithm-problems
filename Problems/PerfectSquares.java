/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * 
 * A perfect square is an integer that is the square of an integer; 
 * in other words, it is the product of some integer with itself. 
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 */
package Problems;

public class PerfectSquares {
    // dp solution
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int sqrt = (int) Math.sqrt(i);
            if (sqrt * sqrt == i) {
                dp[i] = 1;
                continue;
            }
            for (int j = 1; j <= sqrt; j++)
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 111;
        System.out.println(numSquares(n));
    }
}
