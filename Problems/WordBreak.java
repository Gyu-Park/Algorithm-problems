/**
 * Given a string s and a dictionary of strings wordDict, 
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
package Problems;

import java.util.*;

public class WordBreak {
    // dp solution
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }

    // recursive solution (slow)
    public static boolean anotherWordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return recursion(s, set);
    }

    private static boolean recursion(String s, Set<String> set) {
        if (s.length() == 0)
            return true;
        for (int i = 1; i <= s.length(); i++) {
            if (set.contains(s.substring(0, i)) && recursion(s.substring(i), set)) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("aaa");
        wordDict.add("aaaa");
        System.out.println(wordBreak(s, wordDict));
    }
}
