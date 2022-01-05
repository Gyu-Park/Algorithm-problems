/**
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
 * 
 * Each letter in magazine can only be used once in ransomNote.
 */
package Problems;

import java.util.*;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length())
            return false;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i), 0) + 1);
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (map.containsKey(ransomNote.charAt(i)) && map.get(ransomNote.charAt(i)) > 0) {
                map.replace(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) - 1);
            } else {
                return false;
            }
        }

        return true;
    }

    // faster solution
    public static boolean anotherCanConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length())
            return false;

        int[] array = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            array[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (--array[ransomNote.charAt(i) - 'a'] < 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "aa";
        String magazine = "aab";
        System.out.println(canConstruct(ransomNote, magazine));
        System.out.println(anotherCanConstruct(ransomNote, magazine));
    }
}
