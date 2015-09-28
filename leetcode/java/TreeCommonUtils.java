package leetcode;

//   !!! START FROM HERE !!!
// TREE
//		height, min height, max height
//		sametree, symmetric tree, subtree, isBST
//		DFS traversal, preorder/inorder/postorder. recursion or non recursion, 
//		BFS traversal, level list with QUEUE
//
//		Binary search tree, isBST, constructBSTfromArray, constructBSTfromLinkedList(tricky)
//		BT, isBT, constructBTfromTraversal Array
//		Serialization
//		pathSum, rotate, flattern

public class TreeCommonUtils {


	// Tree max height
    public static int countHeight(TreeNode root) {
        if (root == null )
        	return 0;
        
        return Math.max(countHeight(root.left), countHeight(root.right)) + 1;
    }

	/* 
	 * Given a binary tree, find its minimum depth.
	 * The minimum depth is the number of nodes along the shortest path from the root node 
	 * down to the nearest leaf node.
	 */
    public int minDepth(TreeNode root) {
        
        if (root == null) return 0;

        int a, b;
        a = minDepth(root.left);
        b = minDepth(root.right);
        
        if (a * b != 0) return Math.min(a, b) + 1;
        else if (a == 0) return b + 1;
        else if (b == 0) return a + 1;
        else // will never reach, but compiler wants
            return 0;
    }
    
    public int maxDepth(TreeNode root) {
       
        if (root == null) return 0;
        
        int a, b;
        a = maxDepth(root.left);
        b = maxDepth(root.right);
        
        if (a * b != 0) return Math.max(a, b) + 1;	// !!! the only difference compared with above
        else if (a == 0) return b + 1;
        else if (b == 0) return a + 1;
        else // will never reach, but compiler wants
            return 0;
	}	
    
   // this is a very useful utility
	public static boolean sameTree(TreeNode s1, TreeNode s2) {
		// both are empty
		if (s1 == null && s2 == null) return true;
		
		// only one is empty
		if (s1 == null || s2 == null) return false;
		
		if (s1.val != s2.val) {
			return false;
		} else {
			return sameTree(s1.left, s2.left) && 
					sameTree(s1.right, s2.right);
		}
	}

    // recursively build mirror tree once
	public static TreeNode mirrorTree(TreeNode root){
        if (root == null) return root;
        
        TreeNode temp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = temp;
        return root;
    }	
	
	// balanced BST rotate
	public static TreeNode rotateRight(TreeNode oldRoot) {
		TreeNode newRoot = oldRoot.left;
		oldRoot.left = newRoot.right;
		newRoot.right = oldRoot;
		return newRoot;
	}
}
