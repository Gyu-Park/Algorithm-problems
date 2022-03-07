/**
 * We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * 
 * Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
 * 
 * A subsequence of array is a sequence that can be derived from the array 
 * by deleting some or no elements without changing the order of the remaining elements.
 */

package Problems;

import java.util.*;

public class LongestHarmoniousSubsequence {
    // O(nlogn) because of array sort
    public static int findLHS(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();
        int j = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != j) {
                j = nums[i];
                list.add(intList);
                intList = new ArrayList<>();
            }
            intList.add(nums[i]);
            if (i == nums.length - 1)
                list.add(intList);
        }

        int res = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).get(0) - list.get(i - 1).get(0) == 1) {
                res = Math.max(res, list.get(i).size() + list.get(i - 1).size());
            }
        }
        return res;
    }

    // another solution using HashMap O(n)
    public int anotherFindLHS(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int result = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                result = Math.max(result, map.get(key + 1) + map.get(key));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 2, 5, 2, 3, 7 };
        System.out.println(findLHS(nums));
    }
}
