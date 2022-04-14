/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 * 
 * There is only one repeated number in nums, return this repeated number.
 * 
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 */
package Problems;

public class FindTheDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        int[] count = new int[nums.length];
        for (int i : nums) {
            if (count[i] >= 1)
                return i;
            count[i] += 1;
        }
        return -1;
    }

    // another solution using two points and a cycle
    public static int anotherFindDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // marking visited index as a minus value
    public static int anotherFindDuplicate2(int[] nums) {
        for (int num : nums) {
            int absVal = Math.abs(num);
            if (nums[absVal] < 0) {
                return absVal;
            }
            nums[absVal] = -nums[absVal];
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 10, 7, 4, 6, 5, 3, 8, 9, 2, 3 };
        System.out.println(anotherFindDuplicate2(nums));
    }
}
