package AlgoDynamicProgram;

public class PaintFence_DP {
    /*
     * There is a fence with n posts, each post can be painted with one of the k colors.

        You have to paint all the posts such that no more than two adjacent fence posts have the same color.

        Return the total number of ways you can paint the fence.
     */

    // Algorithm: DP
    //  dp[i] = (k-1) * dp[1] + (k-1) * dp[2]
    //  base: dp[1] = k, dp[2] = k <= two adjance paint can be the same

    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;

        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;

        for (int i = 3; i < n+1; i++) {
            // fence i must be either different with i-2 or i-1, can't be same to both
            dp[i] = (k-1) * dp[i-1] + (k-1) * dp[i-2];
        }
        return dp[n];
    }
}
