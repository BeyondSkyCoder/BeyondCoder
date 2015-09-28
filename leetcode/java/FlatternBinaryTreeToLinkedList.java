package leetcode;

public class FlatternBinaryTreeToLinkedList {
	/* 
	 *  Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6

The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

click to show hints.
Hints:

If you notice carefully in the flattened tree, each node's right child points to the next node 
of a pre-order traversal.

	 */

	public static void flattenTreeToRight(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        if (root == null)            return;
        if (root.left == null && root.right == null) return;
        
        if (root.left != null) {
        	// start from root, save right first
            TreeNode tmp = root.right;
            
            // move original left to right to form flatten(right-oriented) node
            root.right = root.left;
            root.left = null;
            
            // find the rightmost from original left half of the tree, link it with original right node
            TreeNode tmp_leright = util_findRightMost(root.right);
            tmp_leright.right = tmp;
            
            // start recursion with one level down
            flattenTreeToRight(root.right);
            
        } else if (root.right != null) {
        	
        	// after left is done, continue flatten process with right for each recursion
        	flattenTreeToRight(root.right);
        }

    }
    
    public static TreeNode util_findRightMost(TreeNode root) {
        if (root.right != null)
        	return util_findRightMost(root.right);
        else
        	return root;
    }
}
