/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * 
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * 
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
package Problems;

public class InterleavingString {
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        
        int s1Index = 0;
        int s2Index = 0;
        for (int i = 0; i < s3.length(); i++) {
            if (s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(i) 
                && s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(i)) {
                if (!isInterleave(s1.substring(s1Index + 1), s2.substring(s2Index), s3.substring(i + 1)))
                    s2Index++;
                else
                    return true;
            } else if (s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(i)) {
                s1Index++;
            } else if (s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(i)) {
                s2Index++;
            } else {
                return false;
            }
        }

        return true;
    }
    
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbcacc";
        System.out.println(isInterleave(s1, s2, s3));
    }
}
