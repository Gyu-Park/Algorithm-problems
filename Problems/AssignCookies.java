/**
 * Assume you are an awesome parent and want to give your children some cookies. 
 * But, you should give each child at most one cookie.
 * 
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; 
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. 
 * Your goal is to maximize the number of your content children and output the maximum number.
 */
package Problems;

import java.util.Arrays;

public class AssignCookies {
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int j = 0;
        for (int i = 0; i < s.length; i++) {
            if (j >= g.length)
                break;
            if (g[j] <= s[i])
                j++;
        }
        return j;
    }

    public static void main(String[] args) {
        int[] g = { 10, 9, 8, 7 };
        int[] s = { 5, 6, 7, 8 };
        System.out.println(findContentChildren(g, s));
    }
}
