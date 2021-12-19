/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * If there is no common prefix, return an empty string "".
 */
package Problems;

import java.util.HashSet;
import java.util.Set;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {

        Set<Character> set = new HashSet<>();
        int indexPointer = 0;
        String prefix = "";
        for (int i = 0; i < strs.length + 1; i++) {
            try {
                if (i == strs.length)
                    i = 0;
                if (i == 0)
                    set.add(strs[i].charAt(indexPointer));
                if (!set.contains(strs[i].charAt(indexPointer))) {
                    return prefix;
                }
                if (i == strs.length - 1) {
                    prefix += strs[i].charAt(indexPointer);
                    set.clear();
                    indexPointer++;
                }
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        return prefix;
    }

    // Better Solution
    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0)
            return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
        return pre;
    }

    public static void main(String[] args) {
        String[] strs = { "flower", "flow", "flight" };

        System.out.println(longestCommonPrefix(strs));
        System.out.println(longestCommonPrefix2(strs));
    }

}
