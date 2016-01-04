package AlgoDynamicProgram;

public class PaintHouse_DP {
    /*
     * I. N * 3 paint cost
     *
     * There are a row of n houses, each house can be painted with one of the three colors:
     * red, blue or green. The cost of painting each house with a certain color is different.
     * You have to paint all the houses such that no two adjacent houses have the same color.

        The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
        For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the
        cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

        Note:
        All costs are positive integers.

        | house | color1        | color2        | color3        |
        | 1     | cost[0][1]    | cost[0][2]    | cost[0][3]    |
        | 2     | (cost[0][2], cost[0][2])  |
     */
    // Algorithm: DP with 2-D
    //  TF: for the 2-D matrix

    public int minCostI(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int len = costs.length;
        int i;

        // keep the first row as original cost[][] value,
        // calculate each row/col
        for (i = 1; i < len; i++) {
            // no same color for adjacent houses
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        // find mincost of last row
        int min1 = Math.min(costs[len-1][0], costs[len-1][1]);
        int min2 = Math.min(min1, costs[i - 1][2]);

        return min2;
    }

    /*
     * II. N * K paint cost
     * There are a row of n houses, each house can be painted with one of the k colors.
     * The cost of painting each house with a certain color is different. You have to paint
     * all the houses such that no two adjacent houses have the same color.

        The cost of painting each house with a certain color is represented by a n x k
        cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0;
        costs[1][2] is the cost of painting house 1 with color 2, and so on...
        Find the minimum cost to paint all houses.
        Note:
        All costs are positive integers.
     */
    // Algorithm: DP with 2D
    //  follow problem I logic

    public int minCostII_AC_slow(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        int row = costs.length;
        int col = costs[0].length;
        int i, j;

        int minCost;

        for (i = 1; i < row; i++) {

            for (j = 0; j < col; j++) {

                minCost = Integer.MAX_VALUE;
                // find the lowest cost of above row with different column
                for (int k = 0; k < col; k++) {
                    if (k == j) continue;

                    if (minCost > costs[i - 1][k])
                        minCost = costs[i - 1][k];
                }

                costs[i][j] += minCost;
            }
        }

        // find mincost of last row
        minCost = Integer.MAX_VALUE;
        for (j = 0; j < col; j++) {
            if (minCost > costs[i - 1][j])
                minCost = costs[i - 1][j];
        }

        return minCost;
    }

    public int minCostII_AC_faster(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;

        int n = costs.length;
        int k = costs[0].length;

        // dp[j] means the min cost for color j
        int[] dp = new int[k];
        int min1 = 0;
        int min2 = 0;

        for (int i = 0; i < n; i++) {
            int oldMin1 = min1;
            int oldMin2 = min2;

            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                if (dp[j] != oldMin1 || oldMin1 == oldMin2) {
                    dp[j] = oldMin1 + costs[i][j];
                } else {
                    dp[j] = oldMin2 + costs[i][j];
                }

                if (min1 <= dp[j]) {
                    min2 = Math.min(min2, dp[j]);
                } else {
                    min2 = min1;
                    min1 = dp[j];
                }
            }

        }

        return min1;
    }

}
