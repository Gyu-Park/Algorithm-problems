/**
 * Given the root of a binary tree, invert the tree, and return its root.
 * 
 * Example
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 */
package Problems;

import java.util.*;

public class InvertBinaryTree {
    // dfs solution
    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;

        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);

        return root;
    }

    // bfs solution
    public static TreeNode bfsInvertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return root;

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
        TreeNode q7 = new TreeNode(9);
        TreeNode q6 = new TreeNode(6);
        TreeNode q5 = new TreeNode(3);
        TreeNode q4 = new TreeNode(1);
        TreeNode q3 = new TreeNode(7, q6, q7);
        TreeNode q2 = new TreeNode(2, q4, q5);
        TreeNode root = new TreeNode(4, q2, q3);
        bfsInvertTree(root);
        invertTree(root);
    }
}
