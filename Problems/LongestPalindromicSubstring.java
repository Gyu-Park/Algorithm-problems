/**
 * Given a string s, return the longest palindromic substring in s.
 */
package Problems;

public class LongestPalindromicSubstring {
    static private int lo, maxLen;

    public static String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;
        if (s.length() == 2 && s.charAt(0) == s.charAt(1))
            return s;
        if (s.length() == 2 && s.charAt(0) != s.charAt(1))
            return s.substring(0, 1);

        int indexCount = 1;
        int leftPoint;
        int rightPoint;
        String res = "";

        while (indexCount < s.length() - 1) {
            leftPoint = indexCount - 1;
            rightPoint = indexCount + 1;

            while (s.charAt(leftPoint) == s.charAt(rightPoint)) {
                if (leftPoint == 0 || rightPoint == s.length() - 1) {
                    res = res.length() < s.substring(leftPoint, rightPoint + 1).length()
                            ? s.substring(leftPoint, rightPoint + 1)
                            : res;
                    break;
                }
                leftPoint--;
                rightPoint++;
            }

            if (s.charAt(leftPoint) != s.charAt(rightPoint)) {
                res = res.length() < s.substring(leftPoint + 1, rightPoint).length()
                        ? s.substring(leftPoint + 1, rightPoint)
                        : res;
            }

            indexCount++;
        }

        indexCount = 1;

        while (indexCount < s.length()) {
            leftPoint = indexCount - 1;
            rightPoint = indexCount;

            if (s.charAt(leftPoint) == s.charAt(rightPoint)) {
                while (true) {
                    if (s.charAt(leftPoint) == s.charAt(rightPoint)) {
                        if (leftPoint == 0 || rightPoint == s.length() - 1) {
                            res = res.length() < s.substring(leftPoint, rightPoint + 1).length()
                                    ? s.substring(leftPoint, rightPoint + 1)
                                    : res;
                            break;
                        }
                        leftPoint--;
                        rightPoint++;
                    } else {
                        res = res.length() < s.substring(leftPoint + 1, rightPoint).length()
                                ? s.substring(leftPoint + 1, rightPoint)
                                : res;
                        break;
                    }
                }
            }
            indexCount++;
        }
        return res;
    }

    // better solution
    public static String anotherLongestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i); // for odd length
            extendPalindrome(s, i, i + 1); // for even length
        }
        return s.substring(lo, lo + maxLen);
    }

    private static void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            lo = left + 1;
            maxLen = right - left - 1;
        }
    }

    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
        System.out.println(anotherLongestPalindrome(s));
    }
}
