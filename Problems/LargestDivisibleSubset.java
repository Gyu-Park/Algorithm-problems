/**
 * Given a set of distinct positive integers nums, 
 * return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 * - answer[i] % answer[j] == 0, or
 * - answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *  
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 * 
 * Example 2:
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 *  
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 109
 * All the integers in nums are unique.
 */
package Problems;

import java.util.*;

public class LargestDivisibleSubset {
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // make the input ascending order to get the largest subset easily
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;

        // using dynamic programming, store the number of elements that is smaller than nums[i] in the subset
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // get the largest index that has the largest number of elements in a subset
        int maxIndex = 0;
        for (int i = 0; i < dp.length; i++) {
            maxIndex = Math.max(maxIndex, i);
        }

        // put the each element of the subset in the result list
        int temp =  nums[maxIndex];
        int currentDp = dp[maxIndex];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (temp % nums[i] == 0 && dp[i] == currentDp) {
                res.add(nums[i]);
                temp = nums[i];
                currentDp--;
            }
        }

        return res;
    }


    // another solution using stream
    public static List<Integer> anotherLargestDivisibleSubset(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.stream(nums).sorted().forEach(value -> {
            List<Integer> prefix = new ArrayList<>(res.stream()
                .filter(l -> value % l.get(l.size() - 1) == 0)
                .max(Comparator.comparing(l -> l.size())).orElse(Collections.emptyList()));
            prefix.add(value);
            res.add(prefix);
        });
        return res.stream().max(Comparator.comparing(l -> l.size())).orElse(Collections.emptyList());
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 8, 10, 12, 13, 15, 18, 20};
        List<Integer> res = largestDivisibleSubset(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }

        System.out.println();

        List<Integer> res2 = anotherLargestDivisibleSubset(nums);
        for (int i : res2) {
            System.out.print(i + " ");
        }
    }
}
