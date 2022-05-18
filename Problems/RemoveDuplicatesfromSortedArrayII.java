/**
 * Given an integer array nums sorted in non-decreasing order, 
 * remove some duplicates in-place such that each unique element appears at most twice. 
 * The relative order of the elements should be kept the same.
 * 
 * Since it is impossible to change the length of the array in some languages, 
 * you must instead have the result be placed in the first part of the array nums. 
 * More formally, if there are k elements after removing the duplicates, 
 * then the first k elements of nums should hold the final result. 
 * It does not matter what you leave beyond the first k elements.
 * 
 * Return k after placing the final result in the first k slots of nums.
 * 
 * Do not allocate extra space for another array. 
 * You must do this by modifying the input array in-place with O(1) extra memory.
 */
package Problems;

public class RemoveDuplicatesfromSortedArrayII {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 2)
            return nums.length;

        int pointOne = 1;
        int pointTwo = 1;
        int countDupe = 0;
        while (pointOne < nums.length) {
            if (nums[pointOne - 1] == nums[pointOne])
                countDupe++;
            else
                countDupe = 0;
            if (countDupe < 2)
                nums[pointTwo++] = nums[pointOne++];
            else
                pointOne++;
        }

        return pointTwo;
    }

    // more concise solution
    public static int anotherRemoveDuplicates(int[] nums) {
        int pointer = 0;
        for (int n : nums)
            if (pointer < 2 || n > nums[pointer - 2])
                nums[pointer++] = n;
        return pointer;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 0, 1, 1, 1, 1, 2, 3, 3 };
        System.out.println(removeDuplicates(nums));
    }
}
