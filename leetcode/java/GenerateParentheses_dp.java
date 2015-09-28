package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GenerateParentheses_dp {
	// very classic recursion
	
	/*
	 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed 
	 *  parentheses.
	For example, given n = 3, a solution set is:
	"((()))", "(()())", "(())()", "()(())", "()()()" 
	 */
	
    public List<String> generateParenthesis(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<String> al = new ArrayList<>();
        
        // use set to keep unique(HashSet), but convert to ArrayList for results
        Set<String> res = generateParenthesisHelper(n);
        al.addAll(res.stream().collect(Collectors.toList()));
        
        return al;
    }
    
    public Set<String> generateParenthesisHelper(int n) {
        Set<String> setp = new HashSet<>();
        
        if (n == 0) {
            setp.add("");
            return setp;
        }
        
        // generate the case of n-1
        Set<String> prevset = generateParenthesisHelper(n-1);
        
        // from n-1 to n, 
        // scan through the each word from previous set, insert pair () after every ( 
        for (String word: prevset) {
        	
        	
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == '(') {
                    String newvalid = insertParenthesisValid(word, i);
                    // use set to ensure uniqueness
                    if (!setp.contains(newvalid)) {
                        setp.add(newvalid);
                    }
                }
            }
        
            // CRITICAL: add the special case with () at the front
            if (!setp.contains("()"+word)) {
                setp.add("()"+word);
            }
        } 
        return setp;
    }
    
    public String insertParenthesisValid(String str, int index) {
        String start = str.substring(0, index+1);
        String end = str.substring(index + 1);
        return start + "()" + end;
    }
}
