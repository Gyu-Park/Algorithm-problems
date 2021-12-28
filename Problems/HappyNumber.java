/**
 * Write an algorithm to determine if a number n is happy.
 * 
 * A happy number is a number defined by the following process:
 * 
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 */
package Problems;

public class HappyNumber {
    public static boolean isHappy(int n) {
        int result = 0;
        while (true) {
            while (n != 0) {
                int endIndex = n % 10;
                result += endIndex * endIndex;
                n = n / 10;
            }

            if (result == 89)
                return false;

            if (result == 1 || result == 7)
                return true;

            n = result;
            result = 0;
        }
    }

    // recursive method
    public static boolean recursiveIsHappy(int n) {
        if (n == 1 || n == 7)
            return true;
        else if (n < 10)
            return false;
        int m = 0;
        while (n != 0) {
            int tail = n % 10;
            m += tail * tail;
            n = n / 10;
        }
        return recursiveIsHappy(m);
    }

    public static void main(String[] args) {
        int n = 44;
        System.out.println(isHappy(n));
        System.out.println(recursiveIsHappy(n));
    }
}
