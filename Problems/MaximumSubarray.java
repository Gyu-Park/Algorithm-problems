/**
 * Given an integer array nums, 
 * find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * 
 * A subarray is a contiguous part of an array.
 */
package Problems;

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        // Kadane's Algorithm
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0)
                sum = 0;
        }
        return max;
    }

    // Divide and conquer approch
    public static int divideAndConquer(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static int helper(int nums[], int start, int end) {

        if (start == end)
            return nums[start];

        int mid = (start + end) / 2;
        int sum = 0, leftMax = Integer.MIN_VALUE;

        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            if (sum > leftMax) {
                leftMax = sum;
            }
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            if (sum > rightMax) {
                rightMax = sum;
            }
        }

        int maxLeftRight = Math.max(helper(nums, start, mid),
                helper(nums, mid + 1, end));
        return Math.max(maxLeftRight, leftMax + rightMax);
    }

    public static void main(String[] args) {
        int[] nums = { -1, -2, -3, -5, -8, };
        System.out.println(maxSubArray(nums));
        System.out.println(divideAndConquer(nums));
    }
}
