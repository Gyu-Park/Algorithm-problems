/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * 
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * 
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 */
package Problems;

public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int n = nums.length;
        int pivotValue = nums[0] - 1;
        if (target > pivotValue) {
            for (int i = 0; i < n; i++) {
                if (target == nums[i])
                    return i;
                if (i != n - 1 && nums[i] > nums[i + 1])
                    return -1;
            }
        } else {
            for (int i = n - 1; i >= 0; i--) {
                if (target == nums[i])
                    return i;
                if (i != 0 && nums[i] < nums[i - 1])
                    return -1;
            }
        }
        return -1;
    }

    // better solution (binary search)
    public static int anotherSearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int res = nums[mid];
            if ((target < nums[0]) && (nums[mid] < nums[0]) || (target >= nums[0]) && (nums[mid] >= nums[0]))
                res = nums[mid];
            else {
                if (target < nums[0])
                    res = Integer.MIN_VALUE;
                else
                    res = Integer.MAX_VALUE;
            }
            if (target == res)
                return mid;
            if (target > res)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 3;
        System.out.println(search(nums, target));
        System.out.println(anotherSearch(nums, target));
    }
}
