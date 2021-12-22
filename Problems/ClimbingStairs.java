/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * 
 * Each time you can either climb 1 or 2 steps. 
 * In how many distinct ways can you climb to the top?
 */
package Problems;

public class ClimbingStairs {
    public static int climbStairs(int n) {
        if (n == 1 || n == 2)
            return n;

        int j = 0;
        int k = 0;
        int l = 0;
        for (int i = 1; i <= n; i++) {
            if (n > 2 && i == 2) {
                j = 1;
                k = 2;
            }
            if (n > 2 && i > 2) {
                l = j;
                j = k;
                k = k + l;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(climbStairs(n));
    }
}
