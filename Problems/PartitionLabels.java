/**
 * You are given a string s. 
 * We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * 
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * 
 * Return a list of integers representing the size of these parts.
 */
package Problems;

import java.util.*;

public class PartitionLabels {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 1) {
            res.add(1); 
            return res;
        }

        Set<Character> set = new HashSet<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            for (int i = s.length() - 1; i >= index; i--) {
                if (set.contains(s.charAt(i))) {
                    String part = s.substring(index, i + 1);
                    addCharIntoSet(part, set, res);
                    index = i + 1;
                    break;
                }
                if (i == index) {
                    res.add(1);
                    index = i + 1;
                    break;
                }
                if (s.charAt(i) == c) {
                    String part = s.substring(index, i + 1);
                    addCharIntoSet(part, set, res);
                    index = i + 1;
                    break;
                }
            }
        }
        return res;
    }

    private static void addCharIntoSet(String s, Set<Character> set, List<Integer> res) {
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                res.set(res.size() - 1, res.get(res.size() - 1) + s.length());
                break;
            }
            if (i == s.length() - 1) {
                res.add(s.length());
            }
        }
        for (char c : s.toCharArray()) {
            set.add(c);
        }
    }

    // more concise and readable solution using an array map
    // time complexity O(n)
    // space complexity O(1)
    public static List<Integer> anotherPartitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 1) {
            res.add(1); 
            return res;
        }

        int[] map = new int[26];
        int count = 0;
        for (char c: s.toCharArray()) {
            map[c - 'a'] = count++;
        }
        
        int pre = -1;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            max = Math.max(max, map[c - 'a']);
            if (max == i) {
                res.add(max - pre);
                pre = max;
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        String s = "qiejxqfnqceocmy";
        List<Integer> res = anotherPartitionLabels(s);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
