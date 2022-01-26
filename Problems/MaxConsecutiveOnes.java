/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 */
package Problems;

public class MaxConsecutiveOnes {
    public static int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 0;
            }
        }
        return Math.max(res, count);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 0, 1, 1, 1 };
        System.out.println(findMaxConsecutiveOnes(nums));
    }
}
