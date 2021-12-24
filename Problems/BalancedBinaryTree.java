/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 */
package Problems;

public class BalancedBinaryTree {
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return recursion(root) != -1 ? true : false;
    }

    private static int recursion(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = recursion(root.left);
        int rightHeight = recursion(root.right);

        if (leftHeight == -1 || rightHeight == -1)
            return -1;

        if (leftHeight - rightHeight > 1 || leftHeight - rightHeight < -1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
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
        System.out.println(isBalanced(p));
    }
}
