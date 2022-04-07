/**
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, 
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected 
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * 
 * Given an integer array nums representing the amount of money of each house, 
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
package Problems;

public class HouseRobber {
    // dp solution
    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return nums[0] > nums[1] ? nums[0] : nums[1];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2] + dp[0];
        for (int i = 3; i < nums.length; i++) {
            if (dp[i - 2] > dp[i - 3]) {
                dp[i] = dp[i - 2] + nums[i];
            } else {
                dp[i] = dp[i - 3] + nums[i];
            }
        }
        return dp[dp.length - 1] > dp[dp.length - 2] ? dp[dp.length - 1] : dp[dp.length - 2];
    }

    // another dp solution
    public int anotherRob(int[] nums) {
        int prevNo = 0;
        int prevYes = 0;
        for (int i : nums) {
            int temp = prevNo;
            prevNo = Math.max(prevNo, prevYes);
            prevYes = i + temp;
        }
        return Math.max(prevNo, prevYes);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 1 };
        System.out.println(rob(nums));
    }
}
