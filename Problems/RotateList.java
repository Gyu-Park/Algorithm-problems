package Problems;

public class RotateList {
    // time complexity O(n)
    // space complexity O(1)
    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null)
            return head;
        ListNode newHead = head;
        int n = 1;
        while (newHead.next != null) {
            newHead = newHead.next;
            n++;
        }

        if (n < k)
            k %= n;
        if (n == k)
            return head;
        newHead.next = head;
        newHead = newHead.next;
        for (int i = 1; i < n - k; i++) {
            head = head.next;
        }

        newHead = head.next;
        head.next = null;
        return newHead;
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
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        // ListNode node3 = new ListNode(4);
        // ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        // node2.next = node3;
        // node3.next = node4;
        int k = 10;
        head = rotateRight(head, k);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
