/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * 
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. 
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, 
 * then the next permutation of that array is the permutation that follows it in the sorted container. 
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * 
 * The replacement must be in place and use only constant extra memory.
 */
package Problems;

public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int numsLen = nums.length;
        if (numsLen < 2)
            return;
        int index = numsLen - 1;
        while (index > 0) {
            if (nums[index - 1] < nums[index])
                break;
            index--;
        }
        if (index == 0) {
            reverseSort(nums, 0, numsLen - 1);
            return;
        } else {
            int val = nums[index - 1];
            int j = numsLen - 1;
            while (j >= index) {
                if (nums[j] > val)
                    break;
                j--;
            }
            swap(nums, j, index - 1);
            reverseSort(nums, index, numsLen - 1);
            return;
        }
    }

    public void anotherNextPermutation(int[] A) {
        if (A == null || A.length <= 1)
            return;
        int i = A.length - 2;
        while (i >= 0 && A[i] >= A[i + 1])
            i--; // Find 1st id i that breaks descending order
        if (i >= 0) { // If not entirely descending
            int j = A.length - 1; // Start from the end
            while (A[j] <= A[i])
                j--; // Find rightmost first larger id j
            swap(A, i, j); // Switch i and j
        }
        reverseSort(A, i + 1, A.length - 1); // Reverse the descending sequence
    }

    private static void swap(int[] num, int i, int j) {
        int temp = 0;
        temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    private static void reverseSort(int[] num, int start, int end) {
        if (start > end)
            return;
        for (int i = start; i <= (end + start) / 2; i++)
            swap(num, i, start + end - i);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
