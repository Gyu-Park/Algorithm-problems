/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * A valid BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
package Problems;

import java.util.*;

public class ValidateBinarySearchTree {
    // interative inorder traversal solution
    public static boolean isValidBST(TreeNode root) {
        if (root.left == null && root.right == null)
            return true;

        long curMaxVal = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.val <= curMaxVal) {
                return false;
            }
            curMaxVal = node.val;
            node = node.right;
        }
        return true;
    }

    // recursive solution
    public boolean recursiveIsValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null)
            return true;
        if (root.val >= maxVal || root.val <= minVal)
            return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
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
        TreeNode q3 = new TreeNode(Integer.MAX_VALUE);
        TreeNode root = new TreeNode(Integer.MIN_VALUE, null, q3);
        System.out.println(isValidBST(root));
    }
}
