/**
 * Given an array of integers temperatures represents the daily temperatures, 
 * return an array answer such that answer[i] is the number of days you have to wait 
 * after the ith day to get a warmer temperature. 
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
package Problems;

import java.util.*;

public class DailyTemperatures {
    // a solution using a stack
    // time complexity O(n)
    // space complexity O(n)
    public static int[] dailyTemperatures(int[] temperatures) {
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

    // another solution without using a stack
    // time complexity O(n)
    // space complexity O(2n)
    public static int[] anotherDailyTemperatures(int[] temperatures) {
        if (temperatures.length == 1)
            return new int[] {0};
        
        int[] storage = new int[temperatures.length];
        int[] res = new int[temperatures.length];
        int endOfStorage = -1;
        for (int i = 1; i < temperatures.length; i++) {
            while (endOfStorage >= 0 && temperatures[storage[endOfStorage]] < temperatures[i]) {
                int index = storage[endOfStorage--];
                res[index] = i - index;
            }
            if (temperatures[i] > temperatures[i - 1]) {
                res[i - 1] = 1;
            } else {
                storage[++endOfStorage] = i - 1;
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
