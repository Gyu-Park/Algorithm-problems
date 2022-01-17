/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 */
package Problems;

public class RepeatedSubstringPattern {
    public static boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                String pattern = s.substring(0, i);
                int patternLen = pattern.length();
                int j = (len / i) - 1;
                int count = 0;
                boolean repeated = false;
                while (j > count) {
                    if (pattern.equals(s.substring(i + patternLen * count, i + patternLen * (count + 1))))
                        repeated = true;
                    else {
                        repeated = false;
                        break;
                    }
                    count++;
                }
                if (repeated)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(repeatedSubstringPattern(s));
    }
}
