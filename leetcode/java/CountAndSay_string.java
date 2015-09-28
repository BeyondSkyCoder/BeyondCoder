package leetcode;

public class CountAndSay_string {

	/*
	 * The count-and-say sequence is the sequence of integers beginning as follows:
		1, 11, 21, 1211, 111221, ...
		
		1 is read off as "one 1" or 11.
		11 is read off as "two 1s" or 21.
		21 is read off as "one 2, then one 1" or 1211.
		
		Given an integer n, generate the nth sequence.
		Note: The sequence of integers will be represented as a string. 
	 */
	
    public static String countAndSay(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (n == 1) return "1";
        
        String s = countAndSay(n-1);

        StringBuilder res = new StringBuilder();
        int i = 0, j;
        while (i < s.length()) {
        	
        	// pick a char, count the same one
            char ch = s.charAt(i);
            j = 1;
            // the i points to the char *before* the different one
            while (i+1 < s.length() && s.charAt(i+1) == ch) {
                i++;
                j++;
            }
            // there are j number of i, use to form the results
            char prefix = (char)(j + '0');
            res.append(prefix);
            res.append(ch);
            
            // move to next one(different)
            i++;
        }
        return res.toString();
    }
    
}
