package leetcode;

public class PalindromNumber_math {


	/* 
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 * 
	 * 
	 * Could negative integers be palindromes? (ie, -1)
	 * If you are thinking of converting the integer to string, note the restriction of using extra space.
	 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
	 * you know that the reversed integer might overflow. How would you handle such case?
	 * There is a more generic way of solving this problem.
	 */
	
    public boolean isPalindrome(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (x < 0) {
            return false;
        }
        

        // find the highest digit
        int div = 1;        
        while (x / div >= 10) {
            div *= 10;	// avoid overflow
        }
        
        // start with LSB and RSB, move to middle
        while (x != 0) {
            int lsb = x / div;
            int rsb = x % 10;
            if (lsb != rsb) {
                return false;
            } else {
                // cut off lsb and find the remaining, then right shift one digit
                x = (x % div) / 10;
                div /= 100;
            }
        }
                
        return true;
    }
}
