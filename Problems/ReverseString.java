/**
 * Write a function that reverses a string. 
 * The input string is given as an array of characters s.
 * 
 * You must do this by modifying the input array in-place with O(1) extra memory.
 */
package Problems;

public class ReverseString {
    public static void reverseString(char[] s) {
        int countBackward = s.length - 1;
        char tmp;
        for (int i = 0; i < s.length; i++) {
            if (i > countBackward || i == countBackward)
                break;
            tmp = s[i];
            s[i] = s[countBackward];
            s[countBackward--] = tmp;
        }
    }

    public static void main(String[] args) {
        char[] s = { 'h', 'e', 'l', 'l', 'o' };
        reverseString(s);
        for (char ss : s) {
            System.out.println(ss);
        }
    }
}
