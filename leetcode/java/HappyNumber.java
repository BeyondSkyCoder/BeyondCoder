package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example: 19 is a happy number

 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1
 */

/* Algorithm: hashmap
    The question didn't specify the endless loop pattern. A few examples show that the pattern is a set of number repeating
    So need to record each number in hashmap. if a hit occurs, abort as failure
    Otherwise, a happy number will end to 1
  */

public class HappyNumber {
    public boolean isHappy(int n) {
        Map<Integer, Integer> hm = new HashMap<>();

        int sum;
        int start;
        while (true) {
            sum = 0;

            if (hm.containsKey(n)) return false; // end in a loop

            hm.put(n, 1);
            while (n / 10 > 0) {
                int digit = n % 10;
                n /= 10;
                sum += digit * digit;
            }
            sum += n * n;

            if (sum == 1) return true;
            else { n = sum; }
        }
    }
}
