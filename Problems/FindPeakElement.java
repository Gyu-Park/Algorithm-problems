package Problems;

public class FindPeakElement {
    // iterative binary search solution
    // time complexity: O(log n)
    // spcae complexity: O(1)
    public static int findPeakElement(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid1 = start + (end - start) / 2;
            int mid2 = mid1 + 1;
            if (nums[mid1] < nums[mid2])
                start = mid2;
            else {
                end = mid1;
            }
        }
        return start;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }
}
