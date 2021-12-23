/**
 * Given the root of a binary tree, 
 * check whether it is a mirror of itself (i.e., symmetric around its center).
 */
package Problems;

import java.util.*;

public class SymmetricTree {
    // A solution using recursion
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return recursive(root.left, root.right);
    }

    private static boolean recursive(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree == null && rightTree == null)
            return true;
        if (leftTree == null || rightTree == null || leftTree.val != rightTree.val)
            return false;
        return recursive(leftTree.left, rightTree.right) && recursive(leftTree.right, rightTree.left);
    }

    // A solution using breadth-first search
    public static boolean iterativeIsSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return bfs(root.left, root.right);
    }

    private static boolean bfs(TreeNode leftTree, TreeNode rightTree) {
        LinkedList<TreeNode> leftQueue = new LinkedList<>();
        LinkedList<TreeNode> rightQueue = new LinkedList<>();

        leftQueue.add(leftTree);
        rightQueue.add(rightTree);

        while (leftQueue.size() != 0) {
            leftTree = leftQueue.poll();
            rightTree = rightQueue.poll();

            if (leftTree == null && rightTree == null) {
                continue;
            } else if (leftTree == null || rightTree == null || leftTree.val != rightTree.val) {
                return false;
            } else {
                leftQueue.add(leftTree.left);
                rightQueue.add(rightTree.right);
                leftQueue.add(leftTree.right);
                rightQueue.add(rightTree.left);
            }
        }

        return true;
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
        TreeNode r7 = new TreeNode(3);
        TreeNode r6 = new TreeNode(4);
        TreeNode r5 = new TreeNode(4);
        TreeNode r4 = new TreeNode(3);
        TreeNode r3 = new TreeNode(2, r6, r7);
        TreeNode r2 = new TreeNode(2, r4, r5);
        TreeNode root = new TreeNode(1, r2, r3);
        System.out.println(isSymmetric(root));
        System.out.println(iterativeIsSymmetric(root));
    }
}
