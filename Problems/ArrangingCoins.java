/**
 * You have n coins and you want to build a staircase with these coins. 
 * The staircase consists of k rows where the ith row has exactly i coins. 
 * The last row of the staircase may be incomplete.
 * 
 * Given the integer n, return the number of complete rows of the staircase you will build.
 */
package Problems;

public class ArrangingCoins {
    public static int arrangeCoins(int n) {
        int i = 1;
        while (0 < n) {
            n -= i++;
        }
        i--;
        if (n < 0)
            return --i;
        else
            return i;
    }

    // bit manipulation
    public static int anotherArrangeCoins(int n) {
        int mask = 1 << 15;
        long result = 0;

        while (mask > 0) {
            long temp = result | mask;
            long coinsFilled = temp * (temp + 1) / 2;
            if (coinsFilled == n) {
                return (int) temp;
            }
            if (coinsFilled < n) {
                result = temp;
            }
            mask >>= 1;
        }

        return (int) result;
    }

    // binary search solution
    public static int bsArrangeCoins(int n) {
        if (n <= 1) {
            return n;
        }
        if (n <= 3) {
            return n == 3 ? 2 : 1;
        }

        // Binary Search space will start from 2 to n/2.
        long start = 2;
        long end = n / 2;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long coinsFilled = mid * (mid + 1) / 2;
            if (coinsFilled == n) {
                return (int) mid;
            }
            if (coinsFilled < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        // Since at this point start > end, start will start pointing to a value greater
        // than the desired result. We will return end as it will point to the correct
        // int value.
        return (int) end;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(arrangeCoins(n));
        System.out.println(anotherArrangeCoins(n));
        System.out.println(bsArrangeCoins(n));
    }
}
