/**
 * Given an integer array nums, 
 * find a contiguous non-empty subarray within the array that has the largest product, 
 * and return the product.
 * 
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * 
 * A subarray is a contiguous subsequence of the array.
 */
package Problems;

public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        int prod = 1;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            res = Math.max(prod, res);
            if (prod == 0) {
                prod = 1;
            }
        }
        prod = 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            prod = prod * nums[i];
            res = Math.max(prod, res);
            if (prod == 0) {
                prod = 1;
            }
        }
        return res;
    }

    public static int anotherMaxProduct(int[] nums) {
        int n = nums.length;
        int res = nums[0];

        for (int i = 1, max = res, min = res; i < n; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { -3, 0, 5, 6, -1, -1, 0, 1, 3, 9, 0, 2, -5, -1, -8, -9 };
        System.out.println(anotherMaxProduct(nums));
    }
}
