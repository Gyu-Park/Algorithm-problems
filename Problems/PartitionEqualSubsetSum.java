/**
 * Given a non-empty array nums containing only positive integers, 
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
package Problems;

import java.util.*;

public class PartitionEqualSubsetSum {
    // a solution using set
    // time complexity O(n * target)
    // space complexity O(target * 2)
    public static boolean canPartition(int[] nums) {
        if (nums.length == 1)
            return false;
        if (nums.length == 2)
            return nums[0] == nums[1];

        int sum = Arrays.stream(nums).sum();
        int target = sum / 2;
        if (sum != target * 2)
            return false;

        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int num : nums) {
            Set<Integer> copySet = new HashSet<>(set);
            for (int k : copySet) {
                int addNum = num + k;
                if (addNum == target)
                    return true;
                set.add(addNum);
            }
        }

        return false;
    }

    // a solution using dp array
    // time complexity O(n * target)
    // space complexity O(target + 1)
    public static boolean anotherCanPartition(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                dp[j] = dp[j] || dp[j - nums[i - 1]];
                if (dp[target])
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 3, 3, 4, 5 };
        System.out.println(anotherCanPartition(nums));
    }
}
