/**
 * Given an integer array nums, return the third distinct maximum number in this array. 
 * If the third maximum does not exist, return the maximum number.
 */
package Problems;

public class ThirdMaximumNumber {
    public static int thirdMax(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        Integer firstMax = nums[0];
        Integer secondMax = null;
        Integer thirdMax = null;
        for (int i = 1; i < nums.length; i++) {
            if (secondMax == null && nums[i] != firstMax) {
                if (nums[i] > firstMax) {
                    secondMax = firstMax;
                    firstMax = nums[i];

                } else {
                    secondMax = nums[i];
                }
            }
            if (thirdMax == null && nums[i] != firstMax && nums[i] != secondMax) {
                thirdMax = nums[i];
                if (nums[i] > secondMax) {
                    thirdMax = secondMax;
                    secondMax = nums[i];
                }
                if (secondMax > firstMax) {
                    secondMax += firstMax;
                    firstMax = secondMax - firstMax;
                    secondMax -= firstMax;
                }
            }
            if (thirdMax != null && nums[i] != firstMax && nums[i] != secondMax && nums[i] != thirdMax) {
                if (nums[i] > thirdMax) {
                    thirdMax = nums[i];
                }
                if (thirdMax > secondMax) {
                    thirdMax += secondMax;
                    secondMax = thirdMax - secondMax;
                    thirdMax -= secondMax;
                }
                if (secondMax > firstMax) {
                    secondMax += firstMax;
                    firstMax = secondMax - firstMax;
                    secondMax -= firstMax;
                }
            }
        }
        if (thirdMax == null)
            return firstMax;
        return thirdMax;
    }

    // shorter solution
    public static int shorterThirdMax(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3))
                continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2 };
        System.out.println(thirdMax(nums));
        System.out.println(shorterThirdMax(nums));
    }
}
