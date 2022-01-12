/**
 * Given a string containing digits from 2-9 inclusive, 
 * return all possible letter combinations that the number could represent. 
 * Return the answer in any order.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below. 
 * Note that 1 does not map to any letters.
 */
package Problems;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        List<String> list = new LinkedList<>();
        if (digits == null || digits.length() == 0)
            return list;
        char[][] map = { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
                { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };
        recursion(digits, list, map, new StringBuilder(), 0);
        return list;
    }

    private static void recursion(String digits, List<String> list, char[][] map, StringBuilder sb, int start) {
        if (start == digits.length()) {
            list.add(new String(sb));
            return;
        }
        int num = digits.charAt(start) - '0';
        for (int i = 0; i < map[num].length; i++) {
            sb.append(map[num][i]);
            recursion(digits, list, map, sb, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // bfs solution
    public static List<String> anotherLetterCombinations(String digits) {
        List<String> ans = new LinkedList<String>();
        if (digits.isEmpty())
            return ans;
        String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        ans.add(0, "");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            int size = ans.size();
            for (int k = 1; k <= size; k++) {
                String t = ans.remove(0);
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String digits = "235";
        List<String> list = anotherLetterCombinations(digits);
        for (String st : list) {
            System.out.print(st + " ");
        }
    }
}
