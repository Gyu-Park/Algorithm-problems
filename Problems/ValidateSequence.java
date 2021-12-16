/** 
Given two non-empty arrays of integers, write a function that determines whether the second array
is a subsequence of the first one.

A subsequence of an array is a set of numbers that aren't necessasrily adjavent in the array
but that are in the same order as they appear in the array.
For instance, the numbers [1, 3, 4] form a subsequence of the array [1, 2, 3, 4],
and sso do the numbers [2, 4].
Note that a single number in an array and the array itself are both valid subsequences of the array.
**/

package Problems;

import java.util.*;

public class ValidateSequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {

        if (array.size() == 0 || sequence.size() == 0)
            return false;

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == sequence.get(0)) {
                sequence.remove(0);
            }
            if (sequence.size() == 0)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {
        List<Integer> array = new ArrayList<>();
        array.add(5);
        array.add(1);
        array.add(22);
        array.add(25);
        array.add(6);
        array.add(-1);
        array.add(8);
        array.add(10);

        List<Integer> sequence = new ArrayList<>();
        sequence.add(1);
        sequence.add(6);
        sequence.add(-1);
        sequence.add(10);

        System.out.println(isValidSubsequence(array, sequence));
    }
}
