/**
 * Given an array nums with n objects colored red, white, or blue, 
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * 
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * 
 * You must solve this problem without using the library's sort function.
 */
package Problems;

public class SortColors {
    // one pass solution
    public static void sortColors(int[] nums) {
        int rightPoint = nums.length - 1;
        int leftPoint = 0;
        int index = 0;
        while (index <= rightPoint) {
            if (nums[index] == 0) {
                nums[index] = nums[leftPoint];
                nums[leftPoint++] = 0;
            }
            if (nums[index] == 2) {
                nums[index] = nums[rightPoint];
                nums[rightPoint--] = 2;
            }
            if (nums[index] == 1 || index < leftPoint)
                index++;
        }
    }

    // merge sort solution
    public static void anotherSortColors(int[] nums) {
        if (nums.length > 1) {
            int halfLen = nums.length / 2;
            int[] firstHalf = new int[halfLen];
            System.arraycopy(nums, 0, firstHalf, 0, halfLen);
            sortColors(firstHalf);

            int secondHalfLen = nums.length - halfLen;
            int[] secondHalf = new int[secondHalfLen];
            System.arraycopy(nums, halfLen, secondHalf, 0, secondHalfLen);
            sortColors(secondHalf);

            merge(firstHalf, secondHalf, nums);
        }
    }

    private static void merge(int[] firstHalf, int[] secondHalf, int[] tempArray) {
        int curFirstHalf = 0;
        int curSecondHalf = 0;
        int curtempArray = 0;

        while (curFirstHalf < firstHalf.length && curSecondHalf < secondHalf.length) {
            if (firstHalf[curFirstHalf] < secondHalf[curSecondHalf])
                tempArray[curtempArray++] = firstHalf[curFirstHalf++];
            else
                tempArray[curtempArray++] = secondHalf[curSecondHalf++];
        }

        while (curFirstHalf < firstHalf.length)
            tempArray[curtempArray++] = firstHalf[curFirstHalf++];
        while (curSecondHalf < secondHalf.length)
            tempArray[curtempArray++] = secondHalf[curSecondHalf++];
    }

    public static void main(String[] args) {
        int[] nums = { 2, 0, 2, 1, 1, 0 };
        sortColors(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
