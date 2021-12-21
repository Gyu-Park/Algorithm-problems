/**
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 */
package Problems;

public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length - 1])
            return nums.length;
        if (target < nums[0])
            return 0;

        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private static int binarySearch(int arr[], int start, int end, int target) {

        if (end >= start) {
            int mid = (start + end) / 2;

            if (arr[mid] == target)
                return mid;

            if (arr[mid] > target && arr[mid - 1] < target)
                return mid;

            if (arr[mid] > target)
                return binarySearch(arr, start, mid - 1, target);

            return binarySearch(arr, mid + 1, end, target);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 6, 7, 8 };
        int target = 6;
        System.out.println(searchInsert(nums, target));
    }
}
