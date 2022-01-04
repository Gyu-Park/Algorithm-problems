/**
 * We are playing the Guess Game. The game is as follows:
 * 
 * I pick a number from 1 to n. You have to guess which number I picked.
 * 
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * 
 * You call a pre-defined API int guess(int num), which returns 3 possible results:
 * 
 * -1: The number I picked is lower than your guess (i.e. pick < num).
 * 1: The number I picked is higher than your guess (i.e. pick > num).
 * 0: The number I picked is equal to your guess (i.e. pick == num).
 * Return the number that I picked.
 */
package Problems;

public class GuessNumberHigherOrLower {
    static final int PICK = 6;

    // 2^31 = 2147483648
    // int max value = 2147483647
    public static int guessNumber(int n) {
        if (guess(n) == 0)
            return n;
        else if (guess(n) == 1) {
            n = binarySearch(n, Integer.MAX_VALUE);
        } else {
            n = binarySearch(0, n);
        }

        return n;
    }

    private static int binarySearch(int start, int end) {
        int mid = start + (end - start) / 2;
        if (guess(mid) == 0)
            return mid;
        else if (guess(mid) == 1) {
            return binarySearch(mid, end);
        } else {
            return binarySearch(start, mid);
        }
    }

    // non recursive solution using binary search
    public static int anotherGuessNumber(int n) {
        int start = 0;
        int end = Integer.MAX_VALUE;
        while (guess(n) != 0) {
            if (guess(n) == 1) {
                start = n;
                n = start + (end - start) / 2;
            } else if (guess(n) == -1) {
                end = n;
                n = start + (end - start) / 2;
            }
        }

        return n;
    }

    private static int guess(int num) {
        if (num > PICK)
            return -1;
        else if (num < PICK)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(guessNumber(n));
        System.out.println(anotherGuessNumber(n));
    }
}
