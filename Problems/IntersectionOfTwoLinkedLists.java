/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. 
 * If the two linked lists have no intersection at all, return null.
 */
package Problems;

import java.util.*;

public class IntersectionOfTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (set.contains(headA)) {
                    return headA;
                }
                set.add(headA);
                headA = headA.next;
            }

            if (headB != null) {
                if (set.contains(headB)) {
                    return headB;
                }
                set.add(headB);
                headB = headB.next;
            }
        }

        return null;
    }

    // better version
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
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

        ListNode headA = new ListNode(1);
        ListNode aNode1 = new ListNode(9);
        ListNode aNode2 = new ListNode(1);
        ListNode headB = new ListNode(3);
        ListNode iNode1 = new ListNode(2);
        ListNode iNode2 = new ListNode(4);
        headA.next = aNode1;
        aNode1.next = aNode2;
        aNode2.next = iNode1;
        headB.next = iNode1;
        iNode1.next = iNode2;

        System.out.println(getIntersectionNode(headA, headB).val);
        System.out.println(getIntersectionNode2(headA, headB).val);
    }
}
