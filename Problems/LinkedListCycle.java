/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * 
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 */
package Problems;

import java.util.*;

public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> isVisited = new HashSet<>();
        while (head != null) {
            if (isVisited.contains(head)) {
                return true;
            }
            isVisited.add(head);
            head = head.next;
        }
        return false;
    }

    // better solution using two pointers
    public static boolean betterHasCycle(ListNode head) {
        if (head == null)
            return false;
        ListNode oneStep = head;
        ListNode twoStep = head;
        while (twoStep.next != null && twoStep.next.next != null) {
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
            if (oneStep == twoStep)
                return true;
        }
        return false;
    }

    // better solution without pointers
    public boolean anotherBetterHasCycle(ListNode head) {
        while (head != null) {
            if (head.val != Integer.MAX_VALUE) {
                head.val = Integer.MAX_VALUE;
            } else {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;

        System.out.println(hasCycle(head));
    }
}
