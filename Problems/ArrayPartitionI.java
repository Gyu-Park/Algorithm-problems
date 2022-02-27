/**
 * Given an integer array nums of 2n integers, 
 * group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) 
 * such that the sum of min(ai, bi) for all i is maximized. 
 * Return the maximized sum.
 */
package Problems;

import java.util.Arrays;

public class ArrayPartitionI {
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 6, 2, 6, 5, 1, 2 };
        System.out.println(arrayPairSum(nums));
    }
}
