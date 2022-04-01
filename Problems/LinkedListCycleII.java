/**
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
 * Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). 
 * It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 * 
 * Do not modify the linked list.
 */
package Problems;

import java.util.*;

public class LinkedListCycleII {
    public static ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (!set.contains(node)) {
            set.add(node);
            if (node == null || node.next == null)
                return null;
            node = node.next;
        }
        return node;
    }

    /**
     * solution using Floyd Cycle Algorithm
     * 
     * Traverse linked list using two pointers.
     * Move one pointer(slow_p) by one and another pointer(fast_p) by two.
     * If these pointers meet at the same node then there is a loop. If pointers do
     * not meet then linked list doesn’t have a loop.
     */
    public static ListNode anotherDetectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next;
            if (slow == fast)
                break;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
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

        System.out.println(anotherDetectCycle(head).val);
    }
}
