/**
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 * 
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 */
package Problems;

import java.util.*;

public class ConcatenatedWords {
    // dp solution
    // time complexity: O(nk^3) where n is the number of words array and k is the number of words[i].length
    // space complexity: O(2n + k)
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(); // O(n)
        for (String word : words)
            set.add(word);

        List<String> res = new ArrayList<>(); // O(n)
        for (String word : words) { // O(n * k^3)
            set.remove(word);
            boolean[] dp = new boolean[word.length() + 1]; // O(k)
            for (int i = 1; i <= word.length(); i++) { // O(k^2)
                if (set.contains(word.substring(0, i)))
                    dp[i] = true;
            }

            if (isConcatenatedWord(word, dp, set)) // O(k^3)
                res.add(word);
            set.add(word);
        }
        return res;
    }

    private static boolean isConcatenatedWord(String word, boolean[] dp, Set<String> set) {
        for (int i = 1; i < dp.length; i++) { // O(k^3)
            if (dp[i]) {
                for (int j = i; j <= word.length(); j++) { // O(k^2)
                    if (set.contains(word.substring(i, j))) // O(k)
                        dp[j] = true;
                }
            }
        }
        return dp[dp.length - 1];
    }

    // another dp solution
    // time complexity: O(nk^3)
    // spcae complexity: O(2n + k)
    public static List<String> anotherFindAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>(); // O(n)
        Set<String> preWords = new HashSet<>();  // O(n)
        Arrays.sort(words, new Comparator<String>() {  // O(nlogn)
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        for (int i = 0; i < words.length; i++) { // O(n * k^2)
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }
        
        return result;
    }
	
    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1]; // O(k)
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) { // O(k)
            for (int j = 0; j < i; j++) { // O(k)
            if (!dp[j]) continue;
            if (dict.contains(word.substring(j, i))) { // O(k)
                dp[i] = true;
                break;
            }
            }
        }
        return dp[word.length()];
    }
    
    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> res = findAllConcatenatedWordsInADict(words);
        for (String st : res) {
            System.out.println(st);
        }

        List<String> list = anotherFindAllConcatenatedWordsInADict(words);
        for (String st : list) {
            System.out.println(st);
        }
    }
}
