package leetcode;

import java.util.ArrayList;

public class SymmetricTree {
	/*
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3

But the following is not:

    1
   / \
  2   2
   \   \
   3    3

	 */
	
	
	// Algorithm1, convert to array/list, then check palindromic
    public boolean isSymmetric1(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return true;
        
        isSymmetricHelper(root, result);
        int len = result.size();
        for (int i = 0; i < len / 2; i++) {
            if (result.get(i) != result.get(len - 1 - i))
                return false;
        }         
        return true;
    }
    
    // In-order traversal
    public static void isSymmetricHelper(TreeNode root, ArrayList<Integer> res) {
        if (root == null) return;
        
        isSymmetricHelper(root.left, res);
        res.add(root.val);
        isSymmetricHelper(root.right, res);
    }
    
    // Algorithm2, without array
    public boolean isSymmetric2(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(root == null) return true;
        
        return TreeCommonUtils.sameTree(root.left, TreeCommonUtils.mirrorTree(root.right));
    }
        
}
