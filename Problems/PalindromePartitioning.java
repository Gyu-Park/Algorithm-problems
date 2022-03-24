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
