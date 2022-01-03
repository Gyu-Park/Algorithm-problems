/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. 
 * Each element in the result must be unique and you may return the result in any order.
 */
package Problems;

import java.util.*;

public class IntersectionOfTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num) && !set2.contains(num))
                set2.add(num);
        }

        int[] res = new int[set2.size()];
        Iterator<Integer> iter = set2.iterator();
        int i = 0;
        while (iter.hasNext()) {
            res[i++] = iter.next();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = { 4, 9, 5 };
        int[] nums2 = { 9, 4, 9, 8, 4 };
        int[] res = intersection(nums1, nums2);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
