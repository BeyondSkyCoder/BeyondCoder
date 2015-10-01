package leetcode;

/*
 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which 
the depth of the two subtrees of every node never differ by more than 1.

 */
public class BalancedBinaryTree {

	// recursion, O(n2)
	
	// Algorithm1: not very efficient since countHeight is called repeatedly
	//  to improve efficiency, need to check the hl and hr. if return false, abort.
	
    public boolean isBalanced1_bfs(TreeNode root) {

        int hl, hr;
        if (root == null) {
            return true;
        }
        
        hl = TreeCommonUtils.countHeight(root.left);
        hr = TreeCommonUtils.countHeight(root.right);
        if (Math.abs(hl - hr) > 1) {
            return false;
        } else {
            return isBalanced1_bfs(root.left) && isBalanced1_bfs(root.right);
        }
    }
    
    
    // Algorithm2, optimized, O(N) time and O(log(N)) space using DP, abort in the middle if failure already
    public boolean isBalanced_dp(TreeNode root) {
        return treeCheckBalanceHelper(root) != -1;
    }
    
    public static int treeCheckBalanceHelper(TreeNode root) {
    	if (root == null) return 0;	// height
    	
    	int leftHeight = treeCheckBalanceHelper(root.left);
    	if (leftHeight == -1)	return -1; // not balanced
    	
    	int rightHeight = treeCheckBalanceHelper(root.right);
    	if (rightHeight == -1) 	return -1;
    	
    	int heightDiff = leftHeight - rightHeight;
    	if (Math.abs(heightDiff) > 1)	return -1;
    	
    	return Math.max(leftHeight,  rightHeight) + 1;
    }
}
