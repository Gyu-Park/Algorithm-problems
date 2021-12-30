/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 */
package Problems;

import java.util.Arrays;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            array[s.charAt(i) - 'a']++;
            array[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0)
                return false;
        }

        return true;
    }

    public static boolean anotherIsAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] ss = new char[s.length()];
        char[] tt = new char[t.length()];
        for (int i = 0; i < s.length(); i++) {
            ss[i] = s.charAt(i);
            tt[i] = t.charAt(i);
        }

        Arrays.sort(ss);
        Arrays.sort(tt);

        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != tt[i])
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(anotherIsAnagram(s, t));
    }
}
