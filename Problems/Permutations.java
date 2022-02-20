/**
 * Given an array nums of distinct integers, return all the possible permutations. 
 * You can return the answer in any order.
 */
package Problems;

import java.util.*;

public class Permutations {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int num : nums) {
            Set<Integer> set = new HashSet<>();
            List<Integer> list = new ArrayList<>();
            list.add(num);
            set.add(num);
            dfsRecursion(res, list, nums, set);
        }
        return res;
    }

    private static void dfsRecursion(List<List<Integer>> res, List<Integer> list, int[] nums, Set<Integer> set) {
        if (list.size() >= nums.length) {
            List<Integer> newList = new ArrayList<>(list);
            res.add(newList);
            return;
        }
        for (int num : nums) {
            if (!set.contains(num)) {
                list.add(num);
                set.add(num);
                dfsRecursion(res, list, nums, set);
                list.remove(list.size() - 1);
                set.remove(num);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> res = permute(nums);
        for (List<Integer> list : res) {
            System.out.print("[ ");
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
    }
}