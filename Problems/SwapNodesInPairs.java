/**
 * Given a linked list, swap every two adjacent nodes and return its head. 
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
package Problems;

public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode prev = head.next;
        ListNode curr = head;
        head = head.next;
        prev.next = head;
        dummy.next = prev;

        while (head != null) {
            
        }
        return dummy;
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
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode result = swapPairs(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
