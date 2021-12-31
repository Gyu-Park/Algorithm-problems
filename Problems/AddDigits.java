/**
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 */
package Problems;

public class AddDigits {
    public static int addDigits(int num) {
        if (num < 10)
            return num;
        int answer = 0;
        while (true) {
            answer += num % 10;
            num = num / 10;
            if (num == 0 && answer < 10)
                break;
            if (num == 0 && answer >= 10) {
                num = answer;
                answer = 0;
            }
        }

        return answer;
    }

    /**
     * The digital root (also repeated digital sum) of a natural number in a given
     * radix is the (single digit) value
     * obtained by an iterative process of summing digits,
     * on each iteration using the result from the previous iteration to compute a
     * digit sum.
     * The process continues until a single-digit number is reached.
     * For example, in base 10, the digital root of the number 12345 is 6
     * because the sum of the digits in the number is 1 + 2 + 3 + 4 + 5 = 15,
     * then the addition process is repeated again for the resulting number 15,
     * so that the sum of 1 + 5 equals 6, which is the digital root of that number.
     * In base 10, this is equivalent to taking the remainder upon division by 9
     * (except when the digital root is 9, where the remainder upon division by 9
     * will be 0).
     */
    public static int withoutLoopAddDigits(int num) {
        return 1 + (num - 1) % 9;
    }

    public static void main(String[] args) {
        int num = 54;
        System.out.println(addDigits(num));
        System.out.println(withoutLoopAddDigits(num));
    }
}
