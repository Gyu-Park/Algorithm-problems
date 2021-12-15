/**
Implement function countNumbers that accepts a sorted array of unique integers and, 
efficiently with respect to time used, 
counts the number of array elements that are less than the parameter lessThan.

For example, SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4) should return 2 
because there are two array elements less than 4.
 **/
package Problems;

import java.util.Arrays;

public class SortedSearch {
    public static int countNumbers(int[] sortedArray, int lessThan) {

        int foundKey = Arrays.binarySearch(sortedArray, lessThan);

        if (foundKey == 0 || foundKey > 0)
            return foundKey;

        int newLessThan = lessThan--;
        while (foundKey < 0) {
            foundKey = Arrays.binarySearch(sortedArray, newLessThan);
            newLessThan--;
        }

        return foundKey + 1;
    }

    public static void main(String[] args) {

        System.out.println(SortedSearch.countNumbers(new int[] { 1, 3, 5, 7 }, 4));

    }
}