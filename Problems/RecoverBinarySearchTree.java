/**
 * You are given the root of a binary search tree (BST), 
 * where the values of exactly two nodes of the tree were swapped by mistake. 
 * Recover the tree without changing its structure.
 */
package Problems;

public class RecoverBinarySearchTree {

    static TreeNode firstEl = null;
    static TreeNode secondEl = null;
    static TreeNode preEl = new TreeNode(Integer.MIN_VALUE);

    public static void recoverTree(TreeNode root) {
        helper(root);

        int temp = firstEl.val;
        firstEl.val = secondEl.val;
        secondEl.val = temp;
    }

    private static void helper(TreeNode root) {
        if (root == null)
            return;
        
        helper(root.left);

        // Start of "do some business", 
        // If first element has not been found, assign it to prevElement (refer to 6 in the example above)
        if (firstEl == null && preEl.val >= root.val)
            firstEl = preEl;
    
        // If first element is found, assign the second element to the root (refer to 2 in the example above)
        if (firstEl != null && preEl.val >= root.val)
            secondEl = root;
        preEl = root;

        helper(root.right);
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
