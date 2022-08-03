/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * Given an integer n, return the nth ugly number.
 *  -------------------------------------------------------------------------------------------------
 * 
 * Example 1:
 * 
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * 
 * -------------------------------------------------------------------------------------------------
 * 
 * Example 2:
 * 
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * 
 * -------------------------------------------------------------------------------------------------
 *  
 * Constraints:
 * 1 <= n <= 1690
 */
package Problems;

import java.util.TreeSet;

public class UglyNumberII {
    // dp solution
    // time complexity: O(n)
    // space complexity: O(n)
    public static int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(Math.min(factor2, factor3), factor5);;
            if (ugly[i] == factor2)
                factor2 = 2 * ugly[++index2];
            if (ugly[i] == factor3)
                factor3 = 3 * ugly[++index3];
            if (ugly[i] == factor5)
                factor5 = 5 * ugly[++index5];
        }

        return ugly[n - 1];
    }

    // treeSet solution
    // time complexity: O(nlogn)
    // space complexity: O(n * 3), so O(n)
    public static int anotherNthUglyNumber(int n) {
        TreeSet<Long> res = new TreeSet<>();
        res.add(1L);
        for (int i = 0; i < n - 1; i++) {
            long first = res.pollFirst();
            res.add(first * 2);
            res.add(first * 3);
            res.add(first * 5);
        }
        return res.first().intValue();
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println(nthUglyNumber(n));
        System.out.println(anotherNthUglyNumber(n));
    }
}
