package leetcode;

public class SumRootToLeafNumbers {
	
	/* 
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3

The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25. 
*/
	
	

	    public int sumNumbers(TreeNode root) {
	        // IMPORTANT: Please reset any member data you declared, as
	        // the same Solution instance will be reused for each test case.
	        if (root == null) return 0;
	        
	        return sum(root, 0);
	    }
	    
	    public int sum(TreeNode root, int prev) {
	    	
	    	int cursum = 10 * prev + root.val;
	        if (root.left == null && root.right == null) {
	            return cursum;
	        } else if (root.left != null && root.right != null) {
	            return sum(root.left, cursum) + sum(root.right, cursum);
	        } else if (root.left != null) {
	            return sum(root.left, cursum);
	        } else {
	            return sum(root.right, cursum);
	        }
	    }
	
}
