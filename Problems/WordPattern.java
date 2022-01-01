/**
 * Given a pattern and a string s, find if s follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 */
package Problems;

import java.util.*;

public class WordPattern {
    public static boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            st.delete(0, st.length());
            while (true) {
                if (s.length() == 0)
                    break;
                if (s.charAt(0) == ' ') {
                    s = s.substring(1, s.length());
                    break;
                }

                st.append(s.charAt(0));
                s = s.substring(1, s.length());
            }
            if (!map.containsKey(pattern.charAt(i)) && !map.containsValue(st.toString())
                    && st.toString().length() != 0) {
                map.put(pattern.charAt(i), st.toString());
            } else {
                if (map.get(pattern.charAt(i)) == null || !map.get(pattern.charAt(i)).equals(st.toString()))
                    return false;
            }
        }
        if (s.length() != 0)
            return false;

        return true;
    }

    // shorter solution
    public static boolean shorterWordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }

    public static void main(String[] args) {
        String pattern = "he";
        String s = "unit";
        System.out.println(wordPattern(pattern, s));
        System.out.println(shorterWordPattern(pattern, s));
    }
}
