/**
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (i.e., from left to right, level by level from leaf to root).
 */
package Problems;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    // inorder traversal solution
    // time complexity O(n)
    // space complexity O(n)
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        res = helper(root, res, 0);
        Collections.reverse(res);
        return res;
    }

    private static List<List<Integer>> helper(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null)
            return res;
        if (level >= res.size())
            res.add(new ArrayList<Integer>());
        
        res = helper(root.left, res, level + 1);
        res.get(level).add(root.val);
        res = helper(root.right, res, level + 1);
        return res;
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
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = levelOrderBottom(root);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
