/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * 
 * A subsequence is a sequence that can be derived from an array 
 * by deleting some or no elements without changing the order of the remaining elements. 
 * For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 */
package Problems;

import java.util.*;

public class LongestIncreasingSubsequence {
    // O(nlogn) time complexity
    public static int lengthOfLIS(int[] nums) {
        int res = 1;
        if (nums.length == 1)
            return res;
        List<Integer> subArray = new LinkedList<>();
        subArray.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < subArray.get(subArray.size() - 1))
                helper(nums[i], subArray);
            else if (nums[i] > subArray.get(subArray.size() - 1))
                subArray.add(nums[i]);
        }
        return subArray.size();
    }

    private static void helper(int value, List<Integer> subArray) {
        for (int i = 0; i < subArray.size(); i++) {
            if (value <= subArray.get(i)) {
                subArray.set(i, value);
                return;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = { 4, 10, 4, 3, 8, 9 };
        System.out.println(lengthOfLIS(nums));
    }
}
