/**
 * You are given an integer array nums and an integer target.
 * 
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' 
 * before each integer in nums and then concatenate all the integers.
 * 
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 
 * and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 */
package Problems;

public class TargetSum {

    static int res = 0;

    // dfs solution
    // time complexity O(2^n) and space complexity O(n) when n = nums.length
    public static int findTargetSumWays(int[] nums, int target) {
        recursion(nums, target, 0, 0);
        return res;
    }

    private static void recursion(int[] nums, int target, int index, int sum) {
        if (index >= nums.length) {
            if (sum == target)
                res++;
            else
                return;
        } else {
            recursion(nums, target, index + 1, sum + nums[index]);
            recursion(nums, target, index + 1, sum - nums[index]);
        }
    }

    // dp solution (optimized solution)
    public static int anotherFindTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (sum < target || (target + sum) % 2 > 0)
            return 0;
        return subsetSum(nums, (target + sum) / 2);
    }

    public static int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }

    public static void main(String[] args) {
        int target = 1;
        int[] nums = { 2, 5, 10, 1, 1 };
        System.out.println(anotherFindTargetSumWays(nums, target));
    }
}
