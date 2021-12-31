/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * 
 * A leaf is a node with no children.
 */
package Problems;

import java.util.*;

public class BinaryTreePaths {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        stack1.add(root);
        st.append("");
        stack2.add(st.toString());

        while (!stack1.isEmpty()) {
            st.delete(0, st.length());
            TreeNode node = stack1.pop();
            st.append(stack2.pop());

            if (node.right == null && node.left == null) {
                st.append(node.val);
                list.add(st.toString());
            }

            if (node.right != null) {
                String storage = st.toString();
                stack1.add(node.right);
                st.append(node.val);
                st.append("->");
                stack2.add(st.toString());
                st.delete(0, st.length());
                st.append(storage);
            }

            if (node.left != null) {
                stack1.add(node.left);
                st.append(node.val);
                st.append("->");
                stack2.add(st.toString());
                st.delete(0, st.length());
            }
        }

        return list;
    }

    public static List<String> recursiveBinaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root != null)
            searchBT(root, "", list);
        return list;
    }

    private static void searchBT(TreeNode root, String path, List<String> list) {
        if (root.left == null && root.right == null)
            list.add(path + root.val);
        if (root.left != null)
            searchBT(root.left, path + root.val + "->", list);
        if (root.right != null)
            searchBT(root.right, path + root.val + "->", list);
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
        TreeNode d = new TreeNode(6);
        TreeNode c = new TreeNode(5);
        TreeNode b = new TreeNode(3);
        TreeNode a = new TreeNode(2, c, d);
        TreeNode root = new TreeNode(1, a, b);
        List<String> list = binaryTreePaths(root);
        for (String st : list) {
            System.out.println(st);
        }

        List<String> list2 = recursiveBinaryTreePaths(root);
        for (String st : list2) {
            System.out.println(st);
        }
    }
}
