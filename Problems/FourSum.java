/**
 * Given an array nums of n integers, 
 * return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * 
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 */
package Problems;

import java.util.*;

public class FourSum {
    // a solution using two for loops with two pointers
    // time complexity O(n^3)
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 3)
            return res;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i])
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j])
                    continue;
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    int sum = nums[i] + nums[j] + nums[start] + nums[end];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        start++;
                        end--;
                        while (start < end && nums[start - 1] == nums[start])
                            start++;
                        while (start < end && nums[end + 1] == nums[end])
                            end--;
                    } else if (sum < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }

        return res;
    }

    // the same method but faster solution
    // because 5 lines of code to pruning the answer by calculating the min and max
    // of twoSum.
    public static List<List<Integer>> anotherFourSum(int[] nums, int target) {
        List<List<Integer>> list = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int twoSumTarget = target - nums[i] - nums[j]; // 5 lines of code from 65 to 69
                int minTwoSum = nums[j + 1] + nums[j + 2];
                int maxTwoSum = nums[nums.length - 1] + nums[nums.length - 2];
                if (twoSumTarget < minTwoSum || twoSumTarget > maxTwoSum)
                    continue;
                for (int start = j + 1, end = nums.length - 1; start < end;) {
                    int twoSum = nums[start] + nums[end];
                    if (twoSum < twoSumTarget) {
                        while (start < end && nums[start] == nums[start + 1])
                            start++;
                        start++;
                    } else if (twoSum > twoSumTarget) {
                        while (start < end && nums[end] == nums[end - 1])
                            end--;
                        end--;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[j], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[start + 1])
                            start++;
                        start++;
                        while (start < end && nums[end] == nums[end - 1])
                            end--;
                        end--;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 0, -1, 0, -2, 2 };
        int target = 0;
        List<List<Integer>> res = fourSum(nums, target);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
