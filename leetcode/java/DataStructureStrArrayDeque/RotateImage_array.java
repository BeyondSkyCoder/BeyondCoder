package DataStructureStrArrayDeque;

public class RotateImage_array {

	/*
     * You are given an n x n 2D matrix representing an image.
	 *	Rotate the image by 90 degrees (clockwise).
	 * Follow up: Could you do this in-place?
	 */

    // Algorithm is very simple, but should draw a graph to get mind clear
    //   start from the outer layer, rotate row/column
    //   loop into inner layer, repeat
    public void rotate(int[][] matrix) {

        int n = matrix.length;
        int tmp;
        for (int i = 0; i < n; i++) {
            for (int offset = i; offset < n - 1 - i; offset++) {
                tmp = matrix[i][offset];
                matrix[i][offset] = matrix[n - 1 - offset][i];
                matrix[n - 1 - offset][i] = matrix[n - 1 - i][n - 1 - offset];
                matrix[n - 1 - i][n - 1 - offset] = matrix[offset][n - 1 - i];
                matrix[offset][n - 1 - i] = tmp;
            }
        }
    }
}
