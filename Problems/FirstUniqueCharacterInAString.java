package Problems;

import java.util.*;

public class FirstUniqueCharacterInAString {
    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), -1);
            } else {
                map.put(s.charAt(i), i);
            }
        }

        if (map.size() == 0)
            return -1;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) != -1)
                return map.get(s.charAt(i));
        }

        return -1;
    }

    // better O(n) solution
    public static int anotherFirstUniqChar(String s) {
        int[] charCount = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars)
            charCount[c - 'a']++;
        for (int i = 0; i < chars.length; i++)
            if (charCount[chars[i] - 'a'] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
        System.out.println(anotherFirstUniqChar(s));
    }
}
