/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 */
package Problems;

import java.util.*;

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        if (nums.length <= k)
            k %= nums.length;
        if (nums.length == 1 || k == 0)
            return;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i + k, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if(i < k) {
                nums[i] = map.get(nums.length + i);
            } else {
                nums[i] = map.get(i);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 2 };
        int k = 3;
        rotate(nums, k);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}