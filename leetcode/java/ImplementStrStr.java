package leetcode;

public class ImplementStrStr {


	/*
	 * Implement strStr().
	 * Returns a pointer to the first occurrence of needle in haystack, 
	 * or null if needle is not part of haystack. 
	 */
	// use String method: substring(), equals()
    public String strStr(String haystack, String needle) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int len1 = needle.length();
        int len2 = haystack.length();
        if (len1 == 0 || len2 == 0) {
            return null;
        }
        
        if (needle.equals("") || haystack.equals(needle)) {
            return haystack;
        }
 
        int i;       
        for (i = 0; i < len2-len1; i++) {
            if (haystack.substring(i, i+len1).equals(needle)) {
                return haystack.substring(i);
            }
        }
        
        return null;
    }
}
