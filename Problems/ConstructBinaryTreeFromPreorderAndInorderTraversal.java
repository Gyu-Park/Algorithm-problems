/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree 
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
package Problems;

import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return constructTree(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private static TreeNode constructTree(int[] preorder, int[] inorder, int preorderStart, int inorderStart, int inorderEnd) {
        if (preorderStart >= preorder.length || inorderStart > inorderEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preorderStart]);
        int inorderIndex = 0;
        for (int i = inorderStart; i <= inorderEnd; i++) {
            if (inorder[i] == root.val) {
                inorderIndex = i;
                break;
            }
        }
        root.left = constructTree(preorder, inorder, preorderStart + 1, inorderStart, inorderIndex - 1);
        root.right = constructTree(preorder, inorder, preorderStart + inorderIndex - inorderStart + 1, inorderIndex + 1, inorderEnd);
        return root;
    }

    // iterative solution
    public TreeNode iterativeBuildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                stack.push(cur);
                cur = cur.left;
            } else {
                j++;
                while (!stack.empty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j++;
                }
                cur = cur.right = new TreeNode(preorder[i]);
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
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        TreeNode root = buildTree(preorder, inorder);
    }
}
