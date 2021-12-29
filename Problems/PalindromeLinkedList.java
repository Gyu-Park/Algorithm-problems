/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 */
package Problems;

import java.util.Stack;

public class PalindromeLinkedList {
    // using reverse linkedlist but it modifies the original list
    public static boolean isPalindrome(ListNode head) {
        if (head.next == null)
            return true;

        ListNode fast = head;
        ListNode reverse = null;
        while (fast != null) {
            if (fast.next == null) {
                head = head.next;
                break;
            } else {
                fast = fast.next.next;
            }

            ListNode next = head.next;
            head.next = reverse;
            reverse = head;
            head = next;
        }

        while (reverse != null) {
            if (reverse.val != head.val)
                return false;
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }

    // solution using a stack and it does not modify the original list
    public static boolean usingStackIsPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        Stack<ListNode> stack = new Stack<>();

        while (fast != null && fast.next != null) {
            stack.push(slow);
            fast = fast.next.next;
            slow = slow.next;
        }

        // if odd length, move one forward away from middle element
        if (fast != null)
            slow = slow.next;

        // compare second half of the list to stack
        while (slow != null) {
            if (stack.pop().val != slow.val)
                return false;
            slow = slow.next;
        }

        return true;
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
        ListNode node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        System.out.println(usingStackIsPalindrome(head));
        System.out.println(isPalindrome(head));
    }
}
