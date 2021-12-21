/**
 * Given a string s consisting of some words separated by some number of spaces, 
 * return the length of the last word in the string.
 * 
 * A word is a maximal substring consisting of non-space characters only.
 */

package Problems;

public class LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        s = s.trim();
        StringBuilder result = new StringBuilder();
        int endIndex = s.length() - 1;
        while (endIndex >= 0) {
            if (s.charAt(endIndex) == ' ') {
                break;
            }
            result.insert(0, s.charAt(endIndex));
            endIndex--;
        }

        return result.length();
    }

    // better solution
    public static int lengthOfLastWordBetterSolution(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }

    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWordBetterSolution(s));
    }

}
