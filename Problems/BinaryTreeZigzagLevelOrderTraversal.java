/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (i.e., from left to right, then right to left for the next level and alternate between).
 * 
 */
package Problems;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    // naive iterative solution using two stacks
    // time complexity O(n)
    // space complexity O(n)
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        int stackSwitch = 1;
        while (!stack1.empty() || !stack2.empty()) {
            TreeNode node = stackSwitch == 1 ? stack1.pop() : stack2.pop();
            list.add(node.val);

            if (stackSwitch == 1 && node.left != null)
                stack2.add(node.left);
            if (stackSwitch == 1 && node.right != null)
                stack2.add(node.right);
            if (stackSwitch == 2 && node.right != null)
                stack1.add(node.right);
            if (stackSwitch == 2 && node.left != null)
                stack1.add(node.left);

            if (stack1.empty() && !stack2.empty() && stackSwitch == 1) {
                stackSwitch = 2;
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
            } else if (!stack1.empty() && stack2.empty() && stackSwitch == 2) {
                stackSwitch = 1;
                res.add(new ArrayList<>(list));
                list = new ArrayList<>();
            }
        }

        if (list.size() > 0)
            res.add(list);

        return res;
    }

    // recursive solution
    // time complexity O(n)
    // space complexity O(n)
    public static List<List<Integer>> anotherZigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }

    private static void helper(TreeNode curr, List<List<Integer>> res, int level) {
        if (curr == null)
            return;

        if (res.size() <= level) {
            List<Integer> newLevel = new LinkedList<>();
            res.add(newLevel);
        }

        List<Integer> list = res.get(level);
        if (level % 2 == 0)
            list.add(curr.val);
        else
            list.add(0, curr.val);

        helper(curr.left, res, level + 1);
        helper(curr.right, res, level + 1);
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = zigzagLevelOrder(root);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
