/**
 * Given the root of a Binary Search Tree (BST), 
 * return the minimum absolute difference between the values of any two different nodes in the tree.
 */

package Problems;

import java.util.*;

public class MinimumAbsoluteDifferenceInBST {

    static Set<Integer> set;

    int min = Integer.MAX_VALUE;
    Integer prev = null;

    // Using TreeSet solution
    public static int getMinimumDifference(TreeNode root) {
        set = new TreeSet<>();
        recursion(root);
        Iterator<Integer> iter = set.iterator();
        int res = Integer.MAX_VALUE;
        int pre = iter.next();
        while (iter.hasNext()) {
            int cur = iter.next();
            res = Math.min(res, cur - pre);
            pre = cur;
        }
        return res;
    }

    private static void recursion(TreeNode root) {
        set.add(root.val);
        if (root.right != null) {
            recursion(root.right);
        }
        if (root.left != null) {
            recursion(root.left);
        }
    }

    // in-order traverse solution
    public int fasterGetMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        getMinimumDifference(root.left);
        
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;
        
        getMinimumDifference(root.right);
        
        return min;
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
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(11);
        System.out.println(getMinimumDifference(root));
    }
}
