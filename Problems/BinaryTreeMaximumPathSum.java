/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * 
 * The path sum of a path is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Example 1:
 * 
 *             1
 *            / \
 *          2    3
 * 
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * 
 * Example 2:
 * 
 *             -10
 *           /     \
 *          9      20
 *                /  \
 *               15   7
 * 
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * 
 * Constraints:
 * 
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 */
package Problems;

public class BinaryTreeMaximumPathSum {
    // recursive solution
    // time compledxity: O(n)
    // space complexity: O(n) ---> in the worst case, binary tree could have all nodes on one side like a linked list.
    static int res = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        recursion(root);
        return res;
    }

    private static int recursion(TreeNode node) {
        int leftMax = 0;
        if (node.left != null) {
            leftMax = recursion(node.left);
        }
        int rightMax = 0;
        if (node.right != null) {
            rightMax = recursion(node.right);
        }
        int leftCenter = leftMax + node.val;
        int rightCenter = rightMax + node.val;
        int betterPath = Math.max(leftCenter, rightCenter);
        res = Math.max(res, Math.max(betterPath, leftCenter + rightCenter - node.val));
        res = Math.max(res, node.val);
        return Math.max(0, betterPath);
    }

    // same mechanism but more concise solution
    // time complexity: O(n)
    // spcae complexity: O(n)
    public static int anotherMaxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        maxPathDown(root);
        return res;
    }

    private static int maxPathDown(TreeNode node) {
        if (node == null)
            return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        res = Math.max(res, left + right + node.val);
        return Math.max(left, right) + node.val;
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
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(6);
        root.right = new TreeNode(-3);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(2);
        root.right.right.left.left = new TreeNode(-6);
        root.right.right.left.right = new TreeNode(-6);
        root.right.right.left.left.left = new TreeNode(-6);
        System.out.println(maxPathSum(root));
        System.out.println(anotherMaxPathSum(root));
    }
}
