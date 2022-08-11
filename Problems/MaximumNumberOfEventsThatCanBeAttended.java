/**
 * You are given an array of events where events[i] = [startDayi, endDayi]. 
 * Every event i starts at startDayi and ends at endDayi.
 * 
 * You can attend an event i at any day d where startTimei <= d <= endTimei. 
 * You can only attend one event at any time d.
 * 
 * Return the maximum number of events you can attend.
 */
package Problems;

import java.util.*;

public class MaximumNumberOfEventsThatCanBeAttended {
    // solution using priority queue
    // time complexity: O(nlogn)
    // space complexity: O(n);
    public static int maxEvents(int[][] events) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int i = 0;
        int res = 0;
        int day = 0;
        int n = events.length;
        while (!pq.isEmpty() || i < n) {
            // first set the day
            if (pq.isEmpty())
                day = events[i][0];
            // put end day into pq when start day is equal or less than the day
            while (i < n && events[i][0] <= day)
                pq.offer(events[i++][1]);
            // pop one event
            pq.poll();
            ++res; ++day;
            // if end day is already passed, throw it out
            while (!pq.isEmpty() && pq.peek() < day)
                pq.poll();
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[][] events = {{1, 10}, {2, 2}, {2, 2}, {2, 2}, {2, 2}};
        System.out.println(maxEvents(events));
    }
}
