/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * 
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
package Problems;

import java.util.*;

public class SubsetsII {
    // recursive solution
    // time complexity O(2^n)
    // space complexity O(2^n)
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        Arrays.sort(nums);
        return helper(nums, 0, res, new ArrayList<>());
    }

    private static List<List<Integer>> helper(int[] nums, int index, List<List<Integer>> res, List<Integer> list) {
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i])
                continue;
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            res = helper(nums, i + 1, res, list);
            list.remove(list.size() - 1);
        }

        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> res = subsetsWithDup(nums);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
