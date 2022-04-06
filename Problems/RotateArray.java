/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 */
package Problems;

import java.util.*;

public class RotateArray {
    // solution using a hash table
    public static void rotate(int[] nums, int k) {
        if (nums.length <= k)
            k %= nums.length;
        if (nums.length == 1 || k == 0)
            return;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(i + k, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if(i < k) {
                nums[i] = map.get(nums.length + i);
            } else {
                nums[i] = map.get(i);
            }
        }
    }

    // solution using an array
    public static void anotherRotate(int[] nums, int k) {
        if (nums.length <= k)
            k %= nums.length;
        if (nums.length == 1 || k == 0)
            return;
        int[] storage = new int[k];
        int count = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (count < k) {
                storage[k - 1 - count] = nums[i];
                count++;
            }
            if (i >= k) {
                nums[i] = nums[i - k];
            } else {
                nums[i] = storage[i];
            }
        }
    }

    // fastest solution
    public static void anotherRotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // solution using cycle
    public static void anotherRotate3(int[] nums, int k) {
        if (nums.length <= k)
            k %= nums.length;
        if (nums.length == 1 || k == 0)
            return;
        int temp;
        int index;
        if ((nums.length % k) / 2 == 0) {
            for (int i = 0; i < k; i++) {
                temp = nums[i];
                index = i + k;
                while (index < nums.length) {
                    int temp2 = nums[index];
                    nums[index] = temp;
                    temp = temp2;
                    index += k;
                    if (index > nums.length - 1) {
                        nums[i] = temp;
                    }
                }
            }
        } else {
            temp = nums[0];
            index = 0 + k;
            while (index < nums.length) {
                int temp2 = nums[index];
                nums[index] = temp;
                temp = temp2;
                if (index == 0)
                    return;
                index += k;
                if (index > nums.length - 1) {
                    index = index - nums.length;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = { -1, -100, 3, 99 };
        int k = 2;
        anotherRotate3(nums, k);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }
}
