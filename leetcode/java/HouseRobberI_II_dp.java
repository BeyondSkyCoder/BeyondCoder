package leetcode;

public class HouseRobberI_II_dp {
	/*
	 * houses are arranged in a circle.
	 * 
	 * Algorithm: DP with two scan for selecting 0, but not the last or not selecting 0, but select last
	 * 
	 */
    public int robII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        if (len == 1) return nums[0];
        else if (len == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp1 = new int[len + 1]; 
        int[] dp2 = new int[len + 2]; 
 
        dp1[0] = 0;
        dp1[1] = nums[0];
        int i, j;
        
        for (i=2; i<len; i++) {
            dp1[i] = Math.max(dp1[i-2] + nums[i-1], dp1[i-1]);
        }

        dp2[1] = 0;
        dp2[2] = nums[1];
        
        for (j=3; j<=len; j++) {
            dp2[j] = Math.max(dp2[j-2] + nums[j-1], dp2[j-1]);
        }
        
        return Math.max(dp1[i-1], dp2[j-1]);
    } 	
    
	/*
	 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

	 */
    public int robI(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        else if (len == 2) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        int i;
        for (i=2; i<=len; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i-1], dp[i-1]);		// Transfer function
        }
        return dp[i-1];
    }
}
