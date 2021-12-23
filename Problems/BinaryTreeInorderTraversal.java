/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        if (root.left != null) {
            recursive(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            recursive(root.right, list);
        }

        return list;
    }

    private static List<Integer> recursive(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            recursive(root.left, list);
        }

        list.add(root.val);

        if (root.right != null) {
            recursive(root.right, list);
        }
        return list;
    }

    // iterative solution
    public List<Integer> inorderTraversalNonRecursiveSolution(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode currentRoot = root;

        while (currentRoot != null || !stack.empty()) {
            while (currentRoot != null) {
                stack.add(currentRoot);
                currentRoot = currentRoot.left;
            }
            currentRoot = stack.pop();
            list.add(currentRoot.val);
            currentRoot = currentRoot.right;
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
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = inorderTraversal(root);
        for (int a : list) {
            System.out.println(a);
        }
    }
}
