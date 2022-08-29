/**
 * Given the root of a binary tree, the value of a target node target, and an integer k, 
 * return an array of the values of all nodes that have a distance k from the target node.
 * 
 * You can return the answer in any order.
 */
package Problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {

    static Set<Integer> visited = new HashSet<>();

    // solution with a hashmap to build a graph
    // time complexity: O(max(number of neighbors)^k) ---- not sure though
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            res.add(target.val);
            return res;
        }
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildMap(root, null, map); // O(n)
        List<TreeNode> list = map.get(target);
        visited.add(target.val);
        findDistVal(map, list, res, k, 1, target); // O(max(number of neighbors)^k)
        return res;
    }

    private static void findDistVal(Map<TreeNode, List<TreeNode>> map, List<TreeNode> list, List<Integer> res, int k, int dist, TreeNode target) {
        if (dist > k)
            return;
        for (int i = 0; i < list.size(); i++) {
            TreeNode node = list.get(i);
            if (visited.contains(node.val))
                continue;
            visited.add(node.val);
            if (k == dist && node.val != target.val) {
                res.add(node.val);
                continue;
            }
            findDistVal(map, map.get(node), res, k, dist + 1, target);
        }
    }

    private static void buildMap(TreeNode node, TreeNode parent, Map<TreeNode, List<TreeNode>> map) {
        if (node == null)
            return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<TreeNode>());
            if (parent != null) {
                map.get(parent).add(node);
                map.get(node).add(parent);
            }
            buildMap(node.left, node, map);
            buildMap(node.right, node, map);
        }
    }

    // another solution with HashMap
    // key = node, val = distance from a target
    static Map<TreeNode, Integer> map = new HashMap<>();
    public static List<Integer> anotherDistanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs(root, K, map.get(root), res);
        return res;
    }
    
    // find target node first and store the distance in that path that we could use it later directly
    private static int find(TreeNode root, TreeNode target) {
        if (root == null) 
            return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
		int right = find(root.right, target);
		if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    private static void dfs(TreeNode root, int K, int length, List<Integer> res) {
        if (root == null) 
            return;
        if (map.containsKey(root)) 
            length = map.get(root);
        if (length == K) 
            res.add(root.val);
        dfs(root.left, K, length + 1, res);
        dfs(root.right, K, length + 1, res);
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode l5 = new TreeNode(4);
        TreeNode l4 = new TreeNode(5);
        TreeNode l3 = new TreeNode(3);
        l3.left = l5;
        TreeNode l2 = new TreeNode(2);
        l2.left = null;
        l2.right = l4;
        TreeNode l1 = new TreeNode(1);
        l1.left = null;
        l1.right = l3;
        TreeNode root = new TreeNode(0);
        root.left = l1;
        root.right = l2;
        System.out.println(anotherDistanceK(root, l3, 3));
    }
}
