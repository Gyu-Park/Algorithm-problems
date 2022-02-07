/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * 
 * If target is not found in the array, return [-1, -1].
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 */
package Problems;

public class FindFirstAndLastPositionOfElementInSortedArray {
    // binary search solution
    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0)
            return new int[] { -1, -1 };
        if (target < nums[0] || target > nums[nums.length - 1])
            return new int[] { -1, -1 };

        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            int mid = startIndex + (endIndex - startIndex) / 2;
            if (startIndex == mid && nums[mid] != target) {
                mid++;
            }
            if (nums[mid] == target) {
                boolean left = false;
                boolean right = false;
                int leftIndex = mid;
                int rightIndex = mid;
                while (!left || !right) {
                    if (leftIndex != 0 && nums[leftIndex - 1] == nums[mid]) {
                        leftIndex--;
                    } else {
                        left = true;
                    }
                    if (rightIndex != nums.length - 1 && nums[rightIndex + 1] == nums[mid]) {
                        rightIndex++;
                    } else {
                        right = true;
                    }
                }
                return new int[] { leftIndex, rightIndex };
            } else if (nums[mid] < target && nums[mid + 1] > target) {
                return new int[] { -1, -1 };
            } else if (nums[mid] > target && nums[mid - +1] < target) {
                return new int[] { -1, -1 };
            }

            if (nums[mid] > target) {
                endIndex = mid;
            } else if (nums[mid] < target) {
                startIndex = mid;
            }
        }
        if (nums[startIndex] == target) {
            return new int[] { startIndex, startIndex };
        } else {
            return new int[] { -1, -1 };
        }
    }

    // more readable solution
    public static int[] anotherSearchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private static int findFirst(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            if (nums[mid] == target)
                idx = mid;
        }
        return idx;
    }

    private static int findLast(int[] nums, int target) {
        int idx = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
            if (nums[mid] == target)
                idx = mid;
        }
        return idx;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, 3, 4, 4, 4 };
        int target = 4;
        int[] res = searchRange(nums, target);
        for (int i : res) {
            System.out.print(i + " ");
        }

        System.out.println(" ");

        int[] res2 = anotherSearchRange(nums, target);
        for (int i : res2) {
            System.out.print(i + " ");
        }
    }
}
