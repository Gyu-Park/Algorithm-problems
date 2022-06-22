/**
 * Given the root of a binary tree and an integer targetSum, 
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum. 
 * Each path should be returned as a list of the node values, not node references.
 * 
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
package Problems;

import java.util.*;

public class PathSumII {
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {

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
        int targetSum = 22;
        TreeNode r5 = new TreeNode(1);
        TreeNode r4 = new TreeNode(5);
        TreeNode r3 = new TreeNode(4, r4, r5);
        TreeNode r2 = new TreeNode(13);
        TreeNode r1 = new TreeNode(8, r2, r3);
        TreeNode l4 = new TreeNode(2);
        TreeNode l3 = new TreeNode(7);
        TreeNode l2 = new TreeNode(11, l3, l4);
        TreeNode l1 = new TreeNode(4, l2, null);
        TreeNode root = new TreeNode(1, l1, r1);
        System.out.println(pathSum(root, targetSum));
    }
}
