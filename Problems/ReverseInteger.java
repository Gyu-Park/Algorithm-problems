/**
 * Given a signed 32-bit integer x, return x with its digits reversed. 
 * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */
package Problems;

public class ReverseInteger {
    public static int reverse(int x) {
        int res = 0;
        int preRes = 0;
        while (x != 0) {
            preRes = res;
            int lastDigit = x % 10;
            x = x / 10;
            res = res * 10 + lastDigit;
            if (res / 10 != preRes)
                return 0;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 1534236469;
        System.out.println(reverse(n));
    }
}
