/**
 * Given a collection of candidate numbers (candidates) and a target number (target), 
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * 
 * Each number in candidates may only be used once in the combination.
 * 
 * Note: The solution set must not contain duplicate combinations.
 */
package Problems;

import java.util.*;

public class CombinationSumII {

    static List<List<Integer>> res;

    // dfs solution
    // time complexity O(2^n)
    // space complexity O(n)
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, new ArrayList<>());
        return res;
    }

    private static void helper(int[] candidates, int target, int index, List<Integer> list) {
        if (index >= candidates.length)
            return;

        Integer pre = null;
        for (int i = index; i < candidates.length; i++) {
            if (pre != null && pre == candidates[i])
                continue;
            if (target - candidates[i] > 0) {
                list.add(candidates[i]);
                helper(candidates, target - candidates[i], i + 1, list);
                pre = list.remove(list.size() - 1);
            } else if (target - candidates[i] == 0) {
                list.add(candidates[i]);
                res.add(new ArrayList<>(list));
                pre = list.remove(list.size() - 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        System.out.println(combinationSum2(candidates, target));
    }
}
