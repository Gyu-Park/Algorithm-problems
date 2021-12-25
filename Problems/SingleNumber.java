/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * 
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 */
package Problems;

import java.util.*;

public class SingleNumber {
    public static int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                set.remove(i);
            } else {
                set.add(i);
            }
        }

        Iterator<Integer> iter = set.iterator();
        return iter.next();
    }

    /**
     * using bitwise XOR
     * XOR operator ^ functions as like this
     * Binary digits for 4 and 1
     * 4 = 100
     * 1 = 1
     * a ^= 4 = binary digit 100
     * a ^= 1 = binary digit 101
     * a ^= 1 = binary digit 100
     */
    public static int bitwiseXORSingleNumber(int[] nums) {
        int ans = 0;

        int len = nums.length;
        for (int i = 0; i != len; i++)
            ans ^= nums[i];

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 1, 2, 1, 2 };
        System.out.println(singleNumber(nums));
        System.out.println(bitwiseXORSingleNumber(nums));
    }
}
