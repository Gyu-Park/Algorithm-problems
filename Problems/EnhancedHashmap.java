/**
 * You have created a programming language and now you have decided to add hashmap support to it. 
 * It was found that in common programming languages, it is impossible to add a number to all hashmap keys/values. 
 * So, you have decided to implement your own hashmap in your new language with following operations.
 * 
 * insert x y - insert and object with key x and value y
 * get x - return the value of an object with key x
 * addToKey x - add x to all keys in map
 * addToValue y - add y to all values in map
 * 
 * Your task is to implement this hashmap, apply the given queries, and to find the sum of all the results for get operations
 */
package Problems;

import java.util.*;

public class EnhancedHashmap {
    Map<Integer, Integer> map;

    public EnhancedHashmap() {
        map = new HashMap<>();
    }

    public void insert(int key, int value) {
        map.put(key, value);
    }

    public int get(int key) {
        return map.get(key);
    }

    public void addToKey(int x) {
        List<Integer> keySet = new ArrayList<>(map.keySet());
        for (int i = keySet.size()-1; i >= 0; i--) {
            int key = keySet.get(i);
            int value = map.get(key);
            map.put(key + x, value);
            map.remove(key);
        }
    }

    public void addToValue(int y) {
        List<Integer> keySet = new ArrayList<>(map.keySet());
        for (int key : keySet) {
            map.put(key, map.get(key) + y);
        }
    }

    public static void main(String[] args) {
        EnhancedHashmap map = new EnhancedHashmap();
        map.insert(1, 2);
        map.insert(2, 3);
        map.addToValue(2);
        map.addToKey(1);
        System.out.println(map.get(3));
    }
}
