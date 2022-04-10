/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
package Problems;

import java.util.Arrays;

public class KthLargestElementInAnArray {
    // O(nlogn) solution
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }
}
