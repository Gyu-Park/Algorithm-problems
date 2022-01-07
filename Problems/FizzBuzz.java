/**
 * Given an integer n, return a string array answer (1-indexed) where:
 * 
 * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * answer[i] == "Fizz" if i is divisible by 3.
 * answer[i] == "Buzz" if i is divisible by 5.
 * answer[i] == i (as a string) if none of the above conditions are true.
 */
package Problems;

import java.util.*;

public class FizzBuzz {
    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder st = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                res.add("FizzBuzz");
            } else if (i % 3 == 0) {
                res.add("Fizz");
            } else if (i % 5 == 0) {
                res.add("Buzz");
            } else {
                st.append(i);
                res.add(st.toString());
                st.delete(0, st.length());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 15;
        List<String> list = fizzBuzz(n);
        for (String s : list) {
            System.out.print(s + " ");
        }
    }
}
