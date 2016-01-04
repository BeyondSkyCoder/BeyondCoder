package AlgoMathBitProbability;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumber_x {
    /*
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

        For example:

        Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

        Note:
        The order of the result is not important. So in the above example, [5, 3] is also correct.
        Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?

	 */

    // Algorithm: bit ^ to find unique element. it makes element of two occurences disappear

    public int[] singleNumberIII(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;    // only the two appear once left
        }
        diff = Integer.highestOneBit(diff); // the bit 1 can separate the above two special element, appeared once

        int[] result = new int[2];
        Arrays.fill(result, 0);
        for (int num : nums) {
            if ((diff & num) == 0) {    // group one with one special element, other appearing twice elements will cancel themselves
                result[0] ^= num;
            } else {                    // group two with the other special element
                result[1] ^= num;
            }
        }
        return result;
    }
	
	/*
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

	  */

    /* algorithm: bit
        Time ~ O(32N), Space ~ O(1)
        add same bit for each number, mod 3. The left is the old one.
     */
    public int singleNumberII(int[] nums) {
        int[] count = new int[32];  // 32-bit integer
        Arrays.fill(count, 0);
        int result = 0;
        for (int i = 0; i < 32; i++) {  // 1st loop must be bit: otherwise count % 3 will not be 0 or 1
            for (int j = 0; j < nums.length; j++)
                if (((nums[j] >> i) & 1) == 1) count[i]++;
            result |= ((count[i] % 3) << i);
        }
        return result;
    }

    /*
     * Given an array of integers, every element appears twice except for one. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without
     * using extra memory ?
     */
    public int singleNumberI(int[] A) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (hm.containsKey(A[i])) {
                hm.put(A[i], 1 + hm.get(A[i]));
            } else {
                hm.put(A[i], 1);
            }
        }

        for (Map.Entry pairs : hm.entrySet()) {
            Integer cnt = (Integer) pairs.getValue();
            if (cnt == 1) {
                Integer key = (Integer) pairs.getKey();
                return (int) key;
            }
        }

        return 0;
    }

    /*
    Use XOR:
0 ^ 0 = 1 ^ 1 = 0
0 ^ 1 = 1 ^ 0 = 1
     */

    public int singleNumberI_bit(int[] A) {
        int num = 0;
        for (int x : A) {
            num ^= x;
        }
        return num;
    }
}
