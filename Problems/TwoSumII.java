/**
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, 
 * find two numbers such that they add up to a specific target number. 
 * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * 
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * 
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * 
 * Example 1:
 * 
 * -----------------------------------------------------------------------------------------------
 * |  Input: numbers = [2,7,11,15], target = 9                                                   |
 * |  Output: [1,2]                                                                              |
 * |  Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2]. |
 * -----------------------------------------------------------------------------------------------
 */
package Problems;

import java.util.*;

public class TwoSumII {
    public static int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (!map.containsKey(numbers[i])) {
                map.put(target - numbers[i], i);
            } else {
                return new int[] { map.get(numbers[i]) + 1, i + 1 };
            }
        }

        return null;
    }

    // better version
    public static int[] anotherVersionTwoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (numbers[left] + numbers[right] != target) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                break;
            }
        }

        return new int[] { ++left, ++right };
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        int[] results = twoSum(nums, target);
        for (int result : results) {
            System.out.print(result + " ");
        }

        System.out.println(" ");

        results = anotherVersionTwoSum(nums, target);
        for (int result : results) {
            System.out.print(result + " ");
        }
    }
}
