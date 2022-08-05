/**
 * You are assigned to put some amount of boxes onto one truck. 
 * You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 * 
 * - numberOfBoxesi is the number of boxes of type i.
 * - numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * 
 * You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. 
 * You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.
 * 
 * Return the maximum total number of units that can be put on the truck.
 */
package Problems;

import java.util.PriorityQueue;

public class MaximumUnitsOnATruck {
    // solution using priorityQueue
    // time complexity: O(nlogn)
    // space complexity: O(n)
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int[] i : boxTypes) {
            pq.offer(i);
        }

        int res = 0;
        while(truckSize > 0 && pq.size() > 0) {
            int[] i = pq.poll();
            truckSize -= i[0];
            res += i[0] * i[1];
            if (truckSize < 0) {
                res-= (0 - truckSize) * i[1];
            }
        }
        return res;
    }

    // counting sort solution
    // time complexity: O(n)
    // space complexity: O(n)
    public static int anotherMaximumUnits(int[][] boxTypes, int truckSize) {
        int[] count = new int[1001];
        for (int[] box : boxTypes) {
            count[box[1]] += box[0];
        }
        int boxes = 0;
        for (int units = 1000; units > 0; --units) {
            if (count[units] > 0) {
                int fitIn = Math.min(count[units], truckSize);
                boxes += units * fitIn;
                truckSize -= fitIn;
                if (truckSize == 0) {
                    return boxes;
                }
            }
        }
        return boxes;
    }
    
    public static void main(String[] args) {
        int[][] boxTypes = {{1, 3}, {2, 2}, {3, 1}};
        int truckSize = 4;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }
}
