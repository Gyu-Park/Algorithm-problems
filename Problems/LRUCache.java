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

    final int MAXSIZE;
    Map<Integer, Integer> hashmap;
    Queue<Integer> keyIndex1;
    Queue<Integer> keyIndex2;

    public LRUCache(int capacity) {
        hashmap = new HashMap<>();
        MAXSIZE = capacity;
        keyIndex1 = new LinkedList<>();
        keyIndex2 = new LinkedList<>();
    }
    
    public int get(int key) {
        if (hashmap.containsKey(key)) {
            putKeyAtTheEnd(key, keyIndex1, keyIndex2);
            return hashmap.get(key);
        } else {
            return -1;
        }
    }

    private void putKeyAtTheEnd(int key, Queue<Integer> nonEmptyQueue, Queue<Integer> emptyQueue) {
        if (nonEmptyQueue.isEmpty() && !emptyQueue.isEmpty())
            putKeyAtTheEnd(key, emptyQueue, nonEmptyQueue);
        else {
            int lastKey = 0;
            while (!nonEmptyQueue.isEmpty()) {
                int tempKey = nonEmptyQueue.poll();
                if (tempKey == key) {
                    lastKey = tempKey;
                } else {
                    emptyQueue.add(tempKey);
                }
            }
            emptyQueue.add(lastKey);
        }
    }
    
    public void put(int key, int value) {
        if (hashmap.size() < MAXSIZE) {
            hashmap.put(key, value);
            if (!keyIndex1.isEmpty() || (keyIndex1.isEmpty() && keyIndex2.isEmpty())) {
                keyIndex1.add(key);
            } else if (!keyIndex2.isEmpty()) {
                keyIndex2.add(key);
            }
        } else {
            if (hashmap.containsKey(key)) {
                hashmap.put(key, value);
                putKeyAtTheEnd(key, keyIndex1, keyIndex2);
            } else {
                if (!keyIndex1.isEmpty()) {
                    hashmap.remove(keyIndex1.poll());
                    keyIndex1.add(key);
                } else {
                    hashmap.remove(keyIndex2.poll());
                    keyIndex2.add(key);
                }
                hashmap.put(key, value);

            }
        }
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
