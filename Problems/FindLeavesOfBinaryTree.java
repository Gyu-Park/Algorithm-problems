/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 * 
 *  - Collect all the leaf nodes.
 *  - Remove all the leaf nodes.
 *  - Repeat until the tree is empty.
 */
package Problems;

import java.util.*;

public class FindLeavesOfBinaryTree {
    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<>());
            res.get(0).add(root.val);
            return res;
        }
        while (root.left != null || root.right != null) {
            res.add(new ArrayList<>());
            helper(res, root);
        }
        res.add(new ArrayList<>());
        res.get(res.size() - 1).add(root.val);
        return res;
    }

    private static void helper(List<List<Integer>> res, TreeNode root) {
        if (root.left != null && root.left.left == null && root.left.right == null) {
            res.get(res.size() - 1).add(root.left.val);
            root.left = null;
        } else if (root.left != null) {
            helper(res, root.left);
        }
        if (root.right != null && root.right.left == null && root.right.right == null) {
            res.get(res.size() - 1).add(root.right.val);
            root.right = null;
        } else if (root.right != null) {
            helper(res, root.right);
        }
    }

    // bottom-up solution
    // time complexity: O(n)
    // space complexity: O(n)
    public List<List<Integer>> findLeaves2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        findLeavesHelper(list, root);
        return list;
    }
    
    private int findLeavesHelper(List<List<Integer>> list, TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftLevel = findLeavesHelper(list, root.left);
        int rightLevel = findLeavesHelper(list, root.right);
        int level = Math.max(leftLevel, rightLevel) + 1;
        if (list.size() == level) {
            list.add(new ArrayList<>());
        }
        list.get(level).add(root.val);
        root.left = root.right = null;
        return level;
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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        findLeaves(root);
    }
}
