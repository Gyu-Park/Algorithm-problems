/**
 * Implement a last-in-first-out (LIFO) stack using only two queues. 
 * The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).
 * 
 * Implement the MyStack class:
 * 
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * 
 * Notes:
 * 
 * You must use only standard operations of a queue, which means that only push to back, 
 * peek/pop from front, size and is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. 
 * You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 */
package Problems;

import java.util.*;

public class ImplementStackUsingQueues {
    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public ImplementStackUsingQueues() {

    }

    public void push(int x) {
        if (queue2.size() == 0) {
            queue1.add(x);
        } else {
            queue2.add(x);
        }
    }

    public int pop() {
        int a = 0;

        if (queue1.size() == 1) {
            return queue1.poll();
        } else if (queue2.size() == 1) {
            return queue2.poll();
        }

        if (queue1.size() != 0) {
            for (int i = 0; i < queue1.size(); i++) {
                a = queue1.poll();
                queue2.add(a);
            }
            return queue1.poll();
        } else {
            for (int i = 0; i < queue2.size(); i++) {
                a = queue2.poll();
                queue1.add(a);
            }
            return queue2.poll();
        }
    }

    public int top() {
        if (queue1.size() != 0) {
            Iterator<Integer> iter = queue1.iterator();
            for (int i = 0; i < queue1.size() - 1; i++) {
                iter.next();
            }
            return iter.next();
        } else if (queue2.size() != 0) {
            Iterator<Integer> iter = queue2.iterator();
            for (int i = 0; i < queue2.size() - 1; i++) {
                iter.next();
            }
            return iter.next();
        }
        return 0;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    public static void main(String[] args) {
        ImplementStackUsingQueues obj = new ImplementStackUsingQueues();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.pop());
        System.out.println(obj.empty());
    }
}
