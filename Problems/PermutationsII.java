/**
 * Given a collection of numbers, nums, that might contain duplicates, 
 * return all possible unique permutations in any order.
 */
package Problems;

import java.util.*;

public class PermutationsII {

    static List<List<Integer>> res;

    // dfs solution
    // time complexity - average: O(n!) / worst case: O(n! * n)
    public static List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        if (nums.length == 1) {
            list.add(nums[0]);
            res.add(list);
            return res;
        }

        Arrays.sort(nums);
        int[] usedNumsIndex = new int[nums.length];
        recursion(nums, list, usedNumsIndex);
        return res;
    }

    private static void recursion(int[] nums, List<Integer> list, int[] numsIndex) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (numsIndex[i] == 0) {
                list.add(nums[i]);
                numsIndex[i] = 1;
                recursion(nums, list, numsIndex);
                list.remove(list.size() - 1);
                numsIndex[i] = 0;

                while (i < nums.length - 1) {
                    if (nums[i] == nums[i + 1])
                        i++;
                    else
                        break;
                }
            }
        }
    }

    // optimized solution
    public static List<List<Integer>> optimizedPermuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return results;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        recursion(map, nums.length, new Integer[nums.length], 0, results);
        return results;
    }

    private static void recursion(Map<Integer, Integer> map, int len, Integer[] p, int index,
            List<List<Integer>> res) {
        if (index == len) {
            res.add(Arrays.asList(Arrays.copyOf(p, len)));
            return;
        }
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                map.put(key, map.get(key) - 1);
                p[index] = key;
                recursion(map, len, p, index + 1, res);
                map.put(key, map.get(key) + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 2 };
        List<List<Integer>> res = permuteUnique(nums);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
