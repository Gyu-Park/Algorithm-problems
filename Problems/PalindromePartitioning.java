/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. 
 * Return all possible palindrome partitioning of s.
 * 
 * A palindrome string is a string that reads the same backward as forward.
 */
package Problems;

import java.util.*;

public class PalindromePartitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if (s.length() == 1) {
            list.add(s);
            res.add(list);
            return res;
        }
        recursion(s, 0, list, res);
        return res;
    }

    private static void recursion(String s, int index, List<String> list, List<List<String>> res) {
        if (index > s.length() - 1) {
            res.add(new ArrayList<>(list));
            return;
        }
        StringBuilder sb = new StringBuilder();
        boolean isPalindrome = false;
        for (int i = index; i < s.length(); i++) {
            sb.append(s.charAt(i));
            isPalindrome = isPalindrome(sb.toString());
            if (isPalindrome) {
                list.add(sb.toString());
                recursion(s, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    // a faster solution (dp + dfs)
    public List<List<String>> anotherPartition(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                path.add(s.substring(pos, i + 1));
                helper(res, path, dp, s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = partition(s);
        for (List<String> list : res) {
            for (String st : list) {
                System.out.print(st + " ");
            }
            System.out.println();
        }
    }
}
