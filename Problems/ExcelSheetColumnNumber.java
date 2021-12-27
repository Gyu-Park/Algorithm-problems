/**
 * Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * For example:
 * 
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28 
 * ...
 */
package Problems;

public class ExcelSheetColumnNumber {
    public static int titleToNumber(String columnTitle) {
        int digitPlace = 0;
        int result = 0;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            if (digitPlace == 0) {
                result += columnTitle.charAt(i) + 1 - 'A';
                digitPlace++;
            } else {
                result += Math.pow(26, digitPlace) * (columnTitle.charAt(i) + 1 - 'A');
                digitPlace++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String columnTitle = "ZY";
        System.out.println(titleToNumber(columnTitle));
    }
}
