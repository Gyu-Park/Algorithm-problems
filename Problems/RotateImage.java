/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * 
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
 * DO NOT allocate another 2D matrix and do the rotation.
 */
package Problems;

public class RotateImage {
    public static void rotate(int[][] matrix) {
        int matLen = matrix.length - 1;
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = i; j < matLen - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[matLen - j][i];
                matrix[matLen - j][i] = matrix[matLen - i][matLen - j];
                matrix[matLen - i][matLen - j] = matrix[j][matLen - i];
                matrix[j][matLen - i] = tmp;
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
