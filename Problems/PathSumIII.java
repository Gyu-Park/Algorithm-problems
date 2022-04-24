/**
 * Given the root of a binary tree and an integer targetSum, 
 * return the number of paths where the sum of the values along the path equals targetSum.
 * 
 * The path does not need to start or end at the root or a leaf, 
 * but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 */
package Problems;

import java.util.*;

public class PathSumIII {
    // dfs solution using a list
    // time complexity O(nlogn) for a balanced binary tree,
    // O(n^2) for the worst cass
    // space complexity O(n + levels of a tree for a list) because of recursion
    // so space complexity is O(n)
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return root.val == targetSum ? 1 : 0;
        return helper(root, targetSum, new ArrayList<>());
    }

    private static int helper(TreeNode root, int targetSum, List<Integer> list) {
        list.add(root.val);
        int res = 0;
        int sum = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            sum += list.get(i);
            if (sum == targetSum)
                res++;
        }

        int left = 0;
        int right = 0;
        if (root.left != null)
            left = helper(root.left, targetSum, list);
        if (root.right != null)
            right = helper(root.right, targetSum, list);
        list.remove(list.size() - 1);
        return left + right + res;
    }

    // another solution using a hashMap
    public static int anotherPathSum(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(root, 0, targetSum, preSum);
    }

    public static int helper(TreeNode root, int currSum, int targetSum, HashMap<Integer, Integer> preSum) {
        if (root == null)
            return 0;

        currSum += root.val;
        int res = preSum.getOrDefault(currSum - targetSum, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, targetSum, preSum) + helper(root.right, currSum, targetSum, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    // typical dfs solution
    public int dfsPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        return pathSumFrom(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int pathSumFrom(TreeNode node, int targetSum) {
        if (node == null)
            return 0;
        return (node.val == targetSum ? 1 : 0)
                + pathSumFrom(node.left, targetSum - node.val) + pathSumFrom(node.right, targetSum - node.val);
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
        TreeNode p2 = new TreeNode(11);
        TreeNode p1 = new TreeNode(-3, null, p2);
        TreeNode q6 = new TreeNode(1);
        TreeNode q5 = new TreeNode(-2);
        TreeNode q4 = new TreeNode(3);
        TreeNode q3 = new TreeNode(2, null, q6);
        TreeNode q2 = new TreeNode(3, q4, q5);
        TreeNode q1 = new TreeNode(5, q2, q3);
        TreeNode root = new TreeNode(10, q1, p1);
        int targetSum = 8;
        System.out.println(anotherPathSum(root, targetSum));
    }
}
