/**
 * In MATLAB, there is a handy function called reshape 
 * which can reshape an m x n matrix into a new one with a different size r x c keeping its original data.
 * 
 * You are given an m x n matrix mat and two integers r and c representing the number of rows 
 * and the number of columns of the wanted reshaped matrix.
 * 
 * The reshaped matrix should be filled with all the elements of the original matrix 
 * in the same row-traversing order as they were.
 * 
 * If the reshape operation with given parameters is possible and legal, 
 * output the new reshaped matrix; Otherwise, output the original matrix.
 */
package Problems;

public class ReshapeTheMatrix {
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int oriMatArea = mat.length * mat[0].length;
        int modifiedArea = r * c;
        if (oriMatArea != modifiedArea) 
            return mat;
        int numInEachRow = oriMatArea / r;
        int m = 0;
        int n = 0;
        int[][] res = new int[r][numInEachRow];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < numInEachRow; j++) {
                res[i][j] = mat[m][n++];
                if (n == mat[0].length) {
                    m++;
                    n = 0;
                }
            }
        }
        return res;
    }

    // more concise solution
    public int[][] betterMatrixReshape(int[][] nums, int r, int c) {
        int n = nums.length;
        int m = nums[0].length;
        if (r * c != n * m) 
            return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c ;i ++) 
            res[i / c][i % c] = nums[i / m][i % m];
        return res;
    }
    
    public static void main(String[] args) {
        int[][] mat = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 }, { 17, 18, 19, 20 } };
        int[][] res = matrixReshape(mat, 42, 5);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
