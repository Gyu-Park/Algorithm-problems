/**
 * Given the root of a binary tree, return the sum of all left leaves.
 */
package Problems;

import java.util.*;

public class SumOfLeftLeaves {
    public static int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }

    private static int helper(TreeNode root, boolean isLeft) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        return helper(root.left, true) + helper(root.right, false);
    }

    public static int iterativeSumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        int res = 0;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null)
                    res += node.left.val;
                else
                    stack.push(node.left);
            }
            if (node.right != null) {
                if (node.right.left != null || node.right.right != null)
                    stack.push(node.right);
            }
        }
        return res;
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
        TreeNode q2 = new TreeNode(5);
        TreeNode q1 = new TreeNode(4);
        TreeNode p = new TreeNode(3, null, q2);
        TreeNode q = new TreeNode(2, q1, null);
        TreeNode root = new TreeNode(1, q, p);
        System.out.println(sumOfLeftLeaves(root));
        System.out.println(iterativeSumOfLeftLeaves(root));
    }
}
