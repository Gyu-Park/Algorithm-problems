/**
 * You are given the root of a binary search tree (BST), 
 * where the values of exactly two nodes of the tree were swapped by mistake. 
 * Recover the tree without changing its structure.
 */
package Problems;

public class RecoverBinarySearchTree {
    public static void recoverTree(TreeNode root) {

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
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        recoverTree(root);
    }
}
