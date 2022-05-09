/**
 * You are given an array of non-overlapping intervals intervals 
 * where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. 
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * 
 * Insert newInterval into intervals such that intervals is still sorted in ascending order 
 * by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 */
package Problems;

import java.util.*;

public class InsertInterval {
    // time complexity O(n) where n is intervals.length.
    // space complexity O(k) where k is result array's length.
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // corner cases
        if (intervals.length == 0)
            return new int[][] { { newInterval[0], newInterval[1] } };
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            intervals = Arrays.copyOf(intervals, intervals.length + 1);
            intervals[intervals.length - 1] = new int[2];
            intervals[intervals.length - 1][0] = newInterval[0];
            intervals[intervals.length - 1][1] = newInterval[1];
            return intervals;
        }

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        int i;
        for (i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > newInterval[0]) {
                queue1.add(newInterval[0]);
                break;
            } else if (intervals[i][1] >= newInterval[0]) {
                queue1.add(intervals[i][0]);
                break;
            }
            queue1.add(intervals[i][0]);
            queue2.add(intervals[i][1]);
        }

        int j;
        boolean found = false;
        for (j = i; j < intervals.length; j++) {
            if (intervals[j][0] > newInterval[1] && !found) {
                queue2.add(newInterval[1]);
                queue1.add(intervals[j][0]);
                queue2.add(intervals[j][1]);
                found = true;
            } else if (intervals[j][1] >= newInterval[1] && !found) {
                queue2.add(intervals[j][1]);
                found = true;
            } else if (j == intervals.length - 1 && intervals[j][1] < newInterval[1]) {
                queue2.add(newInterval[1]);
            } else if (found) {
                queue1.add(intervals[j][0]);
                queue2.add(intervals[j][1]);
            }
        }

        int[][] res = new int[queue1.size()][2];
        for (int k = 0; k < res.length; k++) {
            res[k][0] = queue1.poll();
            res[k][1] = queue2.poll();
        }
        return res;
    }

    // more readable and concise solution with while loops
    // time complexity O(n)
    // spcae complexity O(n)
    public int[][] anotherInsert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;

        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // add the union of intervals we got
        result.add(newInterval);

        // add all the rest
        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval = { 4, 8 };
        int[][] res = insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(" [" + res[i][0] + ", " + res[i][1] + "] ");
        }
    }
}
