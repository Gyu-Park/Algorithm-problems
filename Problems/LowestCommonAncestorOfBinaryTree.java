/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: 
 * “The lowest common ancestor is defined between two nodes p and q 
 * as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 */
package Problems;

public class LowestCommonAncestorOfBinaryTree {
    static TreeNode res;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        boolean rootTrue = root == p || root == q ? true : false;
        boolean left = false, right = false;
        if (root.left != null)
            left = helper(root.left, p, q);
        if (root.right != null)
            right = helper(root.right, p, q);
        if ((left && right) || (left && rootTrue) || (rootTrue && right)) {
            res = root;
        }
        return res;
    }

    private static boolean helper(TreeNode root, TreeNode p, TreeNode q) {
        if (res != null)
            return false;
        boolean rootTrue = root == p || root == q ? true : false;
        boolean left = false, right = false;
        if (root.left != null)
            left = helper(root.left, p, q);
        if (root.right != null)
            right = helper(root.right, p, q);
        if ((left && right) || (left && rootTrue) || (rootTrue && right)) {
            res = root;
        }
        return rootTrue || left || right;
    }

    // a shorter recursive solution
    public TreeNode anotherLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
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
        TreeNode root = new TreeNode(5);
        TreeNode p = new TreeNode(2);
        root.left = p;
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        TreeNode q = new TreeNode(6);
        root.right = q;
        root.right.left = new TreeNode(7);
        TreeNode res = lowestCommonAncestor(root, p, q);
        System.out.println(res.val);
    }
}
