/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. 
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */
package Problems;

public class SearchA2DMatrixII {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1 && n == 1)
            return target == matrix[0][0];
        boolean res = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == target || matrix[i][n - 1] == target)
                return true;
            if (matrix[i][0] < target && matrix[i][n - 1] > target)
                res = helper(matrix, i, target);
            if (res)
                return res;
        }
        return res;
    }

    private static boolean helper(int[][] matrix, int row, int target) {
        int start = 0;
        int end = matrix[row].length - 1;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (matrix[row][mid] == target || matrix[row][start] == target 
                || matrix[row][end] == target)
                return true;
            else if (matrix[row][mid] > target)
                end = mid - 1;
            else if (matrix[row][mid] < target)
                start = mid + 1;
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {
                            {1, 4, 7, 11, 15},
                            {2, 5, 8, 12, 19},
                            {3, 6, 9, 16, 22},
                            {10, 13, 14, 17, 24},
                            {18, 21, 23, 26, 30}
                        };
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }
}
