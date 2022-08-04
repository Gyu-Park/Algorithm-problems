/**
 * Given an array of integers with elements representing lengths of ribbons. 
 * 
 * Your goal is to obtain k ribbons of equal length cutting the ribbons into as many pieces as you want. 
 * 
 * Find the maximum integer length L to obtain at least k ribbons of length L
 */
package Problems;

public class CutRibbon {
    // binary search solution
    // time complexity: O(nlogk) where k is the max number in the arr
    //                  and n is the length of the arr
    // space complexity: O(1)
    public static int greatestLength(int[] arr, int k) {
        int max = 0;
        for (int a : arr)
          max = Math.max(max, a);
    
        int lo = 1, hi = max;
        while (lo <= hi) {
          int mid = lo + (hi - lo) / 2;
          int len = getLen(arr, mid);
          if (len >= k)
            lo = mid + 1;
          else
            hi = mid - 1;
        }

        return hi;
      }

      private static int getLen(int[] arr, int target) {
        int res = 0;
        for (int a : arr)
          res += (a / target);
        return res;
      }
    
      public static void main(String[] args){
        int[] arr = {1, 2, 9, 4, 3};
        System.out.println(greatestLength(arr, 5));
      }
}
