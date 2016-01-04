package AlgoDynamicProgram;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakI_II_DP {
    /*
     * I. Given a string s and a dictionary of words dict, determine if s can be segmented into a
     * space-separated sequence of one or more dictionary words.

        For example, given
        s = "leetcode",
        dict = ["leet", "code"].

        Return true because "leetcode" can be segmented as "leet code".
     */

    // Algorithm1. DFS brute force => TLE.
    //  check every substring from index 0 to the end.

    public boolean wordBreakI_DFS_TLE(String s, Set<String> wordDict) {
        int len = s.length();

        for (int i = 1; i < len + 1; i++) {
            String subStr = s.substring(0, i);

            if (wordDict.contains(subStr)) {
                // if s is all matched, return, no need to recursion further
                if (subStr.length() == s.length()) {
                    return true;
                }
                // DFS with incremented string start
                if (wordBreakI_DFS_TLE(s.substring(i), wordDict)) {
                    return true;
                }
            }
        }

        return false;
    }


    // Algorithm2. DP
    //  t[i]==true  =>  0, (i-1) can be segmented using dictionary
    //  Initial state t[0] == true

    public boolean wordBreakI_DP(String s, Set<String> wordDict) {
        if (s == null) return true;
        int len = s.length();

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i < len + 1; i++) {

            for (int j = 0; j < i; j++) {
                dp[i] = (dp[j] && wordDict.contains(s.substring(j, i)));
                if (dp[i]) {
                    break;
                }
            }
        }

        return dp[len];
    }


	/*
     * II. Given a string s and a dictionary of words dict, add spaces in s to construct a
	 * sentence where each word is a valid dictionary word.
	 * Return *all* such possible sentences.

        For example, given
        s = "catsanddog",
        dict = ["cat", "cats", "and", "sand", "dog"].

        A solution is ["cats and dog", "cat sand dog"].
	 */

    // Algorithm: DP + BackTracking

    public List<String> wordBreakII_DP_BT(String s, Set<String> wordDict) {
        List<String> res = new ArrayList<>();

        // reuse wordBreakI
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                if (dp[i]) {
                    break;
                }
            }
        }

        if (dp[n]) {
            // since the DP proves the solution exists, go inside to output all
            StringBuilder sb = new StringBuilder();

            wordBreakIIHelper_BT(s, wordDict, sb, res, 0);
        }
        return res;
    }

    private void wordBreakIIHelper_BT(String s, Set<String> wordDict, StringBuilder sb, List<String> res, int pos) {

        if (pos == s.length()) {
            // one value break done, add to the result list
            res.add(sb.toString());
            return;
        }

        for (int i = pos + 1; i <= s.length(); i++) {
            String tmp = s.substring(pos, i);

            if (wordDict.contains(tmp)) {
                // add gap and append this new string word
                int len = sb.length();
                if (sb.length() != 0) {
                    sb.append(" ");
                }
                sb.append(tmp);

                wordBreakIIHelper_BT(s, wordDict, sb, res, i);

                // BackTracking
                sb.delete(len, sb.length());
            }
        }
    }

    public static void main(String[] args) {
        boolean res;
        WordBreakI_II_DP outer = new WordBreakI_II_DP();
        Set<String> wd = new HashSet<>();
        wd.add("leet");
        wd.add("code");
        String s = "src/main/java/leetcode";
        res = outer.wordBreakI_DP(s, wd);
        System.out.println("segment results " + res);
    }
}
