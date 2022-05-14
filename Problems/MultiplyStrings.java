/**
 * Given two non-negative integers num1 and num2 represented as strings, 
 * return the product of num1 and num2, also represented as a string.
 * 
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
package Problems;

public class MultiplyStrings {
    // time complexity O(n * m)
    // space complexity O(n + m)
    public static String multiply(String num1, String num2) {
        if (num1.length() == 1 && num1.charAt(0) == '0')
            return "0";
        if (num2.length() == 1 && num2.charAt(0) == '0')
            return "0";
        char[] arary = new char[num1.length() + num2.length()];
        int num1Len = num1.length() - 1;
        int num2Len = num2.length() - 1;
        int index = 0;

        for (int i = num1Len; i >= 0; i--) {
            int arrayIndex = index++;
            int storage = 0;
            int numOne = num1.charAt(i) - '0';
            for (int j = num2Len; j >= 0; j--) {
                int numTwo = num2.charAt(j) - '0';
                int product = numOne * numTwo;
                product += storage;
                if (arary[arrayIndex] >= '0')
                    product += arary[arrayIndex] - '0';
                storage = product / 10;
                product -= storage * 10;
                arary[arrayIndex++] = (char) (product + '0');
                if (j == 0 && storage > 0)
                    arary[arrayIndex] = (char) (storage + '0');
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = arary.length - 1; i >= 0; i--) {
            if (arary[i] >= '0')
                res.append(arary[i]);
        }

        return res.toString();
    }

    // shorter solution
    public String anotherMultiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }

        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : products)
            sb.append(num);

        while (sb.length() != 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(multiply(num1, num2));
    }
}
