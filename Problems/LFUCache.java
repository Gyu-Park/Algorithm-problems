/**
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * 
 * Implement the LFUCache class:
 * - LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * - int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * - void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. 
 *   When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. 
 *   For this problem, when there is a tie (i.e., two or more keys with the same frequency), 
 *   the least recently used key would be invalidated.
 * 
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. 
 * The key with the smallest use counter is the least frequently used key.
 * 
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). 
 * The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * 
 * The functions get and put must each run in O(1) average time complexity.
 */
package Problems;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {

    private final int MAXSIZE;
    private int leastUsedNum;
    private HashMap<Integer, LinkedHashSet<Integer>> countKeyMap; // count, key
    private HashMap<Integer, Integer> keyCountMap;                // key, count
    private HashMap<Integer, Integer> keyValMap;                  // key, val

    public LFUCache(int capacity) {
        countKeyMap = new HashMap<>();
        countKeyMap.put(1, new LinkedHashSet<>());
        keyCountMap = new HashMap<>();
        keyValMap = new HashMap<>();
        MAXSIZE = capacity;
        leastUsedNum = -1;
    }
    
    public int get(int key) {
        if(!keyValMap.containsKey(key))
            return -1;
        int count = keyCountMap.get(key);
        keyCountMap.put(key, count + 1);
        countKeyMap.get(count).remove(key);
        if(count == leastUsedNum && countKeyMap.get(count).size() == 0)
            leastUsedNum++;
        if(!countKeyMap.containsKey(count + 1))
            countKeyMap.put(count + 1, new LinkedHashSet<>());
        countKeyMap.get(count + 1).add(key);
        return keyValMap.get(key);
    }
    
    public void put(int key, int value) {
        if(MAXSIZE <= 0)
            return;
        if(keyValMap.containsKey(key)) {
            keyValMap.put(key, value);
            get(key);
            return;
        } 
        if(keyValMap.size() >= MAXSIZE) {
            int evit = countKeyMap.get(leastUsedNum).iterator().next();
            countKeyMap.get(leastUsedNum).remove(evit);
            keyValMap.remove(evit);
        }
        keyValMap.put(key, value);
        keyCountMap.put(key, 1);
        leastUsedNum = 1;
        countKeyMap.get(1).add(key);
    }
    
}
