/**
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * 
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * 
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * 
 * You must decrease the overall operation steps as much as possible.
 */
package Problems;

public class SearchInRotatedSortedArrayII {
    // time complexity O(n) in the worst case, O(logn) in the average case
    // space complexity O(1)
    public static boolean search(int[] nums, int target) {
        if (nums.length == 1)
            return nums[0] == target;

        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            if (nums[start] == target || nums[end] == target)
                return true;
            if (nums[start] == nums[end]) {
                start++;
                end--;
            } else {
                if (target < nums[start] && target > nums[end])
                    return false;
                else
                    return binarySearch(nums, target, start, end);
            }
        }

        return false;
    }

    private static boolean binarySearch(int[] nums, int target, int i, int j) {
        int start = i;
        int end = j;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            int res = nums[mid];
            if ((target < nums[0] && nums[mid] < nums[0]) || (target >= nums[0] && nums[mid] >= nums[0]))
                res = nums[mid];
            else {
                if (target < nums[0])
                    res = Integer.MIN_VALUE;
                else
                    res = Integer.MAX_VALUE;
            }
            if (target == res)
                return true;
            if (target > res)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return false;
    }

    // another solution
    // time complexity O(n) in the worst case, O(logn) in the average case
    // space complexity O(1)
    public boolean anotherSearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        // check each num so we will check start == end
        // We always get a sorted part and a half part
        // we can check sorted part to decide where to go next
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target || nums[start] == target || nums[end] == target)
                return true;

            // if left part is sorted
            if (nums[start] < nums[mid]) {
                if (target < nums[start] || target > nums[mid]) {
                    // target is in rotated part
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else if (nums[start] > nums[mid]) {
                // right part is rotated

                // target is in rotated part
                if (target < nums[mid] || target > nums[end]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // duplicates, we know nums[mid] != target, so nums[start] != target
                // based on current information, we can only move left pointer to skip one cell
                start++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
        int target = 0;
        System.out.println(search(nums, target));
    }
}
