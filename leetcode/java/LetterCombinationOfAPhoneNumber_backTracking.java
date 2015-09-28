package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfAPhoneNumber_backTracking {
	
	// Telephone words
	//  from left to right: whenever a letter changes, its right neighbor goes through all of its values before the 
	//		original letter changes again. 
	//  from right to left: Conversely whenever a letter resets t its low value, its left neighbor increases to the next value.
    public List<String> letterCombinations(String digits) {
		String[] keyMap = new String[] { null, null, "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        if (digits.length() == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        letterCombinationsHelper(digits, 0, keyMap, res, sb);      
        return res;
    }
    
    public static void letterCombinationsHelper(String digits, int count, String[] km, List<String> results, StringBuilder s2) {
    	if (digits.length() == count) {
    		results.add(s2.toString());
    		return;
    	}
    	
    	String key = km[digits.charAt(count) - '0'];	// small optimization: use '2', and remove the null, null from keyMap

    	for (int i = 0; i < key.length(); i++) {	// append, run recursion, then backup one char
    		s2.append(key.charAt(i));
    		letterCombinationsHelper(digits, count + 1, km, results, s2);
    		s2.deleteCharAt(s2.length() -  1);
    	}
	}
    
}
