package leetcode;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

 */

public class JumpGameI_II_greedy {
    public int jump(int[] nums) {
    	int reach = 0;
    	int prev = 0, max = 0; 
    	int jumps = 0;
    	

    	for (int i=0; i < nums.length; i++) {
    		if (i > prev) {	// must jump now, had been lazy to save jump steps
    			if (i > max) return -1;	// can't reach
    			
    			prev = max;     // greedy: jump to maxs
    			jumps++;
    		}
    		max = Math.max(max, i + nums[i]);
    	}
    	
    	return jumps;
    }	
	
    public boolean canJump(int[] nums) {
        int reach = 0;
        
        // if max reach node is out of i, stop
        for (int i=0; reach < nums.length; i++) {
            if (i > reach) break;	// not reachable, abort 

        	reach = Math.max(reach, i + nums[i]);
        }
        
        return (reach >= nums.length - 1);
    }
}
