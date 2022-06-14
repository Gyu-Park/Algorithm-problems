/**
 * Given the head of a singly linked list and two integers left and right where left <= right, 
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 */
package Problems;

public class ReverseLinkedListII {
    // Long line solution
    // time complexity: O(n)
    // space complexity: O(1)
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;

        int count = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode start = null;
        ListNode beforeStart = null;

        while (head == null || head.next != null) {
            if (left == 1) {
                beforeStart = dummy;
            }

            if (count + 1 == left) {
                beforeStart = head;
                head = head.next;
                count++;
            } else if (count == left) {
                start = head;
                head = head.next;
                start.next = null;
                ListNode prev = head;
                head = head.next;
                prev.next = start;
                count += 2;
                while (count < right) {
                    ListNode curr = head;
                    head = head.next;
                    curr.next = prev;
                    prev = head;
                    head = head.next;
                    prev.next = curr;
                    count += 2;
                }

                if (count == right) {
                    ListNode curr = head;
                    head = head.next;
                    curr.next = prev;
                    beforeStart.next = curr;
                } else {
                    beforeStart.next = prev;
                }
                start.next = head;
                break;
            } else {
                head = head.next;
                count++;
            }
        }

        return dummy.next;
    }

    // more concise, simple solution
    // time complexity: O(n)
    // space complexity: O(1)
    public static ListNode anotherReverseBetween(ListNode head, int left, int right) {
        if (head == null)
            return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 0; i < left - 1; i++)
            pre = pre.next;

        ListNode start = pre.next;
        ListNode then = start.next;

        // start reverse the list
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        return dummy.next;
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

        int left = 2;
        int right = 4;

        head = reverseBetween(head, left, right);

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
