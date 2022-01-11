/**
 * Given a string s, return the number of segments in the string.
 * 
 * A segment is defined to be a contiguous sequence of non-space characters.
 */
package Problems;

public class NumberOfSegmentsinAString {
    public static int countSegments(String s) {
        int res = 0;
        s = s.trim();
        if (s.length() == 0)
            return res;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && s.charAt(i + 1) != ' ') {
                res++;
            }
        }
        return ++res;
    }

    public static void main(String[] args) {
        String s = ", , , ,        a, eaefa";
        System.out.println(countSegments(s));
    }
}
