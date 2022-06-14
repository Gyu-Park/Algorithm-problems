/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree 
 * and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 */
package Problems;

import java.util.Arrays;
import java.util.Stack;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    // not optimized solution
    // time complexity O(height of the tree)
    // space complexity O(height of the tree)
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 1)
            return new TreeNode(inorder[0]);
        return helper(inorder, postorder);
    }

    private static TreeNode helper(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;

        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        int i;
        for (i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal)
                break;
        }
        root.left = helper(Arrays.copyOf(inorder, i), Arrays.copyOf(postorder, i));
        root.right = helper(Arrays.copyOfRange(inorder, i + 1, inorder.length), Arrays.copyOfRange(postorder, i, postorder.length - 1));

        return root;
    }

    // iterative solution
    // time complexity O(n)
    // space complexity O(n)
    public static TreeNode anotherBuildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) 
            return null;
        int ip = inorder.length - 1;
        int pp = postorder.length - 1;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode prev = null;
        TreeNode root = new TreeNode(postorder[pp]);
        stack.push(root);
        pp--;
        
        while (pp >= 0) {
            while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                prev = stack.pop();
                ip--;
            }
            TreeNode newNode = new TreeNode(postorder[pp]);
            if (prev != null) {
                prev.left = newNode;
            } else if (!stack.isEmpty()) {
                TreeNode currTop = stack.peek();
                currTop.right = newNode;
            }
            stack.push(newNode);
            prev = null;
            pp--;
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
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode node = anotherBuildTree(inorder, postorder);
        Stack<TreeNode> stack = new Stack<>();

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val + " ");
            node = node.right;
        }
    } 
}
