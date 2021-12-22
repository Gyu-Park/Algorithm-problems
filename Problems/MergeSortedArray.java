/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
 * and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 * 
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * 
 * The final sorted array should not be returned by the function, 
 * but instead be stored inside the array nums1. 
 * To accommodate this, nums1 has a length of m + n, 
 * where the first m elements denote the elements that should be merged, 
 * and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 */
package Problems;

import java.util.Arrays;

public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int mEnd = m - 1;
        int nEnd = n - 1;
        int mnEnd = m + n - 1;
        while (mEnd >= 0 && nEnd >= 0) {
            if (nums1[mEnd] > nums2[nEnd]) {
                nums1[mnEnd--] = nums1[mEnd];
                nums1[mEnd--] = Integer.MIN_VALUE;
            } else {
                nums1[mnEnd--] = nums2[nEnd--];
            }
        }

        while (nEnd >= 0) {
            nums1[nEnd] = nums2[nEnd];
            nEnd--;
        }
    }

    // Solution that uses "Array.Sort()"
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0)
            return;

        if (m == 1 && n == 1) {
            if (nums1[0] < nums2[0]) {
                nums1[1] = nums2[0];
                return;
            } else if (nums1[0] > nums2[0]) {
                nums1[1] = nums1[0];
                nums1[0] = nums2[0];
                return;
            }
        }

        int count = 0;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[count];
            count++;
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        int[] nums1 = { 2, 0 };
        int[] nums2 = { 1 };
        int m = 1;
        int n = 1;
        merge(nums1, m, nums2, n);
        for (int num : nums1) {
            System.out.print(num);
        }

        System.out.println(" ");

        int[] nums3 = { -1, 0, 2, 0, 0, 0, 0 };
        int[] nums4 = { 1, 5, 6, 7 };
        int i = 3;
        int j = 4;
        merge2(nums3, i, nums4, j);
        for (int num : nums3) {
            System.out.print(num);
        }
    }
}
