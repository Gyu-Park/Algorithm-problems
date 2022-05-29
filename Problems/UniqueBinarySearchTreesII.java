/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), 
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 */
package Problems;

import java.util.*;

public class UniqueBinarySearchTreesII {
    // recursive solution
    // time complexity O((2n)! / (n+1)!n!)
    // this can be written O(Cn), which is Catalan number
    // space complexity O(n * Cn)
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return helper(1, n);
    }

    private static List<TreeNode> helper(int left, int right) {
        List<TreeNode> res = new ArrayList<>();
        if (left > right) {
            res.add(null);
            return res;
        }

        for (int i = left; i <= right; i++) {
            List<TreeNode> leftNode = helper(left, i - 1);
            List<TreeNode> rightNode = helper(i + 1, right);
            for (TreeNode l : leftNode) {
                for (TreeNode r : rightNode) {
                    res.add(new TreeNode(i, l, r));
                }
            }
        }

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
        int n = 3;
        List<TreeNode> res = generateTrees(n);
        System.out.println(res.size());
    }
}
