/**
 * Given a string s, reverse only all the vowels in the string and return it.
 * 
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 */
package Problems;

import java.util.*;

public class ReverseVowelsOfString {
    public static String reverseVowels(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int countBackward = s.length() - 1;
        int countForward = 0;
        char tmp;
        char[] array = s.toCharArray();
        while (countForward < countBackward) {

            while (countForward < countBackward && !set.contains(array[countForward])) {
                countForward++;
            }

            while (countForward < countBackward && !set.contains(array[countBackward])) {
                countBackward--;
            }

            tmp = array[countForward];
            array[countForward++] = array[countBackward];
            array[countBackward--] = tmp;
        }
        return new String(array);
    }

    // faster solution
    public static String fasterReverseVowels(String s) {
        if (s.length() == 0)
            return s;
        char[] array = s.toCharArray();
        int countForward = 0;
        int countBackward = array.length - 1;
        while (countForward < countBackward) {
            if (!isVowel(array[countForward]) && !isVowel(array[countBackward])) {
                countForward++;
                countBackward--;
            } else if (!isVowel(array[countForward])) {
                countForward++;
            } else if (!isVowel(array[countBackward])) {
                countBackward--;
            } else {
                char temp = array[countForward];
                array[countForward] = array[countBackward];
                array[countBackward] = temp;
                countForward++;
                countBackward--;
            }
        }
        return new String(array);
    }

    // switch is faster than "return ch = 'a' || ch = 'e'..."
    private static boolean isVowel(char ch) {
        switch (ch) {
            case ('a'):
                return true;
            case ('e'):
                return true;
            case ('i'):
                return true;
            case ('o'):
                return true;
            case ('u'):
                return true;
            case ('A'):
                return true;
            case ('E'):
                return true;
            case ('I'):
                return true;
            case ('O'):
                return true;
            case ('U'):
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        String s = "hello";
        System.out.println(reverseVowels(s));
        System.out.println(fasterReverseVowels(s));
    }
}
