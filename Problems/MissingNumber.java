/**
 * Given an array nums containing n distinct numbers in the range [0, n], 
 * return the only number in the range that is missing from the array.
 */
package Problems;

public class MissingNumber {
    public static int missingNumber(int[] nums) {
        int sum = nums.length * (nums.length + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }

        return sum;
    }

    // XOR solution
    public static int anotherMissingNumber(int[] nums) {
        // a ^ b ^ b = a
        int ans = nums.length;
        for (int i = 0; i < nums.length; i++) {
            ans ^= i;
            ans ^= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        System.out.println(missingNumber(nums));
        System.out.println(anotherMissingNumber(nums));
    }
}
