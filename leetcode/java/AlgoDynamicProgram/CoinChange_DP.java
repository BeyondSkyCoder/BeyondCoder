package AlgoDynamicProgram;

import java.util.HashSet;
import java.util.Set;

public class CoinChange_DP {
    /*
		coin change problem, not Leetcode
		using the minimum number of coins to make change for a particular amount of cents, sum
		    using a given set of denominations d1, d2, ...dm
	 */
    // Algorithm: DP
    //      use dp[] to record intermediate results
    //      For a special case of US coin, Greedy is be optimal.

    public Set<Integer> coinsChange_DP(Set<Integer> coins, int sum) {
        int[] dp = new int[sum + 1];
        int[] last_coin = new int[sum + 1];

        for (int i = 0; i <= sum; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;
        last_coin[0] = 1;

        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j < i; j++) {

                if (coins.contains(i - j)) {
                    // dp[i] can be dp[j] + one_coin(recorded into last_coin[])
                    if (dp[j] + 1 < dp[i]) {
                        dp[i] = dp[j] + 1;
                        last_coin[i] = (i - j);
                    }
                }
            }
        }

        Set<Integer> ret = new HashSet<>();
        while (sum > 0) {
            ret.add(last_coin[sum]);
            sum -= last_coin[sum];
        }
        return ret;
    }
}
