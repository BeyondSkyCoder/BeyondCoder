package leetcode;

/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

public class DecodeWays_DP {
    /* transition function: 
     * 		d(i) = {d(i - 1), if str[i - 1] != 0} + {d(i - 2), if str[i - 1, i - 2] is 1X (X = 1 ~ 9) or 2X (X = 1 ~ 6)}
    */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        
        if (len == 0 || s.charAt(0) == '0') return 0;   // no solution for leading '0'
        
        int[] dp = new int[len + 1];
        dp[0] = 1;
        int i;
        
        for (i=1; i<=len; i++) {
            if (s.charAt(i-1) != '0') dp[i] += dp[i-1];
            
            if (i >= 2 && (s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6')))
                dp[i] += dp[i-2];
        }
        
        return dp[len];
    }
}
