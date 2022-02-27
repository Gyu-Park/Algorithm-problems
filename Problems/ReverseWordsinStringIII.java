/**
 * Given a string s, reverse the order of characters in each word within a sentence 
 * while still preserving whitespace and initial word order.
 */
package Problems;

public class ReverseWordsinStringIII {
    public static String reverseWords(String s) {
        StringBuilder st = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                st.insert(0, s.charAt(i));
            }
            if (s.charAt(i) == ' ') {
                res.append(st);
                res.append(" ");
                st.delete(0, st.length());
            } else if (i == s.length() - 1) {
                res.append(st);
            }
        }
        return res.toString();
    }

    // another solution using char array (faster)
    public static String anotherReverseWords(String s) {
        char[] array = s.toCharArray();
        int first = 0;
        int last = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1 || array[i+1] == ' ') {
                last = i;
                swap(array, first, last);
                first = i + 2;
            }
        }
        return new String(array);
    }

    private static void swap(char[] array, int first, int last) {
        while (first < last) {
            char temp = array[first];
            array[first++] = array[last];
            array[last--] = temp;
        }
    }
    
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(anotherReverseWords(s));
        System.out.println(reverseWords(s));
    }
}
