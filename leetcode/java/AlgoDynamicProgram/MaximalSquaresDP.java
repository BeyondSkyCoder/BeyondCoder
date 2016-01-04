package AlgoDynamicProgram;

public class MaximalSquaresDP {
    /*
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.

        For example, given the following matrix:

        1 0 1 0 0
        1 0 1 1 1
        1 1 1 1 1
        1 0 0 1 0

        Return 4.
     */

    // Algorithm: DP with 2-D
    //  TF: dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        if (m * n == 0) return 0;

        int[][] dp = new int[m][n];
        dp[0][0] = (int) (matrix[0][0] - '0');

        // init the first row and col
        for (int i = 1; i < m; i++) dp[i][0] = (int) (matrix[i][0] - '0');
        for (int j = 1; j < n; j++) dp[0][j] = (int) (matrix[0][j] - '0');

        // scan and fill in the 2-D matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                // found the smallest of up-left, up, left
                int tmp1 = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                int tmp2 = Math.min(dp[i][j - 1], tmp1);

                // tmp2 can be either 0 or 1 (if all up-left, up, left are 1
                if (matrix[i][j] == '1')
                    dp[i][j] = tmp2 + 1;
                else {
                    // this cell is '0', discard all previous accumulation, set it to 0
                    dp[i][j] = 0;
                }
            }
        }


        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res < dp[i][j])
                    res = dp[i][j];
            }
        }
        return res * res;
    }
}
