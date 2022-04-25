/**
 * Given an array of integers temperatures represents the daily temperatures, 
 * return an array answer such that answer[i] is the number of days you have to wait 
 * after the ith day to get a warmer temperature. 
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
package Problems;

import java.util.*;

public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1)
            return new int[] {0};
        
        List<Integer> list = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] res = new int[temperatures.length];

        for (int i = 1; i < temperatures.length; i++) {
            if(temperatures[i] <= temperatures[i - 1]) {
                list.add(temperatures[i - 1]);
                if (map.containsKey(temperatures[i - 1])) {
                    map.get(temperatures[i - 1]).add(i - 1);
                } else {
                    List<Integer> storage = new ArrayList<>();
                    storage.add(i - 1);
                    map.put(temperatures[i - 1], storage);
                }
            }
            else {
                res[i - 1] = 1;
                if (list.size() > 0) {
                    while (list.size() > 0) {
                        int num = list.get(list.size() - 1);
                        if (temperatures[i] > num) {
                            List<Integer> tempList = map.get(num);
                            res[tempList.get(tempList.size() - 1)] = i - tempList.get(tempList.size() - 1);
                            map.get(num).remove(map.get(num).size() - 1);
                            list.remove(list.size() - 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return res;
    }

    // another solution using a stack
    public static int[] anotherDailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1)
            return new int[] {0};
        
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = 1; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            if (temperatures[i] > temperatures[i - 1]) {
                res[i - 1] = 1;
            } else {
                stack.add(i - 1);
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        int[] temperatures = { 89, 62, 70, 58, 47, 47, 46, 76, 100, 70 };
        int[] res = anotherDailyTemperatures(temperatures);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
