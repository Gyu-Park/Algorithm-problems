/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, 
 * return the k closest points to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * 
 * You may return the answer in any order. 
 * The answer is guaranteed to be unique (except for the order that it is in).
 */
package Problems;

import java.util.*;

public class KClosestPointsToOrigin {
    // solution using priority queue
    // time complexity: O(nlogk)
    // space complexity: O(n)
    public static int[][] kClosest(int[][] points, int k) {
        if (points.length == 1)
            return points;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> (p2[0] * p2[0] + p2[1] * p2[1]) - (p1[0] * p1[0] + p1[1] * p1[1]));
        for (int[] i : points) {
            pq.offer(i);
            if (pq.size() > k)
                pq.poll();
        }

        int[][] res = new int[k][2];
        while (k > 0)
            res[--k] = pq.poll();
        
        return res;
    }

    // shortest solution
    // time complexity: O(nlogn)
    // spcae complexity: O(1)
    public static int[][] anotherKClosest(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

    // quick sort solution
    // time complexity: O(n)
    // space complexity: O(1)
    public static int[][] anotherKClosest2(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    
    private static int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) 
                r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) 
                l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }
    
    private static int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
    
    public static void main(String[] args) {
        int[][] points = {{-5, 3}, {-5, 8}, {-2, -8}, {-9, 7}, {1, -5}, {10, 3}, {8, -8}};
        int k = 6;
        int[][] res = anotherKClosest2(points, k);
        for (int[] i : res) {
            for(int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
