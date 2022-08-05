/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * 
 * Merge all the linked-lists into one sorted linked-list and return it.
 */
package Problems;

import java.util.PriorityQueue;

public class MergekSortedLists {
    // naive solution
    // time complexity: O(kn^2)
    // space complexity: O(n)
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        if (lists.length == 0)
            return head;
        head = lists[0];
        if (lists.length == 1)
            return head;
        
        for (int i = 1; i < lists.length; i++) {
            head = merge(head, lists[i]);
        }
        
        return head;
    }
    
    private static ListNode merge(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        head.next = node1;
        while (node1 != null && node2 != null) {
            if (node1.val > node2.val) {
                pre.next = node2;
                node2 = node2.next;
                pre = pre.next;
                pre.next = node1;
            } else if (node1.val < node2.val) {
                node1 = node1.next;
                pre = pre.next;
            } else {
                node1 = node1.next;
                pre = pre.next;

                pre.next = node2;
                node2 = node2.next;
                pre = pre.next;
                pre.next = node1;
            }
        }

        if (node2 != null) {
            pre.next = node2;
        }

        return head.next;
    }

    // another solution using PriorityQueue
    // time complexity: O(nlogk) where n is all the nodes and k is lists.length
    // spcae complexity: O(k)
    public ListNode anotherMergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;
        
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length, (a,b)-> a.val-b.val);
        
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;
        
        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;
        ListNode[] lists = {node1, node4, node7};
        ListNode res = mergeKLists(lists);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }
}
