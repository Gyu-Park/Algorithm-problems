/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * 
 * Implement the LRUCache class:
 * 
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. 
 * Otherwise, add the key-value pair to the cache. 
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
package Problems;

import java.util.*;

public class LRUCache {

    private final int MAXSIZE;
    private LinkedHashMap<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        MAXSIZE = capacity;
        linkedHashMap = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (linkedHashMap.containsKey(key)) {
            int value = linkedHashMap.remove(key);
            linkedHashMap.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.remove(key);
        } else if (linkedHashMap.size() >= MAXSIZE) {
            linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
        }
        linkedHashMap.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(1, 1);
        obj.put(2, 2);
        System.out.println(obj.get(1));
        obj.put(3, 3);
        System.out.println(obj.get(2));
        obj.put(4, 4);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}
