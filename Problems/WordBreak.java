/**
 * Given a string s and a dictionary of strings wordDict, 
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
package Problems;

import java.util.*;

public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c);
            if (set.contains(sb.toString())) {
                sb.delete(0, sb.length());
            }
        }

        return sb.length() == 0;
    }
    
    public static void main(String[] args) {
        String s = "aaaaaaa";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("aaaa");
        wordDict.add("aaa");
        System.out.println(wordBreak(s, wordDict));
    }
}
