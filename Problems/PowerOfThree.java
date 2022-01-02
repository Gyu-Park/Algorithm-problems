/**
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * 
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 */
package Problems;

public class PowerOfThree {
    public static boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19, 3^20 is bigger than int
        return (n > 0 && 1162261467 % n == 0);
    }

    public static void main(String[] args) {
        int n = 9;
        System.out.println(isPowerOfThree(n));
    }
}
