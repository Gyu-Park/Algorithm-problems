/**
 * Given an integer array nums of length n and an integer target, 
 * find three integers in nums such that the sum is closest to target.
 * 
 * Return the sum of the three integers.
 * 
 * You may assume that each input would have exactly one solution.
 */
package Problems;

import java.util.Arrays;

public class ThreeSumClosest {
    // a solution with using two pointers
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int pre = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int secondPointer = i + 1;
            int endPointer = nums.length - 1;
            while (secondPointer != endPointer) {
                int sum = nums[i] + nums[secondPointer] + nums[endPointer];
                if (sum > target) {
                    endPointer--;
                    if (pre < sum - target) {
                        continue;
                    }
                    pre = sum - target;
                } else if (sum < target) {
                    secondPointer++;
                    if (pre < target - sum) {
                        continue;
                    }
                    pre = target - sum;
                } else if (sum == target) {
                    return sum;
                }
                res = sum;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 2, 4, 8, 16, 32, 64, 128 };
        int target = 82;
        System.out.println(threeSumClosest(nums, target));
    }
}
