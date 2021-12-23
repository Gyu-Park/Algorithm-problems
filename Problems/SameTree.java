/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * 
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
package Problems;

import java.util.*;

public class SameTree {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null || p.val != q.val)
            return false;

        return bfs(p, q);
    }

    private static boolean bfs(TreeNode p, TreeNode q) {
        List<TreeNode> visited = new ArrayList<>();
        LinkedList<TreeNode> queueP = new LinkedList<>();
        LinkedList<TreeNode> queueQ = new LinkedList<>();

        visited.add(p);
        queueP.add(p);
        queueQ.add(q);

        while (queueP.size() != 0) {
            p = queueP.poll();
            q = queueQ.poll();

            if (p.left != null) {
                if (q.left == null)
                    return false;
                if (p.left.val == q.left.val && !visited.contains(p.left)) {
                    visited.add(p.left);
                    queueP.add(p.left);
                    queueQ.add(q.left);
                } else if (p.left.val != q.left.val) {
                    return false;
                }
            } else if (p.left == null && q.left != null) {
                return false;
            }

            if (p.right != null) {
                if (q.right == null)
                    return false;
                if (p.right.val == q.right.val && !visited.contains(p.right)) {
                    visited.add(p.right);
                    queueP.add(p.right);
                    queueQ.add(q.right);
                } else if (p.right.val != q.right.val) {
                    return false;
                }
            } else if (p.right == null && q.right != null) {
                return false;
            }
        }

        return true;
    }

    // Shorter version
    public boolean isSameTreeShorterVersion(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null || p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
        TreeNode p3 = new TreeNode(3);
        TreeNode p2 = new TreeNode(2);
        TreeNode p = new TreeNode(1, p2, p3);

        TreeNode q3 = new TreeNode(3);
        TreeNode q2 = new TreeNode(2);
        TreeNode q = new TreeNode(1, q2, q3);
        System.out.println(isSameTree(p, q));
    }
}
