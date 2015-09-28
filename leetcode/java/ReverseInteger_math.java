package leetcode;

import java.util.ArrayList;
import java.util.Iterator;

public class ReverseInteger_math {


	/* 
	 * Reverse digits of an integer.

	Example1: x = 123, return 321
	Example2: x = -123, return -321
	
	Here are some good questions to ask before coding. 
	
	If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
	Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
	then the reverse of 1000000003 overflows. How should you handle such cases?
	Throw an exception? Good, but what if throwing an exception is not an option? You would then 
	have to re-design the function (ie, add an extra parameter).
	 *
	 */
	
    public int reverse(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        boolean isneg = false;
        if (x < 0) {
            isneg = true;
            x = -x;
        }
        int newnum = 0;
        boolean nonzero = false;
        
        // convert the number digits to Array, reverse during the procedure
        ArrayList<Integer> al = new ArrayList<>();
        while (x != 0) {
            al.add(x % 10);
            x /= 10;
        }
        
        // must-do: skip front zero
        Iterator it = al.iterator();
        Integer tmp;
        while (it.hasNext()) {
            tmp = (Integer)it.next();
            
            // filter out leading zeros
            if (!nonzero && tmp == 0) {

            } else {
                nonzero = true;
                newnum = newnum * 10 + tmp;
            }
        }
        
        if (isneg) {
            newnum = - newnum;
        }
        
        newnum = (newnum > Integer.MAX_VALUE) ? Integer.MAX_VALUE : newnum;
        newnum = (newnum < Integer.MIN_VALUE) ? Integer.MIN_VALUE : newnum;
        
        return newnum;
    }   
}
