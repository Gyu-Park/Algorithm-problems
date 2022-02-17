/**
 * Given two strings a and b, return the length of the longest uncommon subsequence between a and b. 
 * If the longest uncommon subsequence does not exist, return -1.
 * 
 * An uncommon subsequence between two strings is a string that is a subsequence of one but not the other.
 * 
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 * 
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". 
 * Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 */
package Problems;

public class LongestUncommonSubsequenceI {
    public static int findLUSlength(String a, String b) {
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int indexCount = 0;
        for (int i = 0; i < b.length(); i++) {
            if (a.charAt(indexCount) == b.charAt(i))
                indexCount++;
            if (indexCount == a.length() && i < b.length() - 1)
                return b.length();
        }
        return indexCount < a.length() ? b.length() : -1;
    }

    public static void main(String[] args) {
        String a = "aweffwaf";
        String b = "a";
        System.out.println(findLUSlength(a, b));
    }
}
