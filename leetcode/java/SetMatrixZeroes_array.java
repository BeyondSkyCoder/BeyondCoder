package leetcode;

public class SetMatrixZeroes_array {

	/* 
	 *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 *  
	 *  
	 * Follow up: Did you use extra space?
	 * A straight forward solution using O(mn) space is probably a bad idea.
	 * A simple improvement uses O(m + n) space, but still not the best solution.
	 * Could you devise a constant space solution?
	 */
	
    public void setZeroes(int[][] matrix) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int m = matrix.length;
        int n = matrix[0].length;
        
        // O(m+n) space
        int row[] = new int[m];
        int col[] = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	// if either row or col was set before, clear the cell
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}
