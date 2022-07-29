/**
 * A super ugly number is a positive integer whose prime factors are in the array primes.
 * Given an integer n and an array of integers primes, return the nth super ugly number.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *  
 * Example 1:
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
 * 
 * Example 2:
 * Input: n = 1, primes = [2,3,5]
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].
 *  
 * Constraints:
 * 1 <= n <= 105
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * primes[i] is guaranteed to be a prime number.
 * All the values of primes are unique and sorted in ascending order.
 */
package Problems;

public class SuperUglyNumber {
    // naive dp solution
    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        
        int[] dp = new int[(int) Math.pow(primes[0], n)];
        dp[1] = 1;
        int count = 0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 1) {
                count++;
                if (count == n)
                    return i;
                for (int j = 0; j < primes.length; j++) {
                    int num = i * primes[j];
                    dp[num] = 1;
                }
            }
        }

        return -1;
    }

    // another dp solution
    // time complexity: O(nk) where k is the length of primes array.
    // space complexity: Math.max( O(n), O(k) )
    public static int anotherNthSuperUglyNumber(int n, int[] primes) {
        if (n == 1)
            return 1;
        
        // dp array stores the first to nth numbers that are divisible by prime numbers in the "primes" array
        int[] dp = new int[n];
        // index array keeps track of the power of the primes[index]
        int[] index = new int[primes.length];
        // the first number is always 1 since every prime number is divisible by 1.
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            // find the minimum number of the nth number
            for (int j = 0; j < index.length; j++)
                dp[i] = Math.min(dp[i], primes[j] * dp[index[j]]);

            // if the dp[i] is a power of primes[j], index[j]++ (power tracking)
            for (int j = 0; j < primes.length; j++)
                while (primes[j] * dp[index[j]] <= dp[i]) 
                    index[j]++;
        }

        // return the nth number
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2, 7, 13, 19};
        int res = anotherNthSuperUglyNumber(n, primes);
        System.out.println(res);
    }
}
