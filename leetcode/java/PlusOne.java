package leetcode;

public class PlusOne {
	
	// pay attention to final results, highest bit may also have carry bit set
    public int[] plusOne(int[] digits) {

    	int carry = 0, sum;
        int len = digits.length;
        int plus = 1;
        
        for (int i = len - 1; i >= 0 ; i--) {
            sum = carry + digits[i] + plus;
            if (sum >= 10) {
                carry = 1;
                sum %= 10;
            } else {
                carry = 0;
            }
            
            // reset plus to 0 always after first run
            plus = 0;
            digits[i] = sum;
        }

        if (carry == 0) {
            return digits;
        } else {
            int[] res = new int[len + 1];
            res[0] = carry;
            System.arraycopy(digits, 0, res, 1, len);
            return res;
        }
    }
}
