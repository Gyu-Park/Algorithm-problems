/**
 * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. 
 * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
 * 
 * We want to reformat the string s such that each group contains exactly k characters, 
 * except for the first group, which could be shorter than k but still must contain at least one character. 
 * Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
 * 
 * Return the reformatted license key.
 */
package Problems;

public class LicenseKeyFormatting {
    // it was much slower because of sb.insert().
    // After changing it to sb.append(), its runtime significantly decreased.
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                if (count == k) {
                    sb.insert(0, '-');
                    count = 0;
                }
                if (Character.isLowerCase(s.charAt(i))) {
                    sb.insert(0, Character.toUpperCase(s.charAt(i)));
                } else {
                    sb.insert(0, s.charAt(i));
                }
                count++;
            }
        }
        return sb.toString();
    }

    // better solution
    public String anotherLicenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '-')
                continue;
            if (count == k) {
                sb.append('-');
                count = 0;
            }
            sb.append(Character.toUpperCase(c));
            count++;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "--a-a-a-a--";
        int k = 2;
        System.out.println(licenseKeyFormatting(s, k));
    }
}
