/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 */
package Problems;

public class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int indexCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && indexCount != i) {
                nums[indexCount++] = nums[i];
                nums[i] = 0;
            } else if (nums[i] != 0 && indexCount == i) {
                indexCount++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 3, 12 };
        moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }
}
