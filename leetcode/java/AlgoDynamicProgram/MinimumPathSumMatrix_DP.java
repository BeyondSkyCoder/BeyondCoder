package AlgoDynamicProgram;

public class MinimumPathSumMatrix_DP {

	/*
	 * Classic 2D DP problem
	 *
     * Given a m x n grid filled with non-negative numbers, find a path
	 *	from top left to bottom right which minimizes the sum of all numbers along its path.
	 *	Note: You can only move either down or right at any point in time.
	 */

    // Algorithm: DP w/ 2-D
    //      TF: cost[i][0] = cost[i - 1][0] + grid[i][0]
    //          cost[0][j] = cost[0][j - 1] + grid[0][j];
    //  Note: no recursion is necessary since the path allowed is only right or down
    //    first initialize row0 and col0, the row1, col1, etc.
    //    scan full matrix to fill in each cell, find the min_cost of up or left


    public int minPathSumMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        if (m * n == 0) return 0;

        int[][] cost = new int[m][n];
        cost[0][0] = grid[0][0];

        // initilaize the cost of first row and first column
        for (int i = 1; i < m; i++) { cost[i][0] = cost[i - 1][0] + grid[i][0]; }
        for (int j = 1; j < n; j++) { cost[0][j] = cost[0][j - 1] + grid[0][j]; }

        // go through every cell, calculate the total cost with previous DP cache value
        //	for each cell, only two path: come from left + come from above
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cost[i][j] = Math.min(cost[i - 1][j], cost[i][j - 1])
                        + grid[i][j];
            }
        }

        return cost[m - 1][n - 1];
    }
}
