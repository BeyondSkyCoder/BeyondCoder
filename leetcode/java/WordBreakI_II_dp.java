package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakI_II_dp {
	/*
	 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where 
	each word is a valid dictionary word.

	Return all such possible sentences.

	For example, given
	s = "catsanddog",
	dict = ["cat", "cats", "and", "sand", "dog"].

	A solution is ["cats and dog", "cat sand dog"].

	 */

	/* 
	 * 测试结果表明，找所有partitions用DFS + DP最快！Two DPs 比 DFS + DP 慢很多，且占用大量内存。
	 * 
	 */
    public List<String> wordBreakII_DP_DFS(String s, Set<String> wordDict) {
    	
    	// reuse wordBreakI
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                if (dp[i])   break;
            }
        }
        
        // extra DFS if word is breakable from above
        List<String> ll = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        if (dp[n])
        	wordBreakIIHelper_DFS(s, 0, wordDict, sb, ll);
        return ll;
    }

    // wordBreakIIHelper_DFS
    private void wordBreakIIHelper_DFS(String s, int j, Set<String> wordDict, StringBuilder str, List<String> res) {
    	
        if (j == s.length()) {
        	// one round finish, add to the result set
            res.add(str.toString());
        } else {
            for (int i = j + 1; i <= s.length(); i++) {
            	
                if (wordDict.contains(s.substring(j, i))) {
                    int len = str.length();
                    if (len != 0)
                    	str.append(" ");
                    str.append(s.substring(j, i));
                    
                    wordBreakIIHelper_DFS(s, i, wordDict, str, res);
                    
                    str.delete(len, str.length());	// delete new segment after the above dfs
                }
            }
        }
    }
 
    /*
     * Given a string s and a dictionary of words dict, determine if s can be segmented into a 
     * space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

     */
	// I. DP
	// Define an array t[] such that t[i]==true => 0, (i-1) can be segmented using dictionary
	// Initial state t[0] == true    
    // transition function is ?
    public boolean wordBreakI_DP(String s, Set<String> wordDict) {
        if (s == null) return true;
        int len = s.length();
        
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
 
        for(int i=1; i<=len; i++){
            for (int j=0;j<i;j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
 
        return dp[len];
    }
    
	// II. brute force will TLE.
	// just check every substring from index 0 to the end.
	public boolean wordBreakBruteForceTLE(String s, Set<String> wordDict) {
		int len = s.length();
		boolean flag = false;
          
		for(int i = 1; i <= len; i++){
			String subStr = s.substring(0, i);
			if(wordDict.contains(subStr)){
				if(subStr.length() == s.length()){
					return true;
				}   
				flag = wordBreakBruteForceTLE(s.substring(i), wordDict);
			}
		}
		return flag;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean res;
		WordBreakI_II_dp outer = new WordBreakI_II_dp();
		Set<String> wd = new HashSet<>();
		wd.add("leet");
		wd.add("code");
		String s = "leetcode";
		res = outer.wordBreakI_DP(s, wd);
		System.out.println("segment results " + res);
	}    
}
