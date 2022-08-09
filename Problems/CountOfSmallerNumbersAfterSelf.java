/**
 * Given an integer array nums, 
 * return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 * 
 */
package Problems;

import java.util.*;
import java.util.stream.Collectors;

public class CountOfSmallerNumbersAfterSelf {
    // solution using merge sort
    // time complexity: O(nlogn)
    public static List<Integer> countSmaller(int[] nums) {
        int[] res = new int[nums.length];
        int[] index = new int[nums.length]; // store sorted array's index for res while mearging
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        mergeSort(nums, index, 0, nums.length-1, res);
        List<Integer> list = Arrays.stream(res).boxed().collect(Collectors.toList());
        return list;
    }

    private static void mergeSort(int[] nums, int[] index, int l, int r, int[] res) {
        if (l >= r)
            return;
        int mid = (l + r) / 2;
        mergeSort(nums, index, l, mid, res);
        mergeSort(nums, index, mid + 1, r, res);
        merge(nums, index, l, mid, mid+1, r, res);
    }

    private static void merge(int[] nums, int[] index, int l1, int r1, int l2, int r2, int[] res) {
        int start = l1;
        int[] tmp = new int[r2 - l1 + 1]; // for new index after merging
        int count = 0; // for counting smaller right elements
        int p = 0; // for tmp counting
        while (l1 <= r1 || l2 <= r2) {
            if (l1 > r1) { // there's no elements in first part(l1, r1 part)
                tmp[p++] = index[l2++]; // so no need to save count; instead, store index[l1] into tmp[p] and then count them up
            } else if (l2 > r2) { // there's no elements in second part(l2, r2 part)
                res[index[l1]] += count; // so save count value into res[index[l1]]
                tmp[p++] = index[l1++];
            } else if (nums[index[l1]] > nums[index[l2]]) { // when l1 value is bigger than l2 value
                tmp[p++] = index[l2++]; // store index[l2] into tmp[p] and then count them up
                count++; // count up for left array (meaning small value on the right from l1 value)
            } else { // when l2 value is bigger than l1 value
                res[index[l1]] += count; // save count value into res[index[l1]]
                tmp[p++] = index[l1++]; // store index[l1] into tmp[p] and then count them up
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            index[start + i] = tmp[i]; // update index array
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {7, 5, 8, 3}; // 5, 7, 3, 8 -> 3, 5, 7, 8
        System.out.println(countSmaller(nums));
    }
}
