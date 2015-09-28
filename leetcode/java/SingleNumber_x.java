package leetcode;

import java.util.HashMap;
import java.util.Iterator;
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
    public int[] singleNumberIII(int[] nums) {
    	return new int[0];
    }	
	
	/*
	 * Given an array of integers, every element appears three times except for one. Find that single one.
	 */
    public int singleNumberII(int[] nums) {
    	
    	return 0;
    }	
	
	/* 
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * Note:
	 * Your algorithm should have a linear runtime complexity. Could you implement it without 
	 * using extra memory ? 
	 */
    public int singleNumberI(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < A.length; i++) {
            if (hm.containsKey(A[i])) {
                hm.put(A[i], 1 + hm.get(A[i]));
            } else {
                hm.put(A[i], 1);
            }
        }

        for (Object o : hm.entrySet()) {
            Map.Entry pairs = (Map.Entry) o;
            Integer cnt = (Integer) pairs.getValue();
            if (cnt == 1) {
                Integer key = (Integer) pairs.getKey();
                return (int) key;
            }
        }
        
        return 0;
    }
}
