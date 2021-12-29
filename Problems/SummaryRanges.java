/**
 * You are given a sorted unique integer array nums.
 * 
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. 
 * That is, each element of nums is covered by exactly one of the ranges, 
 * and there is no integer x such that x is in one of the ranges but not in nums.
 * 
 * Each range [a,b] in the list should be output as:
 * -------------------
 * |"a->b" if a != b |
 * |"a" if a == b    |
 * -------------------
 */
package Problems;

import java.util.*;

public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums.length == 0)
            return list;

        StringBuilder range = new StringBuilder();
        range.append(nums[0]);

        if (nums.length == 1) {
            list.add(range.toString());
            return list;
        }

        boolean isInRange = false;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                isInRange = true;
                count++;
            } else {
                isInRange = false;
            }
            if (!isInRange) {
                if (count == 0) {
                    list.add(range.toString());
                } else {
                    range.append("->");
                    range.append(nums[i - 1]);
                    list.add(range.toString());
                    count = 0;
                }
                range.delete(0, range.length());
                range.append(nums[i]);
            }
        }

        if (count > 0) {
            range.append("->");
            range.append(nums[nums.length - 1]);
            list.add(range.toString());
        } else {
            list.add(range.toString());
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 2, 4, 5, 7 };
        List<String> result = (summaryRanges(nums));
        for (String a : result) {
            System.out.println(a);
        }
    }
}
