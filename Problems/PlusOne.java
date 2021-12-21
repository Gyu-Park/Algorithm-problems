/**
 * You are given a large integer represented as an integer array digits, 
 * where each digits[i] is the ith digit of the integer. 
 * The digits are ordered from most significant to least significant in left-to-right order. 
 * The large integer does not contain any leading 0's.
 * 
 * Increment the large integer by one and return the resulting array of digits.
 */
package Problems;

public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int endIndex = digits.length - 1;
        if (digits[endIndex] + 1 < 10) {
            digits[endIndex] = digits[endIndex] + 1;
        } else {
            while (true) {
                try {
                    digits[endIndex] = 0;
                    endIndex--;
                    digits[endIndex]++;
                    if (digits[endIndex] < 10) {
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    digits = new int[digits.length + 1];
                    digits[0] = 1;
                    return digits;
                }
            }
        }
        return digits;
    }

    // Same algoritm but Shorter solution
    public static int[] plusOneShorterSolution(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;

        return newNumber;
    }

    public static void main(String[] args) {
        int[] digits = { 8 };
        int[] result = plusOne(digits);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
