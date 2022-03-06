/**
 * Given the roots of two binary trees root and subRoot, 
 * return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
 * 
 * A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants. 
 * The tree tree could also be considered as a subtree of itself.
 */
package Problems;

import java.util.*;

public class SubtreeOfAnotherTree {
    
    static boolean res = false;

    // Postorder traversal solution. This solution is too long compared to others
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root.left == null && root.right == null 
        && subRoot.left == null && subRoot.right == null) {
            return root.val == subRoot.val;
        }
        recursion(root, subRoot);
        return res;
    }

    private static void recursion(TreeNode root, TreeNode subRoot) {
        if (res) return;
        if (root.left != null && !res)
            recursion(root.left, subRoot);
        if (root.right != null && !res)
            recursion(root.right, subRoot);
        
        if (root.val == subRoot.val && !res)
            isSameTree(root, subRoot);
    }

    private static void isSameTree(TreeNode root, TreeNode subRoot) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        queue2.add(subRoot);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode node1 = queue1.poll();
            TreeNode node2 = queue2.poll();
            if (node1.val != node2.val)
                return;
            if ( (node1.left != null && node2.left == null) 
            || (node1.left == null && node2.left != null) 
            || (node1.right == null && node2.right != null) 
            || (node1.right != null && node2.right == null) ) {
                return;
            }
            if (node1.left != null) {
                queue1.add(node1.left);
                queue2.add(node2.left);
            }
            if (node1.right != null) {
                queue1.add(node1.right);
                queue2.add(node2.right);
            }
        }
        res = true;
    }

    // Preorder traversal solution. Shorter and faster
    public static boolean anotherIsSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSame(s, t)) return true;
        return anotherIsSubtree(s.left, t) || anotherIsSubtree(s.right, t);
    }
    
    private static boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        
        if (s.val != t.val) return false;
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
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
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);
        System.out.println(isSubtree(root, subRoot));
    }
}
