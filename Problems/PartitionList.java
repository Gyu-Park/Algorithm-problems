/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
package Problems;

public class PartitionList {
    // time complexity O(n)
    // space complexity O(n)
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null || x <= -100)
            return head;

        ListNode firstDummy = new ListNode(0);
        ListNode firstList = new ListNode(0);
        firstDummy = firstList;

        ListNode secondDummy = new ListNode(0);
        ListNode secondList = new ListNode(0);
        secondDummy = secondList;

        while (head != null) {
            if (head.val < x) {
                firstList.next = head;
                firstList = firstList.next;
            } else {
                secondList.next = head;
                secondList = secondList.next;
            }
            head = head.next;
        }

        secondList.next = null;
        firstList.next = secondDummy.next;

        return firstDummy.next;
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
        int x = 3;
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(2);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode res = partition(head, x);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
