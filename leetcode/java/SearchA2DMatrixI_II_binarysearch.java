package leetcode;

public class SearchA2DMatrixI_II_binarysearch {
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
	
	// Algorithm: search every row with binary search within the row
	
    public boolean searchMatrixII(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        for (int i = 0; i < row; i++) {
            if (searchRow(matrix[i], 0, col - 1, target))
                return true;
        }
        return false;
    }
    
    public boolean searchRow(int[] ar, int low, int high, int target) {
        if (low > high) return false;
        
        int mid = (low + high ) / 2;
        if (ar[mid] == target) {
            return true;
        } else {
            if (ar[mid] < target) {
                return searchRow(ar, mid + 1, high, target);
            } else {
                return searchRow(ar, low, mid - 1, target);
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
	 */
    public boolean searchMatrixI(int[][] matrix, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int start = 0, end = m * n - 1;
        
        // binary search
        while (start <= end) {
            int mid = (start + end) / 2;
            int val = matrix_of(matrix, mid);
            if (target == val) {
                return true;
            } else if (target > val) {
                start = mid + 1;  
            } else {
                end = mid -1;
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
