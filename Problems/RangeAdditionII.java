/**
 * You are given an m x n matrix M initialized with all 0's and an array of operations ops, 
 * where ops[i] = [ai, bi] means M[x][y] should be incremented by one for all 0 <= x < ai and 0 <= y < bi.
 * 
 * Count and return the number of maximum integers in the matrix after performing all the operations.
 */
package Problems;

public class RangeAdditionII {
    public static int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0)
            return m * n;
        int mm = Integer.MAX_VALUE;
        int nn = Integer.MAX_VALUE;
        for (int[] i : ops) {
            mm = Math.min(mm, i[0]);
            nn = Math.min(nn, i[1]);
        }
        return mm * nn;
    }

    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] ops = { { 2, 2 }, { 3, 3 } };
        System.out.println(maxCount(m, n, ops));
    }
}
