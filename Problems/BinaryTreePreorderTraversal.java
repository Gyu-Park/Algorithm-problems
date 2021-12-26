/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * 
 * 
 *            (1)
 *            / \
 *          (2) (3)
 *         /  \
 *       (4)  (5)
 * 
 * Depth First Traversals: 
 * (a) Inorder (Left, Root, Right) : 4 2 5 1 3 
 * (b) Preorder (Root, Left, Right) : 1 2 4 5 3 
 * (c) Postorder (Left, Right, Root) : 4 5 2 3 1
 * Breadth-First or Level Order Traversal: 1 2 3 4 5 
 */
package Problems;

import java.util.*;

public class BinaryTreePreorderTraversal {
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        list = recursion(root, list);
        return list;
    }

    // recursive solution
    private static List<Integer> recursion(TreeNode root, List<Integer> list) {
        if (root == null)
            return list;

        list.add(root.val);

        if (root.left != null)
            recursion(root.left, list);
        if (root.right != null)
            recursion(root.right, list);

        return list;
    }

    // iterative solution
    private static List<Integer> iterativePreorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (stack.size() > 0) {
            TreeNode node = new TreeNode();
            node = stack.pop();

            list.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }

            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return list;
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
        TreeNode q7 = new TreeNode(7);
        TreeNode q6 = new TreeNode(6);
        TreeNode q5 = new TreeNode(5, q7, null);
        TreeNode q4 = new TreeNode(4);
        TreeNode q3 = new TreeNode(3, q5, q6);
        TreeNode q2 = new TreeNode(2, q4, null);
        TreeNode q = new TreeNode(1, q2, q3);

        List<Integer> list = new ArrayList<>();
        list = preorderTraversal(q);
        for (int num : list) {
            System.out.print(num);
        }

        System.out.println(" ");

        List<Integer> list2 = new ArrayList<>();
        list2 = iterativePreorderTraversal(q);
        for (int num : list2) {
            System.out.print(num);
        }
    }
}
