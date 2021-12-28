/**
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while preserving the order of characters. 
 * No two characters may map to the same character, but a character may map to itself.
 */
package Problems;

import java.util.*;

public class IsomorphicStrings {
    // solution using Map
    public static boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i)) && !map.containsValue(t.charAt(i))) {
                map.put(s.charAt(i), t.charAt(i));
            } else if (map.containsKey(s.charAt(i)) && map.containsValue(t.charAt(i))) {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    // better solution
    public static boolean betterIsIsomorphic(String s, String t) {
        String[] storage1 = new String[128];
        String[] storage2 = new String[128];
        for (int i = 0; i < s.length(); i++) {
            if (storage1[s.charAt(i)] == null) {
                storage1[s.charAt(i)] = t.substring(i, i + 1);
            } else if (!storage1[s.charAt(i)].equals(t.substring(i, i + 1))) {
                return false;
            }

            if (storage2[t.charAt(i)] == null) {
                storage2[t.charAt(i)] = s.substring(i, i + 1);
            } else if (!storage2[t.charAt(i)].equals(s.substring(i, i + 1))) {
                return false;
            }
        }

        return true;
    }

    // best Solution
    public static boolean bestIsIsomorphic(String sString, String tString) {
        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;

        char[] sm = new char[128];
        char[] tm = new char[128];

        for (int i = 0; i < length; i++) {
            char sc = s[i];
            char tc = t[i];
            if (sm[sc] == 0 && tm[tc] == 0) {
                sm[sc] = tc;
                tm[tc] = sc;
            } else {
                if (sm[sc] != tc || tm[tc] != sc) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "paper";
        String t = "title";
        System.out.println(isIsomorphic(s, t));
        System.out.println(betterIsIsomorphic(s, t));
        System.out.println(bestIsIsomorphic(s, t));
    }
}
