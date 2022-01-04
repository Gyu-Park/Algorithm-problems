/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * 
 * Follow up: Do not use any built-in library function such as sqrt.
 */
package Problems;

public class ValidPerfectSquare {
    public static boolean isPerfectSquare(int num) {
        if (num == 1)
            return true;
        int start = 0;
        int end = num;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid <= num / mid && (mid + 1) > num / (mid + 1)) {
                start = mid;
                break;
            } else if (mid > num / mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (start * start == num)
            return true;
        else
            return false;
    }

    // another solution (Newton Method)
    public static boolean anotherIsPerfectSquare(int num) {
        int x = num;
        while (x > num / x) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }

    public static void main(String[] args) {
        int num = 100;
        System.out.println(isPerfectSquare(num));
        System.out.println(anotherIsPerfectSquare(num));
    }
}
