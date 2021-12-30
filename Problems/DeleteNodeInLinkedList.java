/**
 * Write a function to delete a node in a singly-linked list. 
 * You will not be given access to the head of the list, 
 * instead you will be given access to the node to be deleted directly.
 * 
 * It is guaranteed that the node to be deleted is not a tail node in the list.
 */
package Problems;

public class DeleteNodeInLinkedList {
    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
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

        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        deleteNode(node1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
