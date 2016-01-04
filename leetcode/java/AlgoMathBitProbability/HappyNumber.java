package AlgoMathBitProbability;

import java.util.HashMap;
import java.util.Map;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the
 * sum of squares of its digits, and repeat the
 * process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example: 19 is a happy number
 *  1^2 + 9^2 = 82
 *  8^2 + 2^2 = 68
 *  6^2 + 8^2 = 100
 *  1^2 + 0^2 + 0^2 = 1
 */

/* Algorithm: Math calculate sum continuous till 1 or looping(abort)

    The question didn't specify the endless loop pattern. A few experiments
    show that the pattern is a set of number repeating.
    So record and catch that by using hashmap. if a hit occurs, abort as failure
    HashMap[intermediate_result] -> count
  */

public class HappyNumber {
    public boolean isHappy(int n) {
        Map<Integer, Integer> hm = new HashMap<>();

        int sum;
        while (true) {
            if (hm.containsKey(n)) {
                return false; // looping, abort
            }

            hm.put(n, 1);

            // calcuate sum or each digits of the number
            sum = 0;
            while (n / 10 > 0) {
                int digit = n % 10;
                n /= 10;
                sum += digit * digit;
            }
            sum += n * n;   // n reduces to single digit after while loop, sum it as well

            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
    }
}
