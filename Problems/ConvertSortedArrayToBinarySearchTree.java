/**
 * Given an integer array nums where the elements are sorted in ascending order, 
 * convert it to a height-balanced binary search tree.
 * 
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 */
package Problems;

public class ConvertSortedArrayToBinarySearchTree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        int mid = nums.length / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (mid == 0) {
            return root;
        }
        root.left = recursion(root.left, nums, 0, mid - 1);
        if (mid < nums.length - 1) {
            root.right = recursion(root.right, nums, mid + 1, nums.length - 1);
        }

        return root;
    }

    private static TreeNode recursion(TreeNode root, int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        root = new TreeNode(nums[mid]);
        if (mid > start) {
            root.left = recursion(root.left, nums, start, mid - 1);
        }
        if (mid < end) {
            root.right = recursion(root.right, nums, mid + 1, end);
        }

        return root;
    }

    // Shorter solution
    public TreeNode shorterSortedArrayToBST(int[] num) {
        if (num.length == 0) {
            return null;
        }
        TreeNode root = helper(num, 0, num.length - 1);

        return root;
    }

    public TreeNode helper(int[] num, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helper(num, low, mid - 1);
        node.right = helper(num, mid + 1, high);

        return node;
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
        int[] nums = { -10, -8, -7, -3, 0, 5, 9, 11, 20 };
        TreeNode result = sortedArrayToBST(nums);
        System.out.println(result.toString());
    }
}
