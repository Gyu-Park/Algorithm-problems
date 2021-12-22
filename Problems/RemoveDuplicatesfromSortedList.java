/**
 * Given the head of a sorted linked list, 
 * delete all duplicates such that each element appears only once. 
 * Return the linked list sorted as well.
 */
package Problems;

public class RemoveDuplicatesfromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;

        ListNode startNode = head;
        while (head.next != null) {
            if (head.val == head.next.val) {
                if (head.next.next != null)
                    head.next = head.next.next;
                else
                    head.next = null;
            } else {
                head = head.next;
            }
        }

        return startNode;
    }

    // Recursive version, but it will run out of stack space when there're too many
    // elements. It is creative solution though.
    public ListNode deleteDuplicatesRecursionSolution(ListNode head) {
        if (head == null || head.next == null)
            return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node4 = new ListNode(3);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = new ListNode(1, node1);
        head = deleteDuplicates(head);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
