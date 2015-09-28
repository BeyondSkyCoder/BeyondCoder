package leetcode;

public class StringToInterger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "2147483648";
		System.out.println(atoi_my(str));	
	}

	/* Implement atoi to convert a string to an integer.
	 * Hint: Carefully consider all possible input cases. If you want a challenge, 
	 * please do not see below and ask yourself what are the possible input cases.

	 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
	 * You are responsible to gather all the input requirements up front.
	 * Requirements for atoi:

	The function first discards as many whitespace characters as necessary until the first non-whitespace 
	character is found. Then, starting from this character, takes an optional initial plus or minus sign 
	followed by as many numerical digits as possible, and interprets them as a numerical value.
	
	The string can contain additional characters after those that form the integral number, which are 
	ignored and have no effect on the behavior of this function.

	If the first sequence of non-whitespace characters in str is not a valid integral number, or if no 
	such sequence exists because either str is empty or it contains only whitespace characters, no 
	conversion is performed.

	If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

	 */
	
	// use method Character.isWhitespace, Character.isDigital, Character.digit(x, 0);
    public static int atoi_my(String str) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (str.length() < 1) {
            return 0;
        }
        
        char c;
        int res = 0;
        boolean isneg = false;
        boolean valid = false;
        int i = 0;
        boolean overflow = false;

        // scan from beginning to find starting point
        while (i < str.length()) {            
            c = str.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
            } else if (Character.isDigit(c) || c == '-' || c == '+') {
                break;
            } else {
                return 0;
            }
        }
            
        // save sign if there is
        c = str.charAt(i);
        if (c == '-') {
            isneg = true;
            i++;
        } else if (c == '+' ) {
            i++;
        } 
        
        // continue to build the number, ignore the tail non-digital
        while (i < str.length()) {
            c = str.charAt(i);
            if (Character.isDigit(c)) {
            	int x = Character.digit(c, 10);
            	
            	// check overflow
            	if ((res < (Integer.MAX_VALUE - x) / 10)) {
            		valid = true;
            		res = res * 10 + (c - '0');
            		i++;
            	} else {
            		overflow = true;
            		break;
            	}
            } else {
                if (valid) {
                    break;
                } else {
                    // illegal
                    return 0;
                }
            }
        }
        
        if (overflow) {
        	return isneg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        res = isneg ? -res : res;
        return res;
    }
    
}
