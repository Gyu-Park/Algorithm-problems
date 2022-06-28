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
    // recursive solution
    // time complexity O(logn) because of a copy when putting a list into res.
    // space complexity O(logn) = the height of the tree
    //                            but in the worst case, O(n) (imbalanced tree)
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        return helper(root, targetSum, res, list, sum);
    }

    private static List<List<Integer>> helper(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> list, int sum) {
        if(root == null)
            return res;
        list.add(root.val);
        sum += root.val;
        if (root.left == null && root.right == null && sum == targetSum) {
            res.add(new ArrayList<>(list));
            return res;
        }
        if (root.left != null) {
            res = helper(root.left, targetSum, res, list, sum);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            res = helper(root.right, targetSum, res, list, sum);
            list.remove(list.size() - 1);
        }
        return res;
    }

    // iterative solution
    // time complexity O(log n)
    // space complexity O(n) in worst case
    public List<List<Integer>> anotherPathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int sum = 0;
        TreeNode cur = root;
        TreeNode pre = null;

        // post-order
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                path.add(cur.val);
                sum += cur.val;
                cur = cur.left;
            }
            cur = stack.peek();
            if(cur.right != null && cur.right != pre){
                cur = cur.right;
                continue;
            } 
            if(cur.left == null && cur.right == null && sum == targetSum) 
                res.add(new ArrayList<Integer>(path));
  
            pre = cur;
            stack.pop();
            path.remove(path.size() - 1);
            sum -= cur.val;
            cur = null;
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
        TreeNode root = new TreeNode(5, l1, r1);
        System.out.println(pathSum(root, targetSum));
    }
}
