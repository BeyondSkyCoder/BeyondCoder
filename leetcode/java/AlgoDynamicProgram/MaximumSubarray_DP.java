package AlgoDynamicProgram;

public class MaximumSubarray_DP {
    /**
     * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
     *
     * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
     * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     *
     * More practice:
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
     * which is more subtle.
     */

    // Algorithm: DP
    //  TF: dp[i] = Math.max(dp[i - 2], dp[i - 1] + nums[i - 1])
    //  Note: this is opposite of house robber. must continuous w/ not gap
    //      second part means keep [0...i-2] and add i-1.

    public int maxSubArray(int[] nums) {
        int len = nums.length;

        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 1; i < len + 1; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 1] + nums[i - 1]);
        }

        return dp[len];
    }
}
