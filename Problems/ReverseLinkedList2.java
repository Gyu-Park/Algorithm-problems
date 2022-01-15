package Problems;

public class ReverseLinkedList2 {
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = new ListNode(0);
        ListNode curr = head;
        head = head.next.next;
        prev.next = curr;
        prev = prev.next;
        curr = prev.next;
        prev.next = null;
        curr.next = prev;

        while (head != null) {
            prev = curr;
            curr = head;
            head = head.next;
            curr.next = prev;
        }

        return curr;
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

        ListNode res = reverseList(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
