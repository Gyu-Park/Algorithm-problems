/**
 * You are given a nested list of integers nestedList. 
 * Each element is either an integer or a list whose elements may also be integers or other lists. 
 * Implement an iterator to flatten it.
 * 
 * Implement the NestedIterator class:
 * 
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * 
 * Your code will be tested with the following pseudocode:
 * 
 *      initialize iterator with nestedList
 *      res = []
 *      while iterator.hasNext()
 *          append iterator.next() to the end of res
 *      return res
 * 
 * If res matches the expected flattened list, then your code will be judged as correct.
 */
package Problems;

import java.util.*;

public class NestedIterator implements Iterator<Integer> {
    Iterator<Integer> iter;

    public NestedIterator(List<NestedInteger> nestedList) {
        List<Integer> list = new ArrayList<>();
        getIntInList(nestedList, list);
        iter = list.iterator();
    }
    
    private void getIntInList(List<NestedInteger> nestedList, List<Integer> list) {
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                list.add(ni.getInteger());
                continue;
            }
            getIntInList(ni.getList(), list);
        }
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    class NestedInteger {
        public boolean isInteger() {
            return true;
        }

        public int getInteger() {
            return 0;
        }

        public List<NestedInteger> getList() {
            return null;
        }

    }
    
}
