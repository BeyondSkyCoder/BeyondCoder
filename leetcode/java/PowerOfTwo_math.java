package leetcode;

/**
 * Given an integer, write a function to determine if it is a power of two.
 */
public class PowerOfTwo_math {
    public boolean isPowerOfTwo(int n) {

        int sum = 0;
        // negative number are false
        if (n < 0) return false;

        // should have only one bit as 1
        while (n != 0) {
            if ((n & 0x1) != 0) {
                sum++;
                if (sum > 1) return false;
            }
            n >>= 1;
        }
        return sum == 1;
    }
}
