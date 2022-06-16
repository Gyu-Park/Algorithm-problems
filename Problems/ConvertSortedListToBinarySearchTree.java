package Problems;

import java.util.*;

public class ConvertSortedListToBinarySearchTree {
    // naive solution (because of subList)
    // time complexity O(n)
    // space complexity O(height)
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return helper(list);
    }

    private static TreeNode helper(List<Integer> list) {
        if (list.size() == 0)
            return null;
        int centerIndex = list.size() / 2;
        TreeNode root = new TreeNode(list.get(centerIndex));
        if (list.size() == 1)
            return root;
        root.left = helper(list.subList(0, centerIndex));
        root.right = helper(list.subList(centerIndex + 1, list.size()));
        return root;
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
        ListNode head = new ListNode(-10);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(9);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        TreeNode res = sortedListToBST(head);
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        int level = 0;
        queue.offer(res);
        levelQueue.offer(level);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int nodeLevel = levelQueue.poll();
            if (nodeLevel == level) {
                System.out.print(node.val + " ");
            } else {
                System.out.println(node.val + " ");
                level = nodeLevel;
            }

            if (node.left != null) {
                queue.offer(node.left);
                levelQueue.offer(nodeLevel + 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                levelQueue.offer(nodeLevel + 1);
            }
        }
    }
}
