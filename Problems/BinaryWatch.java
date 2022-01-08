/**
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), 
 * and the 6 LEDs on the bottom represent the minutes (0-59). 
 * Each LED represents a zero or one, with the least significant bit on the right.
 * 
 * For example, the below binary watch reads "4:51".
 * 
 * Given an integer turnedOn which represents the number of LEDs that are currently on, 
 * return all possible times the watch could represent. You may return the answer in any order.
 * 
 * The hour must not contain a leading zero.
 * 
 * For example, "01:00" is not valid. It should be "1:00".
 * The minute must be consist of two digits and may contain a leading zero.
 * 
 * For example, "10:2" is not valid. It should be "10:02".
 */
package Problems;

import java.util.*;

public class BinaryWatch {
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if (turnedOn > 8)
            return res;
        if (turnedOn == 0) {
            res.add("0:00");
            return res;
        }
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                if (Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {
                    res.add(String.format("%d:%02d", hour, minute));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int turnedOn = 1;
        List<String> res = readBinaryWatch(turnedOn);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
