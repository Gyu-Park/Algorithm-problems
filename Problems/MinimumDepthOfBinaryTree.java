/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 */
package Problems;

import java.util.*;

public class MinimumDepthOfBinaryTree {
    public static int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minDepth = 0;

        while (queue.size() != 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return ++minDepth;
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            minDepth++;
        }

        return minDepth;
    }

    // Recursive solution
    public static int recursiveMinDepth(TreeNode root) {
        int minDepth = 0;
        if (root == null)
            return minDepth;

        return recursion(root, minDepth);
    }

    private static int recursion(TreeNode root, int minDepth) {
        if (root == null)
            return minDepth;
        minDepth++;
        if (root.left == null && root.right == null) {
            return minDepth;
        }

        int left = Integer.MAX_VALUE;
        if (root.left != null) {
            left = recursion(root.left, minDepth);
        }

        int right = Integer.MAX_VALUE;
        if (root.right != null) {
            right = recursion(root.right, minDepth);
        }

        return Math.min(left, right);
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
        TreeNode p6 = new TreeNode(4);
        TreeNode p5 = new TreeNode(3);
        TreeNode p4 = new TreeNode(3, p6, null);
        TreeNode p3 = new TreeNode(2, null, p5);
        TreeNode p2 = new TreeNode(2, p4, null);
        TreeNode p = new TreeNode(1, p2, p3);
        System.out.println(minDepth(p));
    }
}
