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

public class ValidateBinarySearchTree {
    public static boolean isValidBST(TreeNode root) {

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
        TreeNode q5 = new TreeNode(6);
        TreeNode q4 = new TreeNode(3);
        TreeNode q3 = new TreeNode(4, q4, q5);
        TreeNode q2 = new TreeNode(1);
        TreeNode root = new TreeNode(5, q2, q3);
        System.out.println(isValidBST(root));
    }
}
