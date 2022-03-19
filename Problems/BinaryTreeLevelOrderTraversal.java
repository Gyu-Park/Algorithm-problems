/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. 
 * (i.e., from left to right, level by level).
 */
package Problems;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
    // bfs solution
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        int numNodeInLevel = 1;
        int count = numNodeInLevel;
        queue.add(root);

        while (!queue.isEmpty()) {
            if (count == 0) {
                res.add(new LinkedList<>(list));
                list = new LinkedList<>();
                numNodeInLevel *= 2;
                count = numNodeInLevel;
            }
            TreeNode node = queue.poll();
            if (node == null) {
                numNodeInLevel--;
                count--;
                continue;
            } else {
                list.add(node.val);
            }
            queue.add(node.left);
            queue.add(node.right);
            count--;
        }

        return res;
    }

    // more concise bfs solution
    public static List<List<Integer>> anotherLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    // dfs solution
    public static List<List<Integer>> anotherLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        recursion(root, res, 1);
        return res;
    }

    private static void recursion(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null)
            return;
        if (level > res.size())
            res.add(new LinkedList<Integer>());
        res.get(level - 1).add(root.val);

        recursion(root.left, res, level + 1);
        recursion(root.right, res, level + 1);
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
        TreeNode q4 = new TreeNode(7);
        TreeNode q3 = new TreeNode(15);
        TreeNode q2 = new TreeNode(20, q3, q4);
        TreeNode q1 = new TreeNode(9);
        TreeNode root = new TreeNode(3, q1, q2);
        List<List<Integer>> res = levelOrder(root);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
