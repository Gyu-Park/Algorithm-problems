/**
 * Given the root of a binary tree and an integer targetSum, 
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * 
 * A leaf is a node with no children.
 */
package Problems;

import java.util.*;

public class PathSum {
    // Preorder DFS
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> sumQueue = new Stack<>();
        stack.add(root);
        sumQueue.add(root.val);

        while (stack.size() != 0) {
            TreeNode node = stack.pop();
            int sum = sumQueue.pop();

            if (sum == targetSum && node.left == null && node.right == null)
                return true;

            if (node.right != null) {
                stack.add(node.right);
                sumQueue.add(node.right.val + sum);
            }

            if (node.left != null) {
                stack.add(node.left);
                sumQueue.add(node.left.val + sum);
            }

        }

        return false;
    }

    // recursive solution
    public static boolean recursiveHasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && root.val == targetSum)
            return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
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
        TreeNode p9 = new TreeNode(1);
        TreeNode p8 = new TreeNode(2);
        TreeNode p7 = new TreeNode(7);
        TreeNode p6 = new TreeNode(4, null, p9);
        TreeNode p5 = new TreeNode(13);
        TreeNode p4 = new TreeNode(11, p7, p8);
        TreeNode p3 = new TreeNode(8, p5, p6);
        TreeNode p2 = new TreeNode(4, p4, null);
        TreeNode root = new TreeNode(5, p2, p3);
        int targetSum = 22;
        System.out.println(hasPathSum(root, targetSum));
        System.out.println(recursiveHasPathSum(root, targetSum));
    }
}
