package AlgoMathBitProbability;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

// Algorithm: math, summation of series

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        int expected = n * (n + 1) / 2;
        if (sum == expected) return 0;
        else return expected - sum;
    }
}
