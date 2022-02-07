/**
 * Given an integer num, return a string of its base 7 representation.
 */
package Problems;

public class Base7 {
    public static String convertToBase7(int num) {
        StringBuilder st = new StringBuilder();
        if (num == 0)
            return st.append(0).toString();
        while (num != 0) {
            int i = num % 7;
            num = num / 7;
            if (i < 0) {
                i = Math.abs(i);
                st.append(i);
                if (num == 0)
                    st.append("-");
            } else
                st.append(i);
        }
        return st.reverse().toString();
    }

    public static void main(String[] args) {
        int num = -7;
        System.out.println(convertToBase7(num));
    }
}
