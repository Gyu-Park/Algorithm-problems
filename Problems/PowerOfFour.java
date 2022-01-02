/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * 
 * An integer n is a power of four, if there exists an integer x such that n == 4^x.
 */
package Problems;

public class PowerOfFour {
    // soultion using log
    public static boolean isPowerOfFour(int n) {
        double result = (Math.log(n) / Math.log(4));
        if (result % 1 == 0 || result % 1 < 0.000000001)
            return true;
        else
            return false;
    }

    // solution by manipulating a binary number
    public boolean betterIsPowerOfFour(int n) {
        // hex 0x55555555 is 1010101010101010101010101010101
        // so we can find if 1 is in an odd place.
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(isPowerOfFour(n));
    }
}
