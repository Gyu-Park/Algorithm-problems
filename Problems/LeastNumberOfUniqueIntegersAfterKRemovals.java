/**
 * Given an array of integers arr and an integer k. 
 * Find the least number of unique integers after removing exactly k elements.
 */
package Problems;

import java.util.*;

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    // solution using a hashmap with sortByValue method
    // time complexity: O(nlogn)
    // space complexity: O(n)
    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int res = 0;
        map = sortByValue(map);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k > 0) {
                k -= entry.getValue();
                if (k < 0) {
                    res++;
                }
                continue;
            }
            res++;
        }

        return res;
    }

    private static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> map) {
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> a,
                               Map.Entry<Integer, Integer> b)
            {
                return (a.getValue()).compareTo(b.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Integer, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : list) {
            temp.put(entry.getKey(), entry.getValue());
        }
        return temp;
    }

    // another solution using hashmap and priority queue
    // time complexity: O(nlogn)
    // space complexity: O(n)
    public static int anotherFindLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : arr)
            map.put(a, map.getOrDefault(a, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());
        while (k > 0)
            k -= pq.poll();
        
        return k < 0 ? pq.size() + 1 : pq.size(); 
    }

    // the most opimized solution using hashmap and priority queue
    // time complexity: O(n)
    // space complexity: O(n)
    public static int anotherFindLeastNumOfUniqueInts2(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int a : arr)
            map.put(a, map.getOrDefault(a, 0) + 1);

        int[] array = new int[arr.length + 1];
        int count = 0;
        for (int i : map.values()) {
            array[i]++;
            count++;
        }

        int occurrence = 0;
        int i = 0;
        for (i = 0; i < array.length; i ++) {
            occurrence = array[i];
            k -= occurrence * i;
            count -= array[i];
            if (k <= 0)
                break;
        }

        while (k < 0) {
            k += i;
            count++;
        }

        return count;
    }


    
    public static void main(String[] args) {
        int[] arr = {2, 1, 1, 3, 3, 3};
        int k = 3;
        System.out.println(anotherFindLeastNumOfUniqueInts2(arr, k));
    }
}
