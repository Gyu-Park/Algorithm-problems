/**
 * You are given an integer array height of length n. 
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * 
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * 
 * Return the maximum amount of water a container can store.
 * 
 * Notice that you may not slant the container.
 */
package Problems;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int res = 0;
        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while (leftPointer < rightPointer) {
            res = Math.max(res, (rightPointer - leftPointer)
                    * Math.min(height[leftPointer], height[rightPointer]));

            if (height[leftPointer] < height[rightPointer])
                leftPointer++;
            else
                rightPointer--;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(maxArea(height));
    }
}
