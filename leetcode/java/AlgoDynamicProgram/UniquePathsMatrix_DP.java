package AlgoDynamicProgram;

public class UniquePathsMatrix_DP {
    /*
        A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

        The robot can only move either down or right at any point in time. The robot is trying to reach
        the bottom-right corner of the grid (marked 'Finish' in the diagram below).

        How many possible unique paths are there ?
        Note: m and n will be at most 100.
     */
    // Algorithm: DP with 2-D
    //      like pathSum matrix

    public int uniquePaths(int m, int n) {
        if (m < 0 || n < 0) return 0;

        int[][] dp = new int[m][n];

        // base case, 1 path
        dp[0][0] = 1;

        // init the first row and col, 1 path
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1];

        // scan and fill the rest matrix for number of paths (from up and left)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // return the right-bottom corner value
        return dp[m - 1][n - 1];
    }

    /*
        II. Follow up for "Unique Paths":

        Now consider if some obstacles are added to the grids. How many unique paths would there be?

        An obstacle and empty space is marked as 1 and 0 respectively in the grid.

        For example,
        There is one obstacle in the middle of a 3x3 grid as illustrated below.

        [
          [0,0,0],
          [0,1,0],
          [0,0,0]
        ]
        The total number of unique paths is 2.

        Note: m and n will be at most 100.
     */
    // Algorithm: DP with 2-D
    //      same as problem I and count the blocking

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = (obstacleGrid[0][0] == 0) ? 1 : 0;

        // initialize first row and col
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1) dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] != 1) dp[0][j] = dp[0][j - 1];
        }

        // scan and update the rest matrix
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
