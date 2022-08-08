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

    // another binary search solution
    // time complexity: O(log m+n)
    // space complexity: O(1)
    public static double anotherFindMedianSortedArrays(int[] nums1, int[] nums2) {
        int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.

        int lo = 0, hi = N2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly

            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];

            if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] input1 = new int[] { 3, 4, 9, 19 };
        int[] input2 = new int[] { 5, 7, 10 };
        int[] input3 = new int[] { 2, 3, 5, 9, 11, 12, 15, 19, 31, 32 };
        int[] input4 = new int[] { -1 };
        double result = findMedianSortedArrays(input1, input2);
        double result2 = anotherFindMedianSortedArrays(input3, input4);
        System.out.println("result: " + result);
        System.out.println("result: " + result2);
    }
}
