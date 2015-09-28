package leetcode;

/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
 */

public class MaximalSquaresDP {
    /* 1-D DP with TF: 
    	dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1
    */
       
   public int maximalSquare(char[][] matrix) {
       int m = matrix.length;
       if (m==0) return 0;
       int n = matrix[0].length;
       if (m * n == 0) return 0;
       
       int [][] dp = new int[m][n];
       dp[0][0] = (int)(matrix[0][0] - '0');
       for (int i=1;i<m;i++) dp[i][0] = (int)(matrix[i][0] - '0');
       for (int j=1;j<n;j++) dp[0][j] = (int)(matrix[0][j] - '0');
       
       for (int i=1; i<m; i++) {
           for (int j=1; j<n; j++) {
               int tmp1 = Math.min(dp[i-1][j-1], dp[i-1][j]);
               int tmp2 = Math.min(dp[i][j-1], tmp1);
               if (matrix[i][j] == '1')
                   dp[i][j] = tmp2 + 1;
               else
                   dp[i][j] = 0;
           }
       }
       
       int res = 0;
       for (int i=0; i<m; i++) {
           for (int j=0; j<n; j++) {
               if (res < dp[i][j])
                   res = dp[i][j];
           }
       }
       return res*res;
   }
}
