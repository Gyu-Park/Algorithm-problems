/**
 * You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed. 
 * All houses at this place are arranged in a circle. 
 * That means the first house is the neighbor of the last one. 
 * Meanwhile, adjacent houses have a security system connected, 
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, 
 * return the maximum amount of money you can rob tonight without alerting the police.
 *  -------------------------------------------------------------------------------------------------------------------
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * 
 * -------------------------------------------------------------------------------------------------------------------
 * 
 * Example 2:
 * 
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * -------------------------------------------------------------------------------------------------------------------
 * 
 * Example 3:
 * 
 * Input: nums = [1,2,3]
 * Output: 3
 * 
 *  -------------------------------------------------------------------------------------------------------------------
 * 
 * Constraints:
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
package Problems;

public class HouseRobberII {
    // DP solution
    // time complexity: O(2n) = considered O(n)
    // space complexity: O(n) >>> same as time complexity
    public static int rob(int[] nums) {
        int res = 0;
        int[][] dp = new int[3][nums.length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp[0].length; j++) {
                if (i == 1 && (j == 2 || j == dp[0].length - 1))
                    continue;
                if (i == j) {
                    dp[i][j] = nums[i - 1];
                    res = Math.max(res, dp[i][j]);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 2], dp[i][j - 3]) + nums[j - 1];
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res;
    }

    // better solution in terms of space complexity
    // time complexity: O(n)
    // space complexity: O(1)
    public static int anotherRob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        return Math.max(rob0(nums), rob1(nums));
    }

    private static int rob0(int[] nums) {
        int preMax = 0;
        int curMax = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int t = curMax;
            curMax = Math.max(curMax, preMax + nums[i]);
            preMax = t;
        }
        return curMax;
    }

    private static int rob1(int[] nums) {
        int preMax = 0;
        int curMax = 0;
        for (int i = 1; i < nums.length; i++) {
            int t = curMax;
            curMax = Math.max(curMax, preMax + nums[i]);
            preMax = t;
        }
        return curMax;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 5, 9, 6, 8, 7, 4};
        System.out.println(rob(nums));
    }
}
