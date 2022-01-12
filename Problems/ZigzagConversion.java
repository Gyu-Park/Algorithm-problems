/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a number of rows:
 * 
 * string convert(string s, int numRows);
 */
package Problems;

public class ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        String res = "";
        for (int i = 0; i < numRows; i++) {
            int pointer = i;
            boolean downUp = true;
            while (pointer < s.length()) {
                res += s.charAt(pointer);
                if (i == 0)
                    pointer += numRows + (numRows - 2);
                else if (i > 0 && i < numRows - 1 && downUp) {
                    pointer += (numRows - (i + 1)) * 2;
                    downUp = false;
                } else if (i > 0 && i < numRows - 1 && !downUp) {
                    pointer += i * 2;
                    downUp = true;
                } else
                    pointer += i * 2;
            }
        }
        return res;
    }

    // abother solution from another person but slower
    public String anotherConvert(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++)
            sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows - 2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(convert(s, numRows));
    }
}
