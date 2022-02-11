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
        int tracking = 0;
        int a = 1;
    }

    private static int findLastNum(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] == nums.length - (i + 1))
                return i;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int n = 43261596;
        System.out.println(jump(n));
    }
}
