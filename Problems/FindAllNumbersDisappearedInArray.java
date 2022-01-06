/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], 
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 */
package Problems;

import java.util.*;

public class FindAllNumbersDisappearedInArray {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int[] array = new int[nums.length];
        for (int num : nums) {
            array[num - 1] = num;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0)
                list.add(i + 1);

        }

        return list;
    }

    // O(n) time and space complexity solution
    public static List<Integer> anotherFindDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length)
                nums[nums[i] - 1] += nums.length + 1;
            else
                nums[nums[i] % (nums.length + 1) - 1] += nums.length + 1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= nums.length)
                list.add(i + 1);
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 3, 2, 7, 7, 2, 3, 1 };
        List<Integer> list = findDisappearedNumbers(nums);
        for (int num : list) {
            System.out.println(num);
        }

        List<Integer> list2 = anotherFindDisappearedNumbers(nums);
        for (int num : list2) {
            System.out.println(num);
        }
    }
}
