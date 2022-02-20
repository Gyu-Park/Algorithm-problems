/**
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
 * 
 * If there are fewer than k characters left, reverse all of them. 
 * If there are less than 2k but greater than or equal to k characters, 
 * then reverse the first k characters and leave the other as original.
 */
package Problems;

public class ReverseStringII {
    public static String reverseStr(String s, int k) {
        if (s.length() == 1 || k == 1)
            return s;
        int len = s.length();
        StringBuilder st = new StringBuilder(s);
        StringBuilder reverse = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (len < k) {
                reverse.append(st.substring(i, st.length()));
                res.append(reverse.reverse());
                break;
            } else if (len < 2 * k && len >= k) {
                reverse.append(st.substring(i, i + k));
                res.append(reverse.reverse());
                res.append(st.substring(i + k, st.length()));
                break;
            } else if (len >= 2 * k) {
                reverse.append(st.substring(i, i + k));
                res.append(reverse.reverse());
                reverse.delete(0, reverse.length());
                res.append(st.substring(i + k, i + k * 2));
                i += k * 2 - 1;
                len -= 2 * k;
            }
        }
        return res.toString();
    }

    // better solution
    public static String betterReverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while (i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }

    private static void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }

    public static void main(String[] args) {
        String s = "krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc";
        int k = 20;
        System.out.println(reverseStr(s, k));
        System.out.println(betterReverseStr(s, k));
    }
}
