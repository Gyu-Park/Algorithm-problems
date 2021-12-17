package Problems;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {

        if (s.length() == 1)
            return 1;

        Set<String> charContainer = new HashSet<>();
        ArrayList<Integer> substringLengthList = new ArrayList<>();
        StringBuilder substring = new StringBuilder("");
        StringBuilder temp = new StringBuilder("");
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            if (!charContainer.contains(s.substring(i, i + 1))) {
                charContainer.add(s.substring(i, i + 1));
                substring.append(s.substring(i, i + 1));
            } else {
                result.append(substring);
                charContainer.clear();
                for (int j = substring.length(); j > 0; j--) {
                    if (substring.substring(j - 1, j).equals(s.substring(i, i + 1))) {
                        temp.append(substring.substring(j, substring.length()));
                        substring.delete(0, substring.length());
                        substring.append(temp);
                        temp.delete(0, temp.length());
                        substring.append(s.substring(i, i + 1));
                        charContainer.add(s.substring(i, i + 1));
                        break;
                    }
                    charContainer.add(substring.substring(j - 1, j));
                }
                substringLengthList.add(result.length());
                result.delete(0, result.length());
            }
        }
        substringLengthList.add(substring.length());

        return Collections.max(substringLengthList);
    }

    public static int betterSolution(String s) {

        int result = 0;
        int[] cache = new int[128]; // array length = number of ASCII code

        for (int i = 0, j = 0; i < s.length(); i++) {
            j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j; // j = left pointer

            cache[s.charAt(i)] = i + 1;
            /**
             * i(index) = right pointer and
             * i+1 is a number of letters from 0 index to iindex.
             */

            result = Math.max(result, i - j + 1);
            /**
             * i - j + 1 = right pointer(index) - left pointer(index) + 1
             * index starts from 0, so index number is not a number of letters.
             * That's why index+1 is needed.
             */
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "agvguabcdupleart";

        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(betterSolution(s));
    }
}
