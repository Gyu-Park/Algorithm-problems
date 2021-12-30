/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: 
 * “The lowest common ancestor is defined between two nodes p and q 
 * as the lowest node in T that has both p and q as descendants 
 * (where we allow a node to be a descendant of itself).”
 */
package Problems;

public class LowestCommonAncestorOfBST {
    private static TreeNode lca = null;

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q)
            return root;
        int lcaCount = recursion(root, p, q, 0);
        if (lcaCount == 2 && lca == null)
            lca = root;

        return lca;
    }

    private static Integer recursion(TreeNode root, TreeNode p, TreeNode q, int lcaCount) {
        int left = 0;
        int right = 0;
        if (lca == null && root.left != null)
            left = recursion(root.left, p, q, left);

        if (lca == null && root.right != null)
            right = recursion(root.right, p, q, right);

        if (root == q || root == p)
            lcaCount++;

        if (left + right + lcaCount == 2 && lca == null) {
            lca = root;
        }

        return left + right + lcaCount;
    }

    // shorter solution
    public static TreeNode shorterLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return shorterLCA(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return shorterLCA(root.right, p, q);
        } else {
            return root;
        }
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
        TreeNode p4 = new TreeNode(5);
        TreeNode p3 = new TreeNode(3);
        TreeNode q2 = new TreeNode(9);
        TreeNode q1 = new TreeNode(7);
        TreeNode p2 = new TreeNode(4, p3, p4);
        TreeNode p1 = new TreeNode(0);
        TreeNode q = new TreeNode(8, q1, q2);
        TreeNode p = new TreeNode(2, p1, p2);
        TreeNode root = new TreeNode(6, p, q);
        System.out.println(lowestCommonAncestor(root, p, p2).val);
        System.out.println(shorterLCA(root, p, p2).val);
    }
}
