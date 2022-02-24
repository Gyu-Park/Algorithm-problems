/**
 * Given a linked list, swap every two adjacent nodes and return its head. 
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 */
package Problems;

public class SwapNodesInPairs {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head = recurrsion(head.next.next, head.next, head);
        return head;
    }

    private static ListNode recurrsion(ListNode head, ListNode prev, ListNode curr) {
        prev.next = curr;
        if (head == null) {
            curr.next = head;
            return prev;
        } else if (head.next != null) {
            curr.next = head.next;
        } else {
            curr.next = head;
            return prev;
        }
        recurrsion(head.next.next, head.next, head);
        return prev;
    }

    // shorter solution
    public static ListNode shorterSwapPairs(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode node = head.next;
        head.next = swapPairs(head.next.next);
        node.next = head;
        return node;
    }

    // iterative solution - better memory usage
    public static ListNode iterSwapPairs(ListNode head) {
        if (head == null || head.next == null) 
            return head;
        ListNode curr = head;
        ListNode newHead = head.next;
        while (curr != null && curr.next != null) {
            ListNode tmp = curr;
            curr = curr.next;
            tmp.next = curr.next;
            curr.next = tmp;
            curr = tmp.next;
            if (curr != null && curr.next != null) 
                tmp.next = curr.next;
        }
        return newHead;
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
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        /**
        ListNode result = swapPairs(head);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        **/

        ListNode result2 = iterSwapPairs(head);
        while (result2 != null) {
            System.out.print(result2.val + " ");
            result2 = result2.next;
        }
    }
}
