/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
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

import java.util.*;

public class ExcelSheetColumnTitle {
    static final int NUMBER_OF_ALPHABET = 26;

    public static String convertToTitle(int columnNumber) {
        Map<Integer, Character> map = new HashMap<>();
        map = createMap(map);

        StringBuilder result = new StringBuilder();
        int lastDigit = 0;
        if (columnNumber <= NUMBER_OF_ALPHABET) {
            result.append(map.get(columnNumber));
            return result.toString();
        }

        while (true) {
            if (columnNumber == 0) {
                break;
            }
            lastDigit = columnNumber % NUMBER_OF_ALPHABET;
            if (lastDigit == 0 && columnNumber / NUMBER_OF_ALPHABET <= 26) {
                result.insert(0, map.get(lastDigit));
                columnNumber = columnNumber - NUMBER_OF_ALPHABET;
            } else {
                result.insert(0, map.get(lastDigit));
                if (lastDigit == 0) {
                    columnNumber = columnNumber - NUMBER_OF_ALPHABET;
                }
                columnNumber = columnNumber - lastDigit;
            }

            columnNumber = columnNumber / NUMBER_OF_ALPHABET;
        }

        return result.toString();
    }

    // the shortest version but slower.
    public static String shorterConvertToTitle(int columnNumber) {
        return columnNumber == 0 ? "" : convertToTitle(--columnNumber / 26) + (char) ('A' + (columnNumber % 26));
    }

    public static String betterConvertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            result.insert(0, (char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return result.toString();
    }

    private static Map<Integer, Character> createMap(Map<Integer, Character> map) {
        map.put(0, 'Z');
        map.put(1, 'A');
        map.put(2, 'B');
        map.put(3, 'C');
        map.put(4, 'D');
        map.put(5, 'E');
        map.put(6, 'F');
        map.put(7, 'G');
        map.put(8, 'H');
        map.put(9, 'I');
        map.put(10, 'J');
        map.put(11, 'K');
        map.put(12, 'L');
        map.put(13, 'M');
        map.put(14, 'N');
        map.put(15, 'O');
        map.put(16, 'P');
        map.put(17, 'Q');
        map.put(18, 'R');
        map.put(19, 'S');
        map.put(20, 'T');
        map.put(21, 'U');
        map.put(22, 'V');
        map.put(23, 'W');
        map.put(24, 'X');
        map.put(25, 'Y');
        map.put(26, 'Z');
        return map;
    }

    public static void main(String[] args) {
        int columnNumber = 702;
        System.out.println(convertToTitle(columnNumber));
        System.out.println(shorterConvertToTitle(columnNumber));
        System.out.println(betterConvertToTitle(columnNumber));
    }
}
