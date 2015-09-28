package leetcode;

/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

 *  DP: 
 *  动态规划数组是dp[i][j]，表示：s1取前i位，s2取前j位，是否能组成s3的前i+j位。
 *  初始化是，假设s1为空，那么s2每一位跟s3匹配放入dp[0][j]；假设s2为空，那么s1每一位跟s3匹配放入dp[i][0]。
 *
 */

public class InterleavingString {
    public boolean isInterleaveTBD(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int len1 = s1.length(), len2 = s2.length();
        if (len1 == 0) return s2.equals(s3);
        if (len2 == 0) return s1.equals(s3);

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        int i, j;

        for (i=1; i<=len1; i++) dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i-1));
        for (j=1; j<=len2; j++) dp[0][j] = dp[0][j-1] && (s2.charAt(j-1) == s3.charAt(j-1));
        
        for (i=1; i<=len1; i++) {
            for (j=1; j<=len2; j++) {
                char c = s3.charAt(i+j-1);  // -1 because dp[0][0] was not used
                // check from up or from left path is valid
                dp[i][j] = dp[i-1][j] && (c == s1.charAt(i-1)) ||
                    dp[i][j-1] && (c == s2.charAt(j-1));
            }
        }

        return dp[i-1][j-1];
    }
}
