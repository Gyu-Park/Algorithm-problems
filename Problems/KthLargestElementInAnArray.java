/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 */
package Problems;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class KthLargestElementInAnArray {
    // O(nlogn) solution
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // another solution using quickselect sort algorithm - O(n)
    public static int anotherFindKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private static int quickSelect(int[] nums, int low, int high, int k) {
        int pivot = ThreadLocalRandom.current().nextInt(low, high + 1);
        swap(nums, low, pivot);
        pivot = low;

        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(nums, pivot++, j);
            }
        }
        swap(nums, pivot, high);

        int fixIndexFromHigh = high - pivot + 1;
        if (fixIndexFromHigh == k)
            return nums[pivot];
        if (fixIndexFromHigh > k)
            return quickSelect(nums, pivot + 1, high, k);
        return quickSelect(nums, low, pivot - 1, k - fixIndexFromHigh);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // another solution using a priority queue - O(n) best case / O(n^2) worst case
    public int anotherFindKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 5, 6, 3, 2, 3, 1, 2 };
        int k = 4;
        System.out.println(anotherFindKthLargest(nums, k));
    }
}
