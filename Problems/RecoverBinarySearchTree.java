/**
 * You are given the root of a binary search tree (BST), 
 * where the values of exactly two nodes of the tree were swapped by mistake. 
 * Recover the tree without changing its structure.
 */
package Problems;

public class RecoverBinarySearchTree {
    // iterative solution using Morris-traversal
    // time complexity O(n)
    // space complexity O(1)
    public static void recoverTree(TreeNode root) {
        TreeNode pre = null;
        TreeNode first = null, second = null;
        // Morris Traversal
        TreeNode temp = null;
        while (root != null) {
            if (root.left != null) {
                // connect threading for root
                temp = root.left;
                while (temp.right != null && temp.right != root)
                    temp = temp.right;
                // the threading already exists
                if (temp.right != null) {
                    if (pre != null && pre.val > root.val) {
                        if (first == null) {
                            first = pre;
                            second = root;
                        } else {
                            second = root;
                        }
                    }
                    pre = root;

                    temp.right = null;
                    root = root.right;
                } else {
                    // construct the threading
                    temp.right = root;
                    root = root.left;
                }
            } else {
                if (pre != null && pre.val > root.val) {
                    if (first == null) {
                        first = pre;
                        second = root;
                    } else {
                        second = root;
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        // swap two node values;
        if (first != null && second != null) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }

    // recursive solution using in-order traversal
    // time complexity O(n)
    // space complexity O(n)
    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    public void anotherRecoverTree(TreeNode root) {
        // use inorder traversal to detect incorrect node

        inOrder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        // search left tree
        inOrder(root.left);

        // in inorder traversal of BST, prev should always have smaller value than
        // current value
        if (prev != null && prev.val >= root.val) {
            // incorrect smaller node is always found as prev node
            if (first == null)
                first = prev;
            // incorrect larger node is always found as curr(root) node
            second = root;
        }

        // update prev node
        prev = root;

        // search right tree
        inOrder(root.right);
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
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        recoverTree(root);

    }
}
