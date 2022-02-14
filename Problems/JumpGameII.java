/**
 * Given an array of non-negative integers nums, 
 * you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * You can assume that you can always reach the last index.
 */
package Problems;

public class JumpGameII {
    public static int jump(int[] nums) {
        if (nums[0] == 0) {
            return 0;
        }

        int lastIndex = nums.length - 1;
        int jums = 0;
        while (lastIndex > 0) {
            lastIndex = findLastNum(nums, lastIndex);
            jums++;
        }

        return jums;
    }

    private static int findLastNum(int[] nums, int lastIndex) {
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] >= lastIndex - i)
                return i;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }
}
