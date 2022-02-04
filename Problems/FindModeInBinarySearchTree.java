/**
 * Given the root of a binary search tree (BST) with duplicates, 
 * return all the mode(s) (i.e., the most frequently occurred element) in it.
 * 
 * If the tree has more than one mode, return them in any order.
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
package Problems;

import java.util.*;

public class FindModeInBinarySearchTree {
    // bfs
    public static int[] findMode(TreeNode root) {
        if (root.left == null && root.right == null)
            return new int[] {root.val};
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!map.containsKey(node.val)) {
                values.add(node.val);
            }
            map.put(node.val, map.getOrDefault(node.val, 0) + 1);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
        
        List<Integer> moreModes = new ArrayList<>();
        int maxMode = 0;
        for (int i = 0; i < values.size(); i++) {
            if (i == 0) {
                maxMode = values.get(i);
            }else if (map.get(maxMode) < map.get(values.get(i))) {
                maxMode = values.get(i);
                moreModes = new ArrayList<>();
            } else if (map.get(maxMode) == map.get(values.get(i))) {
                moreModes.add(values.get(i));
            }
        }
        int[] res;
        if (moreModes.size() != 0) {
            res = new int[moreModes.size() + 1];
            int count = 0;
            res[count++] = maxMode;
            for(int i = 0; i < moreModes.size(); i++) {
                    res[count++] =  moreModes.get(i);
            }
        } else {
            res = new int[1];
            res[0] = maxMode;
        }
        return res;
    }

    // inorder dfs solution
    public static int[] betterFindMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private static int currVal;
    private static int currCount = 0;
    private static int maxCount = 0;
    private static int modeCount = 0;
    
    private static int[] modes;

    private static void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    
    private static void inorder(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                handleValue(node.val);
                node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left;
                } else {
                    prev.right = null;
                    handleValue(node.val);
                    node = node.right;
                }
            }
        }
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
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left = null;
        root.right.right = new TreeNode(4);
        root.right.left = null;
        root.right.right.right = new TreeNode(5);
        root.right.right.left = null;
        root.right.right.right.right = new TreeNode(6);
        root.right.right.right.left = null;
        int[] res = findMode(root);
        for (int a : res) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        int[] res2 = betterFindMode(root);
        for (int a : res2) {
            System.out.print(a + " ");
        }
    }    
}
