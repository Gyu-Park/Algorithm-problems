/**
 * Given an integer num, return a string representing its hexadecimal representation. 
 * For negative integers, two’s complement method is used.
 * 
 * All the letters in the answer string should be lowercase characters, 
 * and there should not be any leading zeros in the answer except for the zero itself.
 * 
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 */
package Problems;

import java.util.*;

public class ConvertNumberToHexadecimal {
    public static String toHex(int num) {
        if (num == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        Map<Integer, String> hexMap = new HashMap<>();
        int remain = 0;
        createMap(hexMap);

        while (num != 0) {
            remain = num % 16;
            if (remain >= 0) {
                res.insert(0, hexMap.get(remain));
            } else {
                res.insert(0, hexMap.get(16 + remain));
            }
            num >>>= 4;
        }
        return res.toString();
    }

    // a little bit faster solution
    public static String anotherToHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuilder strBuilder = new StringBuilder();
        int count = 0;
        while (count < 8 && num != 0) {
            strBuilder.append(hex[num & 15]);
            num = num >> 4;
            count++;
        }
        return strBuilder.reverse().toString();
    }

    private static void createMap(Map<Integer, String> hexMap) {
        hexMap.put(0, "0");
        hexMap.put(1, "1");
        hexMap.put(2, "2");
        hexMap.put(3, "3");
        hexMap.put(4, "4");
        hexMap.put(5, "5");
        hexMap.put(6, "6");
        hexMap.put(7, "7");
        hexMap.put(8, "8");
        hexMap.put(9, "9");
        hexMap.put(10, "a");
        hexMap.put(11, "b");
        hexMap.put(12, "c");
        hexMap.put(13, "d");
        hexMap.put(14, "e");
        hexMap.put(15, "f");
    }

    public static void main(String[] args) {
        int num = -100000;
        System.out.println(toHex(num));
        System.out.println(anotherToHex(num));
    }
}
