/**
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order, and each of their nodes contains a single digit. 
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself. 
**/
package Problems;

public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int sum = 0;
        int count = 0;
        ListNode node = new ListNode();
        ListNode head = new ListNode();

        while (l1 != null || l2 != null || carry == 1) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum = sum + carry;
            if (sum >= 10) {
                carry = 1;
                sum = sum - 10;
            } else {
                carry = 0;
            }
            node.val = sum;
            sum = 0;
            if (l1 == null && l2 == null && carry == 0) {
                node.next = null;
                if (count == 0) {
                    head = node;
                    count++;
                }
            } else {
                node.next = new ListNode();
                if (count == 0) {
                    head = node;
                    count++;
                }
                node = node.next;
            }
        }
        return head;
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
        ListNode l1 = new ListNode();
        ListNode a1 = new ListNode();
        ListNode a2 = new ListNode();
        ListNode a3 = new ListNode();
        ListNode a4 = new ListNode();
        ListNode a5 = new ListNode();
        ListNode a6 = new ListNode();
        l1.val = 9;
        a1.val = 9;
        a2.val = 9;
        a3.val = 9;
        a4.val = 9;
        a5.val = 9;
        a6.val = 9;
        l1.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        a5.next = a6;
        a6.next = null;

        ListNode l2 = new ListNode();
        ListNode b1 = new ListNode();
        ListNode b2 = new ListNode();
        ListNode b3 = new ListNode();
        l2.val = 9;
        b1.val = 9;
        b2.val = 9;
        b3.val = 9;
        l2.next = b1;
        b1.next = b2;
        b2.next = b3;
        b3.next = null;

        ListNode result = addTwoNumbers(l1, l2);
    }
}
