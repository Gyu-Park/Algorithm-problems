/**
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

public class BinaryTreeTraversal {
    // iterative inorder traversal
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }

        return list;
    }

    // recursive inorder traversal
    public static List<Integer> recursiveInorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        list = recursiveInorderTraversal(root, list);
        return list;
    }

    private static List<Integer> recursiveInorderTraversal(TreeNode root, List<Integer> list) {
        if (root.left != null)
            list = recursiveInorderTraversal(root.left, list);
        list.add(root.val);
        if (root.right != null)
            list = recursiveInorderTraversal(root.right, list);
        return list;
    }

    // iterative preorder traversal
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null)
                stack.add(node.right);
            if (node.left != null)
                stack.add(node.left);
        }

        return list;
    }

    // recursive preorder traversal
    public static List<Integer> recursivePreorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        list = recursivePreorderTraversal(root, list);
        return list;
    }

    private static List<Integer> recursivePreorderTraversal(TreeNode root, List<Integer> list) {
        list.add(root.val);
        if (root.left != null)
            list = recursivePreorderTraversal(root.left, list);
        if (root.right != null)
            list = recursivePreorderTraversal(root.right, list);
        return list;
    }

    // iterative postorder traversal
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.add(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.add(node);
            if (node.left != null)
                stack1.add(node.left);
            if (node.right != null)
                stack1.add(node.right);
        }

        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }

        return list;
    }

    // recursive postorder traversal
    public static List<Integer> recursivePostorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        list = recursivePostorderTraversal(root, list);
        return list;
    }

    private static List<Integer> recursivePostorderTraversal(TreeNode root, List<Integer> list) {
        if (root.left != null)
            list = recursivePostorderTraversal(root.left, list);
        if (root.right != null)
            list = recursivePostorderTraversal(root.right, list);
        list.add(root.val);
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
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(7);
        List<Integer> list = inorderTraversal(root);
        for (int a : list) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        List<Integer> list2 = recursiveInorderTraversal(root);
        for (int a : list2) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        List<Integer> list3 = preorderTraversal(root);
        for (int a : list3) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        List<Integer> list4 = recursivePreorderTraversal(root);
        for (int a : list4) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        List<Integer> list5 = postorderTraversal(root);
        for (int a : list5) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        List<Integer> list6 = recursivePostorderTraversal(root);
        for (int a : list6) {
            System.out.print(a + " ");
        }
    }
}
