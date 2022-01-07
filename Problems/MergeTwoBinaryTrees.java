/** 
 * You are given two binary trees root1 and root2.
 * 
 * Imagine that when you put one of them to cover the other, 
 * some nodes of the two trees are overlapped while the others are not. 
 * You need to merge the two trees into a new binary tree. 
 * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. 
 * Otherwise, the NOT null node will be used as the node of the new tree.
 * 
 * Return the merged tree.
 * 
 * Note: The merging process must start from the root nodes of both trees.
 */
package Problems;

import java.util.*;

public class MergeTwoBinaryTrees {
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return null;
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;

        return recursion(root1, root2);
    }

    private static TreeNode recursion(TreeNode root1, TreeNode root2) {
        if (root1 != null && root2 != null) {
            root1.val += root2.val;
        }

        if (root1.left != null && root2.left != null) {
            recursion(root1.left, root2.left);
        } else if (root1.left == null && root2.left != null) {
            root1.left = new TreeNode(0);
            recursion(root1.left, root2.left);
        }

        if (root1.right != null && root2.right != null) {
            recursion(root1.right, root2.right);
        } else if (root1.right == null && root2.right != null) {
            root1.right = new TreeNode(0);
            recursion(root1.right, root2.right);
        }

        return root1;
    }

    // Solution using BFS
    public TreeNode BFSMergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        // use array in the queue to manipulate at the same time
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[] { t1, t2 });

        while (!queue.isEmpty()) {
            TreeNode[] nodes = queue.poll();

            // merge 2 into 1 when it is not null
            if (nodes[1] == null)
                continue;

            // nodes[0] must not be null
            nodes[0].val += nodes[1].val;

            // make sure nodes[0] will be null
            if (nodes[0].left == null)
                nodes[0].left = nodes[1].left;
            else
                queue.offer(new TreeNode[] { nodes[0].left, nodes[1].left });
            if (nodes[0].right == null)
                nodes[0].right = nodes[1].right;
            else
                queue.offer(new TreeNode[] { nodes[0].right, nodes[1].right });

        }
        return t1;
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
        TreeNode p4 = new TreeNode(7);
        TreeNode p3 = new TreeNode(4);
        TreeNode p2 = new TreeNode(3, null, p4);
        TreeNode p1 = new TreeNode(1, null, p3);
        TreeNode root2 = new TreeNode(2, p1, p2);

        TreeNode q3 = new TreeNode(5);
        TreeNode q2 = new TreeNode(2);
        TreeNode q1 = new TreeNode(3, q3, null);
        TreeNode root1 = new TreeNode(1, q1, q2);
        System.out.println(mergeTrees(root1, root2));
    }
}
