/**
 * Given an integer array nums and an integer k, 
 * return the k most frequent elements. 
 * 
 * You may return the answer in any order.
 */
package Problems;

import java.util.*;

public class TopKFrequentElements {
    // a solution using a HashMap and TreeMap
    public static int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 1)
            return new int[] {nums[0]};

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Map<Integer, List<Integer>> freqMap = new TreeMap<>(Collections.reverseOrder());
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (freqMap.containsKey(freq)) {
                freqMap.get(freq).add(key);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(key);
                freqMap.put(freq, list);
            }
        }

        int[] res = new int[k];
        Iterator<Integer> iter = freqMap.keySet().iterator();
        int index = 0;
        while(index < res.length) {
            List<Integer> list = freqMap.get(iter.next());
            for (int i : list) {
                res[index++] = i;
                if (index == res.length)
                    break;
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3, 2, 2, 2, 3, 3, 3, 3, 4, 5, 5, 6, 6 };
        int k = 3;
        int[] res = topKFrequent(nums, k);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
