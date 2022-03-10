/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. 
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 */

package Problems;

public class Search2DMatrix {
    // A Solution using two binary search
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1])
            return false;

        int col = 0;
        int start = 0;
        int end = matrix.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target >= matrix[mid][0] && target < matrix[mid + 1][0]) {
                col = mid;
                break;
            } else if (target > matrix[mid][0]) {
                start = mid + 1;
            } else if (target < matrix[mid][0]) {
                end = mid - 1;
            }
            if (start == end)
                col = start;
        }

        start = 0;
        end = matrix[col].length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == matrix[col][mid]) {
                return true;
            } else if (target > matrix[col][mid]) {
                start = mid + 1;
            } else if (target < matrix[col][mid]) {
                end = mid - 1;
            }
        }
        return false;
    }

    // Solution with one binary search (treat matrix as an one demensional array)
    public boolean anotherSearchMatrix(int[][] matrix, int target) {

        int row = matrix.length;
        int col = matrix[0].length;
        int begin = 0;
        int end = row * col - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            int mid_value = matrix[mid / col][mid % col];
            if (mid_value == target) {
                return true;
            } else if (mid_value < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
        int target = 23;
        System.out.println(searchMatrix(matrix, target));
    }
}
