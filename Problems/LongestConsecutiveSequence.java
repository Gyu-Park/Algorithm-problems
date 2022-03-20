/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * 
 * You must write an algorithm that runs in O(n) time.
 */
package Problems;

import java.util.*;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return 1;

        Set<Integer> set = new TreeSet<>();
        // when putting values into a TreeSet, it takes O(logn) time
        for (int num : nums)
            set.add(num);

        Iterator<Integer> iter = set.iterator();
        int preNum = iter.next();
        int count = 1;
        int res = 0;
        while (iter.hasNext()) {
            int curNum = iter.next();
            if (curNum - preNum == 1) {
                count++;
                preNum = curNum;
            } else {
                res = Math.max(res, count);
                count = 1;
                preNum = curNum;
            }
        }

        return res = Math.max(res, count);
    }

    // O(n) solution using a hashmap
    public static int anotherLongestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                continue;
            } else {
                int left = map.containsKey(i - 1) ? map.get(i - 1) : 0;
                int right = map.containsKey(i + 1) ? map.get(i + 1) : 0;
                int len = left + right + 1;
                res = Math.max(res, len);
                map.put(i, len);

                if (left > 0)
                    map.put(i - left, len);
                if (right > 0)
                    map.put(i + right, len);
            }
        }
        return res;
    }

    // O(n) solution using a hashset
    public static int anotherLongestConsecutive2(int[] nums) {
        if (nums.length == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        int res = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
                int storeNum = num;
                int left = 0;
                int right = 0;

                while (set.contains(--num)) {
                    set.remove(num);
                    left++;
                }
                num = storeNum;
                while (set.contains(++num)) {
                    set.remove(num);
                    right++;
                }
                res = Math.max(res, left + right + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = { 0, 3, 7, 2, 5, 8, 4, 6, 0, 1 };
        System.out.println(longestConsecutive(nums));
        System.out.println(anotherLongestConsecutive(nums));
    }
}
