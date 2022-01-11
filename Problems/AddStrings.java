/**
 * Given two non-negative integers, num1 and num2 represented as string, 
 * return the sum of num1 and num2 as a string.
 * 
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
 * You must also not convert the inputs to integers directly.
 */
package Problems;

public class AddStrings {
    public static String addStrings(String num1, String num2) {
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder st = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = 0;
            int n2 = 0;
            if (i >= 0)
                n1 = num1.charAt(i) - '0';
            if (j >= 0)
                n2 = num2.charAt(j) - '0';
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            st.append(sum % 10);
            i--;
            j--;
        }

        if (carry != 0)
            st.append(carry);

        return st.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "12";
        String num2 = "100";
        System.out.println(addStrings(num1, num2));
    }
}
