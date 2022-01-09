/**
 * Given an integer array nums, return all the triplets 
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 */
package Problems;

import java.util.*;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums.length < 3)
            return res;
        Arrays.sort(nums);
        List<Integer> triplet = new ArrayList<Integer>();
        Set<List<Integer>> set = new HashSet<>();
        int k = -1;
        int end = nums.length - 1;

        for (int i = 0; i < end - 1; i++) {
            if (nums[i] > 0)
                break;
            for (int j = i + 1; j < end; j++) {
                if (nums[i] + nums[j] > 0) {
                    break;
                }
                if (nums[i] + nums[j] + nums[end] >= 0)
                    k = binarySearch(nums, j, (end - j) / 2 + j, end, nums[i] + nums[j]);
                if (k != -1) {
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);
                    if (res.size() > 0 && !set.contains(triplet)) {
                        res.add(triplet);
                        set.add(triplet);
                    } else if (res.size() == 0) {
                        res.add(triplet);
                        set.add(triplet);
                    }
                }
                if (triplet.size() > 0)
                    triplet = new ArrayList<>();
                k = -1;
            }
        }
        return res;
    }

    private static int binarySearch(int[] nums, int start, int mid, int end, int firstPlusSecond) {
        while (nums[mid] + firstPlusSecond != 0 && start != mid) {
            if (nums[mid] + firstPlusSecond < 0) {
                start = mid;
                mid = (end - start) / 2 + start;
            } else if (nums[mid] + firstPlusSecond > 0) {
                end = mid;
                mid = (end - start) / 2 + start;
            }
        }

        if (start == mid)
            mid = end;
        if (nums[mid] + firstPlusSecond != 0)
            mid = -1;
        return mid;
    }

    // better solution
    public static List<List<Integer>> anotherThreeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new LinkedList<>();
        if (num.length < 3 || num[0] > 0 || num[num.length - 1] < 0)
            return res;
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1])
                            lo++;
                        while (lo < hi && num[hi] == num[hi - 1])
                            hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum)
                        lo++;
                    else
                        hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4 };
        List<List<Integer>> res = threeSum(nums);
        for (List<Integer> list : res) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.print("/");
        }
    }
}
