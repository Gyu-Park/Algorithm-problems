/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), 
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 */
package Problems;

import java.util.*;

public class UniqueBinarySearchTreesII {
    public static List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(1));
            return res;
        }

        int[] array = new int[n + 1];
        for (int i = 1; i < array.length; i++)
            array[i] = i;

        for (int i = 1; i < array.length; i++) {
            TreeNode node = new TreeNode(array[i]);
            res = helper(res, array, node);
        }

        return res;
    }

    private static List<TreeNode> helper(List<TreeNode> res, int[] array, TreeNode node) {

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
        int n = 3;
        List<TreeNode> res = generateTrees(n);
    }
}
