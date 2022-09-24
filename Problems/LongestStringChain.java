/**
 * You are given an array of words where each word consists of lowercase English letters.
 * 
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA 
 * without changing the order of the other characters to make it equal to wordB.
 * 
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * 
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, 
 * where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. 
 * A single word is trivially a word chain with k == 1.
 * 
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 */
package Problems;

import java.util.*;

public class LongestStringChain {
    // solution with memoization and top-down dfs
    // time complexity: O(n + (n * l^2)) -> O(n * l^2)
    // space complexity: O(n + n) -> O(n)
    public static int longestStrChain(String[] words) {
        Set<String> wordsSet = new HashSet<>(); // O(n)
        Collections.addAll(wordsSet, words);    // O(n) 
        Map<String, Integer> memo = new HashMap<>(); // O(n)
        int res = 0;
        for (String word : words) { // O(n)
            res = Math.max(res, dfs(memo, wordsSet, word));
        }
        return res;
    }

    private static int dfs(Map<String, Integer> memo, Set<String> wordsSet, String word) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }

        StringBuilder sb = new StringBuilder(word);
        int maxLen = 1;

        for (int i = 0; i < word.length(); i++) { // O(l)
            sb.deleteCharAt(i);
            String newWord = sb.toString(); // O(l)
            if (wordsSet.contains(newWord)) {
                maxLen = Math.max(maxLen, 1 + dfs(memo, wordsSet, newWord));
            }
            sb.insert(i, word.charAt(i));
        }
        memo.put(word, maxLen);
        return maxLen;
    }

    // solution with dp bottom-up
    // time complexity: O(nlogn + (n * l^2))
    // space complexity: O(n)
    public static int longestStrChain2(String[] words) {
        Map<String, Integer> dp = new HashMap<>(); // O(n)
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // O(nlogn)
        int res = 0;
        for (String word : words) { // O(n)
            StringBuilder sb = new StringBuilder(word);
            int curLen = 1;
            for (int i = 0; i < word.length(); i++) { // O(l)
                sb.deleteCharAt(i);
                curLen = Math.max(curLen, dp.getOrDefault(sb.toString(), 0) + 1);
                sb.insert(i, word.charAt(i)); // O(l)
            }
            res = Math.max(res, curLen);
            dp.put(word, curLen);
        }
        return res;
    }
    
    public static void main(String[] args) {
        String[] words = { "abcd", "abc", "bcd", "abd", "ab", "ad", "b" };
        System.out.println(longestStrChain2(words));
    }
}
