/**
 * Given an integer array nums, 
 * return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * 
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
package Problems;

public class ProductOfArrayExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int temp = 1;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 2, 4, 5, 10, 3 };
        int[] res = productExceptSelf(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
