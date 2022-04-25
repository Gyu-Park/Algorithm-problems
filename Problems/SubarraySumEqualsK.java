/**
 * Given an array of integers nums and an integer k, 
 * return the total number of subarrays whose sum equals to k.
 */
package Problems;

import java.util.*;

public class SubarraySumEqualsK {
    // a soultion using a  prefix hashmap
    // time complexity O(n)
    // space complexity O(n)
    public static int subarraySum(int[] nums, int k) {
        if (nums.length == 1)
            return nums[0] == k ? 1 : 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 1, 2, 3, 4 };
        int k = 2;
        System.out.println(subarraySum(nums, k));
    }
}
