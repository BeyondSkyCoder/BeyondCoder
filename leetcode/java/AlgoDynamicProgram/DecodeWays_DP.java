package AlgoDynamicProgram;

public class DecodeWays_DP {
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

    // Algorithm: DP
    //  dp[i] means the decodes way for s[0...i-1]. need to new dp[s.len+1]
    // TF: d(i) = dp[i-1] if s[i-1] is a valid char
    //      or  = dp[i-1] + dp[i-2] is s[i-1 and s[i-2] together is a valid char
    //      valid char means: for single digit, it's decodable, not  '0' for two digits, it's 1x or 2y, y=0-6]

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();

        if (len == 0 || s.charAt(0) == '0') return 0;   // no solution for leading '0'

        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int i = 1; i <= len; i++) {

            // case I, s[i-1] is valid char(independently decodable, not 0)
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // case II, s[i-2, i-1] is valid ("1x" or "2y", where y<=6)
            if (i >= 2 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')))
                dp[i] += dp[i - 2];
        }

        return dp[len];
    }
}
