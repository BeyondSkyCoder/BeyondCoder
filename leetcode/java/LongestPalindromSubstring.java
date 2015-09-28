package leetcode;

public class LongestPalindromSubstring {

	
	// check if a string is a palindrom, ignoring non alphanumeric character
	public boolean isPalindromIgnoreOther(String s) {
		int i = 0, j = s.length() - 1;
		
		while (i < j) {
			while (i<j && !Character.isLetterOrDigit(s.charAt(i))) i++;
			while (i<j && Character.isLetterOrDigit(s.charAt(i))) j--;
			if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
				return false;
			}
			i++;
			j--;
		}
		
		return true;
	}

	/* 
	 * Given a string S, find the longest palindromic substring in S. 
	 * You may assume that the maximum length of S is 1000, and there exists one 
	 * unique longest palindromic substring.
	 */
	
    public String longestPalindrome(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s.length() == 0) return new String();
        else if (s.length() == 1) return s;
        
        int len = s.length();
        int max[] = new int[len];
        for (int i = 0; i < len; i++) { max[i] = 0; }
        int l, r;
        
        for (int i = 0; i < len; i++) {
        	
            // case A. check if center is at i, span to left and right, check ==
        	// move l and r pointers outside to both ends
            l = i;
            r = i;
            while (l >= 0 && r < len) {
                if (s.charAt(l) == s.charAt(r)) {
                    max[i] = r - l + 1;
                    l--;
                    r++;
                } else {
                    break;
                }
            }
            
            // case B. check if center is at i and i + 1, span to left and right
            l = i;
            r = i + 1;
            while (l >= 0 && r < len) {
                if (s.charAt(l) == s.charAt(r)) {
                    
                    if (max[i] < r - l + 1) {
                        max[i] = r- l + 1;
                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }
        
        // find the global max length palindrom
        int max_len = 0;
        int pivot = -1;
        for (int i = 0; i < len; i++) {
            if (max_len < max[i]) {
                max_len = max[i];
                pivot = i;
            }
        }
        
        return s.substring( pivot - (max_len - 1) / 2, pivot + (max_len) / 2 + 1);
    }  
}
