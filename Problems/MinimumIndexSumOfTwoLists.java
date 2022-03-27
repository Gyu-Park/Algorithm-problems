/**
 * Suppose Andy and Doris want to choose a restaurant for dinner, 
 * and they both have a list of favorite restaurants represented by strings.
 * You need to help them find out their common interest with the least list index sum. 
 * If there is a choice tie between answers, output all of them with no order requirement. 
 * You could assume there always exists an answer.\
 */
package Problems;

import java.util.*;

public class MinimumIndexSumOfTwoLists {
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i]) && indexSum > map.get(list2[i]) + i) {
                indexSum = map.get(list2[i]) + i;
                queue.clear();
                queue.add(list2[i]);
            } else if (map.containsKey(list2[i]) && indexSum == map.get(list2[i]) + i) {
                queue.add(list2[i]);
            }
        }

        String[] res = new String[queue.size()];
        int count = 0;
        while (!queue.isEmpty()) {
            res[count++] = queue.poll();
        }

        return res;
    }

    // same algoritm, but shorter and faster solution
    public String[] anotherFindRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new LinkedList<>();

        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            Integer j = map.get(list2[i]);
            if (j != null && i + j <= minSum) {
                if (i + j < minSum) {
                    res.clear();
                    minSum = i + j;
                }
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args) {
        String[] list1 = { "Shogun", "Tapioca Express", "Burger King", "KFC" };
        String[] list2 = { "Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun" };
        String[] res = findRestaurant(list1, list2);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
