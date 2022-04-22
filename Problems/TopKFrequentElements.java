/**
 * Given an integer array nums and an integer k, 
 * return the k most frequent elements. 
 * 
 * You may return the answer in any order.
 */
package Problems;

import java.util.*;

public class TopKFrequentElements {
    // a solution using a HashMap and TreeMap O(nlogn)
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

    // slower solution using two hash sets and one list 
    // O(n^m) n = nums.length, m = unique numbers in nums
    private static int[] anotherTopKFrequent(int[] nums, int k) {
        if (nums.length == 1)
            return new int[] {nums[0]};
        List<Set<Integer>> list = new ArrayList<>();
        list.add(new HashSet<>());
        Set<Integer> isContains = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (isContains.add(nums[i])) {
                list.get(0).add(nums[i]);
            }
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).contains(nums[i]) && list.size() - 1 == j) {
                    list.get(j).remove(nums[i]);
                    list.add(new HashSet<>());
                    list.get(j + 1).add(nums[i]);
                    break;
                } else if (list.get(j).contains(nums[i]) && list.size() - 1 > j) {
                    list.get(j).remove(nums[i]);
                    list.get(j + 1).add(nums[i]);
                    break;
                }
            }
        }

        int[] res = new int[k];
        int index = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Set<Integer> set = list.get(i);
            Iterator<Integer> iter = set.iterator();
            while (iter.hasNext()) {
                res[index++] = iter.next();
                if (index == res.length)
                    return res;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3, 2, 4, 2, 3, 3 };
        int k = 3;
        int[] res = topKFrequent(nums, k);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
