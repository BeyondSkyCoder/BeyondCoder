package leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.
 */
public class TwoSum_design {
    Map<Integer, Integer> hm = new HashMap<>();

	public void add(int number) {		
	    if (hm.containsKey(number)) {
	        hm.put(number, hm.get(number) + 1);
	        return;
	    }
	    hm.put(number, 1);
	}

	public boolean find(int value) {
	    for (int i : hm.keySet()) {
	        int j = value - i;
	    	
	        if (hm.containsKey(j)) {
	            if (j != i) {
    	                return true;
	            }
	            else {  // if that's itself, make sure it has more than 2
	                if (hm.get(i) > 1) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	public static void main(String[] args) {
		TwoSum_design outer = new TwoSum_design();
		outer.add(0);
		outer.add(0);
		System.out.println(outer.find(0));
	}
}
