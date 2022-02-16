/**
 * Given an array of distinct integers candidates and a target integer target, 
 * return a list of all unique combinations of candidates where the chosen numbers sum to target. 
 * You may return the combinations in any order.
 * 
 * The same number may be chosen from candidates an unlimited number of times. 
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * 
 * It is guaranteed that the number of unique combinations 
 * that sum up to target is less than 150 combinations for the given input.
 */
package Problems;

import java.util.*;

public class CombinationSum {

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int index = 0;
        while (index < candidates.length) {
            List<Integer> list = new ArrayList<>();
            list.add(candidates[index]);
            if (candidates[index] < target) {
                dfs(candidates, target, list, candidates[index], index);
            } else if (candidates[index] == target) {
                res.add(new ArrayList<Integer>(list));
            }
            index++;
        }
        return res;
    }

    private static void dfs(int[] candidates, int target, List<Integer> list , int sum, int index) {
        while (index < candidates.length) {
            if (sum + candidates[index] < target) {
                sum += candidates[index];
                list.add(candidates[index]);
                dfs(candidates, target, list, sum, index);
                sum -= candidates[index];
                list.remove(list.size() - 1);
            } else if (sum + candidates[index] == target) {
                list.add(candidates[index]);
                res.add(new ArrayList<Integer>(list));
                list.remove(list.size() - 1);
            }
            index++;
        }
    }
    
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = combinationSum(candidates, target);
        for (List<Integer> r : res) {
            for (int i : r) {
                System.out.print(i + " ");
            }
            System.out.print(" / ");
        }
    }
}
