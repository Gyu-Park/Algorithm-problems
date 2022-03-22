/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * 
 * The "linked list" should use the same TreeNode class 
 * where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
package Problems;

import java.util.*;

public class FlattenBinaryTreeToLinkedList {
    // preorder traversal and store the values in a arraylist in order.
    // And re-build the tree based on values in the aaraylist.
    public static void flatten(TreeNode root) {
        if (root == null)
            return;
        if (root.right == null && root.left == null)
            return;
        List<Integer> preorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorder.add(node.val);
            if (node.right != null)
                stack.add(node.right);
            if (node.left != null)
                stack.add(node.left);
        }

        root.left = null;
        recursion(root, preorder, 1);
    }

    private static void recursion(TreeNode root, List<Integer> preorder, int index) {
        if (index >= preorder.size())
            return;
        root.right = new TreeNode(preorder.get(index++));
        recursion(root.right, preorder, index);
    }

    // postorder solution
    // keep previous nodes like a tail and attach it on a current node.
    public static void anotherFlatten(TreeNode root) {
        helper(root, null);
    }

    private static TreeNode helper(TreeNode root, TreeNode preNode) {
        if (root == null)
            return preNode;
        preNode = helper(root.right, preNode);
        preNode = helper (root.left, preNode);

        root.right = preNode;
        root.left = null;
        preNode = root;
        return preNode;
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
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        anotherFlatten(root);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
    }
}
