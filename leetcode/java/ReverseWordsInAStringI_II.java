package leetcode;

public class ReverseWordsInAStringI_II {
	// II doesn't allow leading or trailing space or more than one space in between
	
	// in-place, no extra space reverse
    public void reverseWords(char[] s) {
        
        reverseWordsHelper(s, 0, s.length);
        
        int i, j;
        
        for (i = 0, j = 0; j <= s.length; j++) {
            if (j == s.length || s[j] == ' ') {
                reverseWordsHelper(s, i, j);
                i = j + 1;
            }
        }
    }
    
    public static void reverseWordsHelper(char [] s, int start, int end) {
        char tmp;
        for (int i = 0 ; i < (end - start)/2; i++) {
            tmp = s[start + i];
            s[start + i] = s[end - i - 1];
            s[end - i - 1] = tmp;
        }
    }
    
    // I. allow space anywhere
    /* What constitutes a word?
    		A sequence of non-space characters constitutes a word.
    	Could the input string contain leading or trailing spaces?
    		Yes. However, your reversed string should not contain leading or trailing spaces.
    	How about multiple spaces between two words?
    		Reduce them to a single space in the reversed string.
    */
    		
    public String reverseWordsI(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
 
		// split to words by space, it does all the tricks of managing spaces
		String[] arr = s.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; --i) {
			
			// due to the use of s.split(" "), if there is leading space, there will be one "" string in the array, skip it
			if (!arr[i].equals("")) {
				sb.append(arr[i]).append(" ");
			}
		}
		// handle special case and convert it to string type
		return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
	}
    
}
