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

public class InsertInterval {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0)
            return new int[][] { { newInterval[0], newInterval[1] } };

        int k = 2;
        int i;
        for (i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > newInterval[0]) {
                k = 0;
                break;
            } else if (intervals[i][1] > newInterval[0]) {
                k = 1;
                break;
            }
        }

        int kk = 2;
        int j;
        for (j = i; j < intervals.length; j++) {
            if (intervals[j][0] > newInterval[1]) {
                kk = 0;
                j--;
                break;
            } else if (intervals[j][1] > newInterval[1]) {
                kk = 1;
                break;
            }
        }

        int newLen = i + (intervals.length - j);
        int[][] res = new int[newLen][2];
        int ii;
        for (ii = 0; ii < res.length; ii++) {
            if (ii == i) {
                if (k == 0)
                    res[ii][0] = newInterval[0];
                else if (k == 1)
                    res[ii][0] = intervals[ii][0];

                if (kk == 0)
                    res[ii][1] = newInterval[1];
                else if (kk == 1)
                    res[ii][1] = intervals[j][1];
                else if (kk == 2) {
                    res[ii][1] = newInterval[1];
                    return res;
                }
                break;
            }
            res[ii][0] = intervals[ii][0];
            res[ii][1] = intervals[ii][1];
        }

        for (int jj = j + 1; jj < intervals.length; jj++) {
            res[++ii][0] = intervals[jj][0];
            res[ii][1] = intervals[jj][1];
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval = { 4, 8 };
        int[][] intervals2 = { { 1, 3 }, { 6, 9 } };
        int[] newInterval2 = { 2, 5 };
        int[][] res = insert(intervals, newInterval);
        for (int i = 0; i < res.length; i++) {
            System.out.print(" [" + res[i][0] + ", " + res[i][1] + "] ");
        }
    }
}
