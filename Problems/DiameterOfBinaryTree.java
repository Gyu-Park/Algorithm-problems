/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
 * This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges between them.
 */
package Problems;

public class DiameterOfBinaryTree {
    static int longestPath = 0;
    int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null)
            return 0;

        int leftPath = recursion(root.left, 0);
        int rightPath = recursion(root.right, 0);
        if (leftPath + rightPath > longestPath)
            longestPath = leftPath + rightPath;

        return longestPath;
    }

    private static int recursion(TreeNode root, int pathCount) {
        if (root == null)
            return pathCount;

        pathCount++;
        int leftPath = recursion(root.left, pathCount);
        int rightPath = recursion(root.right, pathCount);
        if (leftPath - pathCount + rightPath - pathCount > longestPath)
            longestPath = leftPath - pathCount + rightPath - pathCount;

        return Math.max(leftPath, rightPath);
    }

    // shorter solution
    public int anotherDiameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
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
        TreeNode p2 = new TreeNode(5);
        TreeNode p1 = new TreeNode(4, p2, null);
        TreeNode p = new TreeNode(2, p1, null);
        TreeNode root = new TreeNode(1, p, null);
        System.out.println(diameterOfBinaryTree(root));
    }
}
