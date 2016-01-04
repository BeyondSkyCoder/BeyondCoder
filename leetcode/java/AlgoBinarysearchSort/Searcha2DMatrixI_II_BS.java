package AlgoBinarysearchSort;

public class Searcha2DMatrixI_II_BS {
    /*
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

        Integers in each row are sorted in ascending from left to right.
        Integers in each column are sorted in ascending from top to bottom. !! this is different than MatrixI()
        For example,
        Consider the following matrix:
        [
          [1,   4,  7, 11, 15],
          [2,   5,  8, 12, 19],
          [3,   6,  9, 16, 22],
          [10, 13, 14, 17, 24],
          [18, 21, 23, 26, 30]
        ]
	 */

    // Algorithm: start from left-bottom(or right-top) corner
    //              all right cells are bigger, all upper cells are smaller

    public boolean searchMatrixII_nonRecursive(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int row = matrix.length;
        int col = matrix[0].length;

        int m = row - 1, n = 0;     // start for left-bottom

        while (m >= 0 && n < col) { // terminate if goes out of boundary
            if (target > matrix[m][n]) {
                n++;
            } else if (target < matrix[m][n]) {
                m--;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean searchMatrixII(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            if (searchRow(matrix[i], 0, col - 1, target))
                return true;
        }
        return false;
    }

    public boolean searchRow(int[] ar, int left, int right, int target) {
        if (left > right) {
            return false;
        }

        int mid = (left + right) / 2;
        if (ar[mid] == target) {
            return true;
        } else {
            if (ar[mid] < target) {
                return searchRow(ar, mid + 1, right, target);
            } else {
                return searchRow(ar, left, mid - 1, target);
            }
        }
    }


    /*
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.
        For example,
        Consider the following matrix:
        [
          [1,   3,  5,  7],
          [10, 11, 16, 20],
          [23, 30, 34, 50]
        ] 
      * Algorithm: simple Binary Search extension
	 */
    public boolean searchMatrixI(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;

        // binary search
        while (left <= right) {
            int mid = (left + right) / 2;

            int val = matrix_of(matrix, mid);   // this is the only extra

            if (target == val) {
                return true;
            } else if (target > val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public int matrix_of(int[][] matrix, int offset) {
        int m = matrix.length;
        int n = matrix[0].length;

        int col = offset % n;
        int row = offset / n;

        return matrix[row][col];
    }
}
