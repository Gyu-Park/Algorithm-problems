/**
 * Given the root of a binary tree, return its maximum depth.
 * 
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
package Problems;

public class MaximumDepthOfBinaryTree {
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int maxDepth = 1;
        return recursive(root.left, root.right, maxDepth);
    }

    private static int recursive(TreeNode leftTree, TreeNode rightTree, int maxDepth) {
        if (leftTree == null && rightTree == null)
            return maxDepth;
        maxDepth++;
        int i = maxDepth;
        int j = maxDepth;
        if (leftTree != null) {
            i = recursive(leftTree.left, leftTree.right, maxDepth);
        }
        if (rightTree != null) {
            j = recursive(rightTree.left, rightTree.right, maxDepth);
        }
        return Math.max(i, j);
    }

    // Shorter version of MaxDepth Method
    public static int shorterMaxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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
        TreeNode r5 = new TreeNode(4);
        TreeNode r4 = new TreeNode(3, null, r5);
        TreeNode r3 = new TreeNode(2);
        TreeNode r2 = new TreeNode(9, r4, null);
        TreeNode root = new TreeNode(1, r2, r3);
        System.out.println(maxDepth(root));
        System.out.println(shorterMaxDepth(root));
    }
}
