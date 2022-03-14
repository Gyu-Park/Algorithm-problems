/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) 
 * which has exactly n nodes of unique values from 1 to n.
 */
package Problems;

public class UniqueBinarySearchTrees {
    /**
     * Dinamic Programming Problem
     * 
     * left subtree * right subtree for each root number,
     * and sum up all the numbers.
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(numTrees(n));
    }
}
