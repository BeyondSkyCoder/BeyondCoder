package leetcode;

public class ClimbingStairs_DP {

	/* 
	 *	You are climbing a stair case. It takes n steps to reach to the top.
		Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? 
	 */
    public int climbStairsRecursion(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (n == 0) return 0;
        
        int[] cache = new int[n];
        for (int i = 0; i < n; i++) {
            cache[i] = -1;
        }
        
        return climbStairs_helper(n, cache);
    }
    
    // partial DP: recursive with caching
    public int climbStairs_helper(int n, int[] cache){
        if (n == 0) return 1;
        else if (n < 0) return 0;
        
        // DP to cache intermediate results
        if (cache[n-1] == -1) {
            cache[n-1] = climbStairs_helper(n-1, cache);
        }
        if (n > 1 && cache[n-2] == -1) {
            cache[n-2] = climbStairs_helper(n-2, cache);
        }
        return cache[n -1] + (n > 1 ? cache[n-2] : 0);
    }

    // real DP, O(n)
    public int climbStairsIterative(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (n < 2) return 1;
        int[] steps = new int[n];
        steps[0] = 1;
        steps[1] = 2;
        for (int i = 2; i < n; i++){
            steps[i] = steps[i - 1] + steps[i - 2];
        }
        return steps[n - 1];
    }
}
