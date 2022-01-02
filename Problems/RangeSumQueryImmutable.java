/**
 * Given an integer array nums, handle multiple queries of the following type:
 * 
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * 
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums 
 * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 */
package Problems;

public class RangeSumQueryImmutable {
    private int[] nums;

    public RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0)
            return this.nums[right];

        return this.nums[right] - this.nums[left - 1];
    }

    public static void main(String[] args) {
        int[] nums = { -2, 0, 3, -5, 2, -1 };
        RangeSumQueryImmutable obj = new RangeSumQueryImmutable(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }
}
