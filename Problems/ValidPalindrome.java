/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, 
 * it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 */
package Problems;

public class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s == " " || s.length() == 1)
            return true;

        String s2 = "";
        for (int i = 0; i < s.length(); i++) {
            if ((int) s.charAt(i) > 96 && (int) s.charAt(i) < 123) {
                s2 += s.charAt(i);
            } else if ((int) s.charAt(i) > 64 && (int) s.charAt(i) < 91) {
                s2 += s.substring(i, i + 1).toLowerCase();
            } else if ((int) s.charAt(i) > 47 && (int) s.charAt(i) < 58) {
                s2 += s.charAt(i);
            }
        }

        if (s2.length() == 1)
            return true;

        s = "";
        int n = s2.length() / 2;
        for (int i = s2.length() - 1; i >= n; i--) {
            s += s2.charAt(i);
            s2 = s2.substring(0, i);
        }

        if (s.length() != s2.length() && s2.length() > 0) {
            s = s.substring(0, s.length() - 1);
        }

        return s.equals(s2);
    }

    // it's better to compare one char by one char
    // better solution
    public static boolean betterIsPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int head = 0;
        int tail = s.length() - 1;
        char cHead, cTail;
        while (head <= tail) {
            cHead = s.charAt(head);
            cTail = s.charAt(tail);
            if (!Character.isLetterOrDigit(cHead)) {
                head++;
            } else if (!Character.isLetterOrDigit(cTail)) {
                tail--;
            } else {
                if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
                    return false;
                }
                head++;
                tail--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
        System.out.println(betterIsPalindrome(s));
    }
}
