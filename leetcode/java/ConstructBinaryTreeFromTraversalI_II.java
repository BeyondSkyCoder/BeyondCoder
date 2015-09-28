package leetcode;

public class ConstructBinaryTreeFromTraversalI_II {
	/*
	 * Given inorder and postorder traversal of a tree, construct the binary tree.
	 */
	// the root is at the end of postorder, but mid of inorder
	//	find the root, divide the array by half, recursion.
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        return buildTreeHelper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    // take two array and start/end index
    
    // Postorder: { left tree - right tree - root }
    // 		the end of postorder is always the new root !
    // Inorder: { left tree - root - right tree }
    //		the root is at the middle
    // Preorder: { root - left tree - right tree }
    //		the root is at the beginning
    public static TreeNode buildTreeHelper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie || ps > pe) return null;
        
        TreeNode root = new TreeNode(postorder[pe]);
        
        for (int i =  is; i <= ie; i++) {
            // find the root from inorder, should be in middle
            if (inorder[i] == root.val) {
            	
                int len = i - 1 - is;
                // divide array into two parts, skip current root
                TreeNode left = buildTreeHelper(inorder, is, i - 1, postorder, ps, ps + len);
                len = ie - i - 1;
                TreeNode right = buildTreeHelper(inorder, i + 1, ie, postorder, pe - 1 - len , pe - 1);
                root.left = left;
                root.right = right;
            }
        }
        return root;
    }
    
    /*
     * Given preorder and inorder traversal of a tree, construct the binary tree.
Note:
You may assume that duplicates do not exist in the tree. 
     */
	// the root is at the beginning of preorder, but mid of inorder
	//	find the root, divide the array by half, recursion.
    
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        return buildTreeHelper2(inorder, 0, inorder.length-1, preorder, 0, preorder.length-1);
    }
    
    public static TreeNode buildTreeHelper2(int[] inorder, int is, int ie, int[] preorder, int ps, int pe) {
        if (is > ie || ps > pe) return null;
        
        // the beginning of preorder is always the new root. This is the major different from above
        TreeNode root = new TreeNode(preorder[ps]);
        
        for (int i =  is; i <= ie; i++) {
            // find the root from inorder, should be in middle
            if (inorder[i] == root.val) {
                int len = i - 1 - is;
                // divide array into two parts, skip current root
                TreeNode left = buildTreeHelper(inorder, is, i - 1, preorder, ps + 1, ps + 1 + len);
                len = ie - i - 1;
                TreeNode right = buildTreeHelper(inorder, i + 1, ie, preorder, pe - len , pe);
                root.left = left;
                root.right = right;
            }
        }
        return root;
    }
    
}
