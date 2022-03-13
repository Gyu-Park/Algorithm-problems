/**
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * 
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 */
package Problems;

import java.util.*;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        for (int i = 1; i <= nums.length; i++) {
            recursion(res, new ArrayList<Integer>(), nums, i, 0);
        }

        return res;
    }

    private static void recursion(List<List<Integer>> res, List<Integer> list, int[] nums, int numElements, int index) {
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            if (numElements - 1 > 0) {
                recursion(res, list, nums, numElements - 1, i + 1);
            } else {
                res.add(new ArrayList<>(list));
            }
            list.remove(list.size() - 1);
        }
    }

    // another solution
    public static List<List<Integer>> anotherSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());

        for (int i : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> sub : res) {
                List<Integer> a = new ArrayList<>(sub);
                a.add(i);
                tmp.add(a);
            }
            res.addAll(tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> res = anotherSubsets(nums);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i + " ");
            }
            System.out.print("\n");
        }
    }
}
