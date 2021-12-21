/**
 * Given two binary strings a and b, return their sum as a binary string.
 */
package Problems;

public class AddBinary {
    public static String addBinary(String a, String b) {
        int aEndIndex = a.length() - 1;
        int bEndIndex = b.length() - 1;
        int carry = 0;
        int tempSum = 0;
        StringBuilder sum = new StringBuilder();

        while (aEndIndex >= 0 || bEndIndex >= 0) {
            if (aEndIndex >= 0 && bEndIndex >= 0)
                tempSum = Integer.parseInt(a.substring(aEndIndex, aEndIndex + 1))
                        + Integer.parseInt(b.substring(bEndIndex, bEndIndex + 1)) + carry;
            else if (aEndIndex >= 0 && bEndIndex < 0)
                tempSum = Integer.parseInt(a.substring(aEndIndex, aEndIndex + 1)) + carry;
            else if (aEndIndex < 0 && bEndIndex >= 0)
                tempSum = Integer.parseInt(b.substring(bEndIndex, bEndIndex + 1)) + carry;

            aEndIndex--;
            bEndIndex--;
            carry = 0;
            if (tempSum == 3) {
                sum.append("1");
                carry++;
            } else if (tempSum == 2) {
                sum.append("0");
                carry++;
            } else if (tempSum == 1) {
                sum.append("1");
            } else {
                sum.append("0");
            }
        }

        if (tempSum == 2 || tempSum == 3)
            sum.append("1");

        return sum.reverse().toString();
    }

    // Better Solution
    public static String addBinaryBetterSolution(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0)
                sum += b.charAt(j--) - '0'; // subtract '0' to get the int value of the char from the ascii
            if (i >= 0)
                sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0)
            sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(addBinary(a, b));
        System.out.println(addBinaryBetterSolution(a, b));
    }
}
