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

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++)
            list.add(i);
        
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = new TreeNode(list.get(i));
            list.remove(list.get(i));
            res = helper(res, list, node);
            list.add(i + 1);
        }

        return res;
    }

    private static List<TreeNode> helper(List<TreeNode> res, List<Integer> list, TreeNode node) {
        
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
