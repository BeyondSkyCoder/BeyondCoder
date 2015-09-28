package leetcode;

public class ClosestBinarySearchTreeValue {
	/*
	 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
	 */
	// Algorithm: binary search
	
    public int closestValue(TreeNode root, double target) {
    	int res = root.val;
    	
    	
    	while (root != null) {

    		root = (target > root.val) ? root.right : root.left;
    		
    		if (root != null) {
    			double dif1 = Math.abs(target - root.val);
    			double dif2 = Math.abs(target - res);
    			res = dif1 < dif2 ? root.val : res;
    		}
    	}
    	return res;
    }
}
