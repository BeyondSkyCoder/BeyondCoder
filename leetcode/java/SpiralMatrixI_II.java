package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixI_II {
	
	/* 
	 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

	For example,
	Given n = 3,

	You should return the following matrix:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]
	 */	
    public static int[][] generateMatrixII(int n) {
	    int[][] matrix = new int[n][n];
    	if (n==0) return matrix;
	   
	    int i, j, k;
	    int cnt=1;
	    
	    // use the offset to loop through all the spiral order
	    // Mzhu, trick is to check and break the loop if next attempt to is invalid
	    for (i=0; i<=n/2; i++) {
	        if (i>=n-i) break;
	        for (j=i; j<n-i;j++) matrix[i][j]=cnt++;
	        if (i>=n-1-i) break;
	        for (k=i+1;k<n-i;k++) matrix[k][n-1-i]=cnt++;
	        if (n-1-i-1<i) break;
	        for (j=n-1-i-1; j>=i; j--) matrix[n-1-i][j]=cnt++;
	        if (i>=n-1-i-1) break;
	        for (k=n-1-i-1; k>i; k--) matrix[k][i]=cnt++;
	    }
	    // assert(cnt-1 == n*n);
	    
        return matrix;
    }	
    
    /* 
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

    For example,
    Given the following matrix:

    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
     */
	public List<Integer> spiralOrderI(int[][] matrix) {
	    List<Integer> res = new ArrayList<>();
	    if (matrix == null || matrix.length == 0 ) return res;
	    
	    int m = matrix.length;
	    int n = matrix[0].length;
	    
	    int i, j, k;
	    
	    // use the offset to loop through all the spiral order
	    // Mzhu, trick is to check and break the loop if next attempt to is invalid
	    for (i=0; i<=n/2 && i<=m/2; i++) {
	        if (i>=n-i) break;
	        for (j=i; j<n-i;j++) res.add(matrix[i][j]);
	        if (i>=m-1-i) break;
	        for (k=i+1;k<m-i;k++) res.add(matrix[k][n-1-i]);
	        if (n-1-i-1<i) break;
	        for (j=n-1-i-1; j>=i; j--) res.add(matrix[m-1-i][j]);
	        if (i>=m-1-i-1) break;
	        for (k=m-1-i-1; k>i; k--) res.add(matrix[k][i]);
	    }
	    return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		generateMatrixII(3);
	}

}
