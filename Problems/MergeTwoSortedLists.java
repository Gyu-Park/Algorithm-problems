/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * 
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * 
 * Return the head of the merged linked list.
 */

package Problems;

public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null)
            return null;
        if (list1 == null && list2 != null)
            return list2;
        if (list2 == null && list1 != null)
            return list1;

        ListNode currentNode = new ListNode();
        ListNode head = new ListNode();
        int count = 0;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null && list1.val <= list2.val) {
                currentNode.val = list1.val;
                list1 = list1.next;
            } else if (list1 != null && list2 != null && list1.val > list2.val) {
                currentNode.val = list2.val;
                list2 = list2.next;
            }

            if (list1 != null || list2 != null) {
                currentNode.next = new ListNode();
                if (count == 0) {
                    head = currentNode;
                    count++;
                }
                currentNode = currentNode.next;
            }
            if (list1 == null && list2 != null) {
                currentNode.val = list2.val;
                list2 = list2.next;
            } else if (list1 != null && list2 == null) {
                currentNode.val = list1.val;
                list1 = list1.next;
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
        // list1 = [1,2,4], list2 = [1,3,4]
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();
        ListNode list3 = new ListNode();
        list1.val = 1;
        list2.val = 2;
        list3.val = 4;
        list1.next = list2;
        list2.next = list3;

        ListNode list4 = new ListNode();
        ListNode list5 = new ListNode();
        ListNode list6 = new ListNode();
        list4.val = 1;
        list5.val = 3;
        list6.val = 4;
        list4.next = list5;
        list5.next = list6;

        ListNode result = new ListNode();
        result = mergeTwoLists(list1, list4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
