/**
* Given two sorted arrays nums1 and nums2 of size m and n respectively,
* return the median of the two sorted arrays.
*
* The overall run time complexity should be O(log (m+n)).
**/

package Problems;

public class MedianOfTwoSortedArrays {

    /**
     * I couldn't figure out a solution
     * although I knew that I needed to use binary search,
     * so I refer other's solution.
     */

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;
        int firstMiddlePoint = (nums1Length + nums2Length + 1) / 2; // firstPoint in a combined array
        int secondMiddlePoint = (nums1Length + nums2Length + 2) / 2; // secondPoint in a combined array
        return (getMedian(nums1, 0, nums2, 0, firstMiddlePoint) + getMedian(nums1, 0, nums2, 0, secondMiddlePoint))
                / 2.0;
    }

    private static double getMedian(int[] nums1, int nums1StartPoint, int[] nums2, int nums2StartPoint,
            int middlePoint) {
        if (nums1StartPoint > nums1.length - 1)
            return nums2[nums2StartPoint + middlePoint - 1];
        if (nums2StartPoint > nums2.length - 1)
            return nums1[nums1StartPoint + middlePoint - 1];
        if (middlePoint == 1)
            return Math.min(nums1[nums1StartPoint], nums2[nums2StartPoint]);

        int nums1Mid = Integer.MAX_VALUE;
        int nums2Mid = Integer.MAX_VALUE;
        if (nums1StartPoint + middlePoint / 2 - 1 < nums1.length)
            nums1Mid = nums1[nums1StartPoint + middlePoint / 2 - 1];
        if (nums2StartPoint + middlePoint / 2 - 1 < nums2.length)
            nums2Mid = nums2[nums2StartPoint + middlePoint / 2 - 1];

        if (nums1Mid < nums2Mid)
            return getMedian(nums1, nums1StartPoint + middlePoint / 2, nums2, nums2StartPoint,
                    middlePoint - middlePoint / 2);
        else
            return getMedian(nums1, nums1StartPoint, nums2, nums2StartPoint + middlePoint / 2,
                    middlePoint - middlePoint / 2);
    }

    public static void main(String[] args) {
        int[] input1 = new int[] { 3, 4, 9, 19 };
        int[] input2 = new int[] { 5, 7, 10 };
        int[] input3 = new int[] { 1, 2, 3, 5, 9, 11, 12, 15, 19, 31 };
        int[] input4 = new int[] { -1, 0, 4, 18 };
        double result = findMedianSortedArrays(input1, input2);
        double result2 = findMedianSortedArrays(input3, input4);
        System.out.println("result: " + result);
        System.out.println("result: " + result2);
    }
}
