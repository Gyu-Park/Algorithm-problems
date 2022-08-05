/**
 * Given a matrix of integers, we'd like to consider the sum of the elements within the area of a 45° rotated rectangle. 
 * More formally, the area is bounded by two diagonals parallel to the main diagonal 
 * and two diagonals parallel to the secondary diagonal. 
 * The dimensions of the rotated rectangle are defined by the number of elements along the borders of the rectangle. 
 * Given integers a and b representing the dimensions of the rotated rectangle, and matrix (a matrix of integers),
 * your task is to find the greatest sum of integers contained within an a x b rotated rectangle.
 * 
 * Note: The order of the dimensions is not important - consider all a x b and b x a rectangles.
 */
package Problems;

public class RotatedRectSum {
    public static int solve(int[][] matrix, int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int res = Integer.MIN_VALUE;
        if (a == b) {
            for (int i = a - 1; i < matrix.length; i++) {
                for (int j = a - 1; j < matrix[i].length; j++) {
                    if (i + (a - 1) < matrix.length && j + (a - 1) < matrix[i].length) {
                        res = Math.max(res, bottomToUp(matrix, a, i, j - (a - 1)) 
                            + upToBottom(matrix, a - 1, i + 1, j - (a - 2)));
                    }
                }
            }
        } else {
            int move = b - a;
            for (int i = a - 1; i < matrix.length; i++) {
                if (i + move + a >= matrix.length)
                    break;
                for (int j = a - 1; j < matrix[i].length - (a - 1); j++) {
                    int upTriangle = 0;
                    int midRectangle = 0;
                    int bottomTriangle = 0;
                    // right diagonal rectangle
                    if (j + move + (a - 1) < matrix[i].length) {
                        upTriangle = bottomToUp(matrix, a, i, j - (a - 1));
                        midRectangle = midSum(matrix, a * 2 - 1, move - 1, i + 1, j + 1, false);
                        bottomTriangle = upToBottom(matrix, a, i + move, (j + move) - (a - 1));
                        res = Math.max(res, upTriangle + midRectangle + bottomTriangle);
                    }
                    // left diagonal rectangle
                    if (j - move - (a - 1) >= 0) {
                        upTriangle = bottomToUp(matrix, a, i, j - (a - 1));
                        midRectangle = midSum(matrix, a * 2 - 1, move - 1, i + 1, j - 1, true);
                        bottomTriangle = upToBottom(matrix, a, i + move, (j - move) - (a - 1));
                        res = Math.max(res, upTriangle + midRectangle + bottomTriangle);
                    }
                }
            }
        }
        return res;
    }

    private static int bottomToUp(int[][] matrix, int a, int i, int j) {
        if (a == 1)
            return matrix[i][j];
        int start = j;
        int end = start + (a * 2 - 1);
        int sum = 0;
        while (start < end) {
            sum += matrix[i][start++];
        }
        sum += bottomToUp(matrix, a - 1, i - 1, j + 1);
        return sum;
    }

    private static int upToBottom(int[][] matrix, int a, int i, int j) {
        if (a == 1)
            return matrix[i][j];
        int start = j;
        int end = start + (a * 2 - 1);
        int sum = 0;
        while (start < end) {
            sum += matrix[i][start++];
        }
        sum += upToBottom(matrix, a - 1, i + 1, j + 1);
        return sum;
    }

    private static int midSum(int[][] matrix, int length, int height, int i, int j, boolean left) {
        if (height == 0)
            return 0;
        int start = j - (length / 2);
        int end = start + length;
        int sum = 0;
        while (start < end) {
            sum += matrix[i][start++];
        }
        if (left) {
            sum += midSum(matrix, length, height - 1, i + 1, j - 1, left);
        } else {
            sum += midSum(matrix, length, height - 1, i + 1, j + 1, left);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0},
                        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0}
                        };
        int a = 3;
        int b = 6;
        int max = solve(matrix, a, b);
        System.out.println(max);
    }
}
