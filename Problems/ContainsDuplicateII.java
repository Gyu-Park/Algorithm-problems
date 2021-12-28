/**
 * Given an integer array nums and an integer k, 
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 */
package Problems;

import java.util.*;

public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }

    // faster solution
    public static boolean fasterContainsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                return true;

            if (set.size() > k) {
                set.remove(nums[count++]);
            }

        }

        return false;
    }

    public static void main(String[] args) {
        int nums[] = { 1, 2, 3, 1, 2, 3 };
        int k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
        System.out.println(fasterContainsNearbyDuplicate(nums, k));
    }
}
