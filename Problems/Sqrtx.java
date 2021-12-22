/**
 * Given a non-negative integer x, compute and return the square root of x.
 * 
 * Since the return type is an integer, the decimal digits are truncated, 
 * and only the integer part of the result is returned.
 * 
 * Note: You are not allowed to use any built-in exponent function or operator, 
 * such as pow(x, 0.5) or x ** 0.5.
 */
package Problems;

public class Sqrtx {
    public static int mySqrt(int x) {
        if (x == 0)
            return 0;
        int start = 1, end = x;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid <= x / mid && (mid + 1) > x / (mid + 1))
                return mid;
            else if (mid > x / mid)
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }

    public static void main(String[] args) {
        int x = 8;
        System.out.println(mySqrt(x));
    }
}
