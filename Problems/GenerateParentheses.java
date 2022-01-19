/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 */
package Problems;

import java.util.*;

public class GenerateParentheses {
    static List<String> list = new ArrayList<>();

    public static List<String> generateParenthesis(int n) {
        String res = "";
        recursion(n, n, true, res);
        return list;
    }

    // n = number of open brackets, m = number of close brackets
    private static void recursion(int n, int m, boolean open, String res) {
        if (n > 0 && open) {
            res += "(";
            n--;
        }
        if (m > n && !open) {
            res += ")";
            m--;
        }
        if (n > 0)
            recursion(n, m, true, res);
        if (m > n)
            recursion(n, m, false, res);
        if (n == 0 && m == 0) {
            list.add(res);
        }
    }

    // iterative solution
    public static List<String> iterativeGenerateParenthesis(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }

        List<List<String>> lists = new ArrayList<>();
        lists.add(Collections.singletonList(""));

        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String first : lists.get(j)) {
                    for (String second : lists.get(i - 1 - j)) {
                        StringBuilder sb = new StringBuilder("(");
                        sb.append(first).append(")").append(second);
                        list.add(sb.toString());
                    }
                }
            }
            lists.add(list);
        }

        return lists.get(n);
    }

    public static void main(String[] args) {
        int n = 4;
        List<String> list = generateParenthesis(n);
        for (String s : list) {
            System.out.print(s + " ");
        }

        System.out.println(" ");

        List<String> list2 = iterativeGenerateParenthesis(n);
        for (String s : list2) {
            System.out.print(s + " ");
        }
    }
}
