/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * 
 * You may return the answer in any order.
 */
package Problems;

import java.util.*;

public class Combinations {

    static List<List<Integer>> res;

    // recursive solution
    /**
     * time complexity O(n^k) <- roughly if considering only the first tree
     * and where k > 1 && k < n
     * Precisely, time complexity O(n!/(n-k)!k!), which is C(n,k) = n choose k
     * or time complexity also can be said O(n ^ (n / 2)),
     * which is O(n^min(k, n-k)),
     * and it is the upper-bound for "n choose k" complexity.
     **/
    // space complexity O(k)
    public static List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<>();
        if (n < k)
            return res;
        helper(n, k - 1, 1, list);
        return res;
    }

    private static void helper(int n, int k, int index, List<Integer> list) {
        for (int i = index; i <= n - k; i++) {
            list.add(i);
            if (k == 0)
                res.add(new ArrayList<>(list));
            else
                helper(n, k - 1, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    // another solution with a different approach
    public static List<List<Integer>> anotherCombine(int n, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k > n || k < 0) {
            return res;
        }
        if (k == 0) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        res = anotherCombine(n - 1, k - 1);
        for (List<Integer> list : res) {
            list.add(n);
        }
        res.addAll(anotherCombine(n - 1, k));
        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 4;
        List<List<Integer>> res = combine(n, k);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
