/**
 * Given the head of a linked list and an integer val, 
 * remove all the nodes of the linked list that has Node.val == val, and return the new head.
 */
package Problems;

public class RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        ListNode prev = new ListNode(0);
        prev.next = head;
        ListNode curr = prev;
        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return prev.next;
    }

    // recursive solution, but it's slower
    public ListNode recursiveRemoveElements(ListNode head, int val) {
        if (head == null)
            return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
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

        int val = 6;
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        head = removeElements(head, val);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
