/**
 * Given an array nums of size n, return the majority element.
 * 
 * The majority element is the element that appears more than ⌊n / 2⌋ times. 
 * You may assume that the majority element always exists in the array.
 */
package Problems;

import java.util.*;

public class MajorityElement {
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            if (map.get(nums[i]) > nums.length / 2) {
                result = nums[i];
                break;
            }
        }

        return result;
    }

    public static int betterMajorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count++;
                major = nums[i];
            } else if (major == nums[i]) {
                count++;
            } else
                count--;

        }
        return major;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(majorityElement(nums));
        System.out.println(betterMajorityElement(nums));
    }
}
