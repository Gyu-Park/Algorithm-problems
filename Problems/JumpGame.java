/**
 * You are given an integer array nums. You are initially positioned at the array's first index, 
 * and each element in the array represents your maximum jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 */
package Problems;

public class JumpGame {
    public static boolean canJump(int[] nums) {
        if (nums.length == 1)
            return true;
        int numsLen = nums.length - 1;
        for (int i = numsLen - 1; i >= 0; i--) {
            if (nums[i] >= numsLen - i) {
                numsLen = i;
            }
        }
        return numsLen == 0;
    }

    // another solution
    public static boolean anotherCanJump(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            if (i > max)
                return false;
            max = Math.max(nums[i] + i, max);
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
        System.out.println(anotherCanJump(nums));
    }
}
