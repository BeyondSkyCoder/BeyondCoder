package AlgoDynamicProgram;

import java.util.Arrays;

public class PerfectSquare_DP {
	/*
	 * Given a positive integer n, find the least number of perfect square numbers
	 * (for example, 1, 4, 9, 16, ...) which sum to n.

        For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
	 */
    // Algorithm: DP
    //  dp[i] means the least number of square numbers up to i-1

    public int numSquares(int n) {
        if (n == 0) return 0;
        if (n == 0) return 1;


        int[] dp = new int[n + 1];
        // 1. fill all with MAX
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 2. fill those lucky ones: square of one number
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        // 3. fill others with help of dp[i] up to dp[n]
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; i + j * j <= n; j++) {
                // dp[i] plus one more square
                dp[i + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
            }
        }
        return dp[n];
    }
}
