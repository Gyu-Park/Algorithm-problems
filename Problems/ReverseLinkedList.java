/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
package Problems;

public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev1 = head;
        ListNode prev2 = head.next;
        head = head.next.next;
        prev1.next = null;
        while (head != null) {
            prev2.next = prev1;
            prev1 = prev2;
            prev2 = head;
            head = head.next;
        }
        prev2.next = prev1;
        head = prev2;

        return head;
    }

    public static ListNode shorterReverseList(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    // recursive solution
    public static ListNode recursiveReverseList(ListNode head) {
        return recursion(head, null);
    }

    private static ListNode recursion(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return recursion(next, head);
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

        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode result = reverseList(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

        System.out.println(" ");

        head = new ListNode(1);
        node1 = new ListNode(2);
        node2 = new ListNode(3);
        node3 = new ListNode(4);
        node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ListNode result2 = shorterReverseList(head);
        while (result2 != null) {
            System.out.print(result2.val + " ");
            result2 = result2.next;
        }

        head = new ListNode(1);
        node1 = new ListNode(2);
        node2 = new ListNode(3);
        node3 = new ListNode(4);
        node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(" ");

        ListNode result3 = recursiveReverseList(head);
        while (result3 != null) {
            System.out.print(result3.val + " ");
            result3 = result3.next;
        }
    }
}
