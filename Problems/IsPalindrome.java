/**
 * Given an integer x, return true if x is palindrome integer.
 * 
 * An integer is a palindrome when it reads the same backward as forward.
 * For example, 121 is a palindrome while 123 is not.
 */
package Problems;

public class IsPalindrome {
    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        return (x == reverse || x == reverse / 10);
    }

    public static void main(String[] args) {
        int x = 123494321;
        System.out.println(isPalindrome(x));
    }
}
