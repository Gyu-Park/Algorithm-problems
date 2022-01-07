/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * 
 * A subsequence of a string is a new string that is formed from the original string 
 * by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 *  (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 */
package Problems;

public class IsSubsequence {
    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        if (t.length() < s.length())
            return false;

        int indexCount = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(indexCount) == t.charAt(i))
                indexCount++;

            if (indexCount == s.length())
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
