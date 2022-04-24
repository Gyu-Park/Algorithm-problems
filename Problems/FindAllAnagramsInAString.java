/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. 
 * You may return the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
 * typically using all the original letters exactly once.
 */
package Problems;

import java.util.*;

public class FindAllAnagramsInAString {

    // a solution with using two hashmaps
    // but it is not readable
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (p.length() > s.length())
            return res;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int pLen = p.length() - 1;
        int count = 0;
        int sLen = s.length() - 1;
        Map<Character, Integer> newMap = new HashMap<>(map);
        for (int i = 0; i <= sLen; i++) {
            char c = s.charAt(i);
            if (newMap.containsKey(c)) {
                count++;
                if (newMap.get(c) > 0) {
                    newMap.put(c, newMap.get(c) - 1);
                } else {
                    int index = i - count + 1;
                    for (int j = index; j <= sLen; j++) {
                        count--;
                        if (s.charAt(j) == c) {
                            break;
                        } else {
                            newMap.put(s.charAt(j), newMap.get(s.charAt(j)) + 1);
                        }
                    }
                }
                if (count == pLen + 1) {
                    res.add(i - pLen);
                    count--;
                    newMap.put(s.charAt(i - pLen), newMap.get(s.charAt(i - pLen)) + 1);
                }
            } else {
                count = 0;
                newMap = new HashMap<>(map);
            }

        }
        return res;
    }

    // another solution with the same algorithm as the first solution but faster
    // Sliding Window Algorithm
    public List<Integer> anotherFindAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length())
            return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0)
                    counter--;
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }

    // another solution without a hashmap.
    // easy to read
    public List<Integer> anotherFindAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<Integer>();
        if (p.length() > s.length())
            return res;

        int n = s.length();
        int m = p.length();
        int[] count = freq(p);

        int[] currentCount = freq(s.substring(0, m));

        if (areSame(count, currentCount))
            res.add(0);

        int i;
        for (i = m; i < n; i++) {
            currentCount[s.charAt(i - m) - 'a']--;
            currentCount[s.charAt(i) - 'a']++;
            if (areSame(count, currentCount)) {
                res.add(i - m + 1);
            }
        }
        return res;
    }

    private boolean areSame(int[] x, int[] y) {
        for (int i = 0; i < 26; i++) {
            if (x[i] != y[i])
                return false;
        }

        return true;
    }

    private int[] freq(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "bcdebcdekbbccddeekbccdddde";
        String p = "bcdebcde";
        List<Integer> res = findAnagrams(s, p);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
