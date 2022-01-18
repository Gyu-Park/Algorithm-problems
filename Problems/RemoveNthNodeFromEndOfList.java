/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 */
package Problems;

public class RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1)
            return null;
        ListNode node = head;
        int count = 0;
        while (node != null) {
            node = node.next;
            count++;
        }

        n = count - n;
        count = 1;
        ListNode curr = head;
        while (true) {
            if (count == n) {
                curr.next = curr.next.next;
                break;
            } else if (count > n) {
                head = head.next;
                break;
            }
            count++;
            curr = curr.next;
        }
        return head;
    }

    // "in one pass" solution
    public static ListNode anotherRemoveNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start;
        ListNode fast = start;
        slow.next = head;

        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        return start.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode newHead = anotherRemoveNthFromEnd(head, 2);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}
