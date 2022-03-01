/**
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 * 
 * The tilt of a tree node is the absolute difference 
 * between the sum of all left subtree node values and all right subtree node values. 
 * If a node does not have a left child, then the sum of the left subtree node values is treated as 0. 
 * The rule is similar if the node does not have a right child.
 */
package Problems;

public class BinaryTreeTilt {

    static int sum;

    public static int findTilt(TreeNode root) {
        recursion(root);
        return sum;
    }

    private static int recursion(TreeNode root) {
        if (root == null) return 0;

        int left = recursion(root.left);
        int right = recursion(root.right);
        int res;
        if (left > right) {
            res = left - right;
        } else {
            res = right - left;
        }
        sum += res;
        return root.val + left + right;
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
        TreeNode root = new TreeNode(21);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(3);
        root.right = new TreeNode(14);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(2);
        System.out.println(findTilt(root));        
    }
}
