/**
 * Given a string s which consists of lowercase or uppercase letters, 
 * return the length of the longest palindrome that can be built with those letters.
 * 
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 */
package Problems;

public class LongestPalindrome {
    public static int longestPalindrome(String s) {
        int[] storage = new int[52];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) - 'a' < 0) {
                storage[s.charAt(i) - 'A']++;
            } else {
                storage[s.charAt(i) - 71]++;
            }
        }

        boolean odd = false;
        int res = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == 1 && !odd) {
                odd = true;
                res += storage[i];
            } else if (storage[i] >= 3 && storage[i] % 2 == 1 && !odd) {
                odd = true;
                res += storage[i];
            } else if (storage[i] >= 3 && storage[i] % 2 == 1 && odd) {
                res += storage[i] - 1;
            } else if (storage[i] % 2 == 0) {
                res += storage[i];
            }
        }
        return res;
    }

    // another ssolution
    public static int anotherLongestPalindrome(String s) {
        int[] chars = new int[128];
        char[] t = s.toCharArray();
        for (char c : t) {
            chars[c]++;
        }
        int single = 0;
        for (int n : chars) {
            if (n % 2 != 0) {
                single++;
            }
        }
        return single > 1 ? t.length - single + 1 : t.length;
    }

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
        System.out.println(anotherLongestPalindrome(s));
    }
}
