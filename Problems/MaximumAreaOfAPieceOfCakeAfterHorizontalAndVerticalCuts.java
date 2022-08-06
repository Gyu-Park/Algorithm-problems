/**
 * You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
 * 
 * - horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
 * - verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
 * 
 * Return the maximum area of a piece of cake 
 * after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. 
 * Since the answer can be a large number, return this modulo 10^9 + 7.
 */
package Problems;

import java.util.Arrays;

public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    // time complexity: O(max(hlogh, vlogv)), where h and v are the length of each array
    // space complexity: O(1);
    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long maxWidth = getMaxDistance(h, horizontalCuts);
        long maxHeight = getMaxDistance(w, verticalCuts);

        return (int) (maxHeight * maxWidth % 1000000007);
    }

    private static long getMaxDistance(int end, int[] cuts) {
        Arrays.sort(cuts);
        int maxDist = 0;
        int prev = 0;
        for (int i : cuts) {
            maxDist = Math.max(maxDist, i - prev);
            prev = i;
        }
        return Math.max(maxDist, end - prev);
    }
    
    public static void main(String[] args) {
        int h = 5;
        int w = 4;
        int[] horizontalCuts = {3, 1};
        int[] verticalCuts = {1};
        System.out.println(maxArea(h, w, horizontalCuts, verticalCuts));
    }
}
