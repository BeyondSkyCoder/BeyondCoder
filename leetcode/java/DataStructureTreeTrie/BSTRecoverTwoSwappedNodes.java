package DataStructureTreeTrie;

import java.util.ArrayList;

public class BSTRecoverTwoSwappedNodes {
	/*
     *  Two elements of a binary search tree (BST) are swapped by mistake.
		Recover the tree without changing its structure.	
		Note: A solution using O(n) space is pretty straight forward. 
		Could you devise a constant space solution?
	 */

    public void recoverTreeTBD(TreeNode root) {
        ArrayList<TreeNode> inorder = new ArrayList<>();
        if (root == null) return;
        inorderTraversal(root, inorder);

        int error1 = 0, error2;
        int prev = 0;
        for (int i = 1; i < inorder.size(); i++) {
            if (inorder.get(i).val > inorder.get(prev).val) {
                prev = i;
            } else {
                // prev is bigger and the bad one
                error1 = prev;
                break;
            }
        }

        error2 = prev + 1;
        // find the smallest afterward
        for (int i = error2 + 1; i < inorder.size(); i++) {
            if (inorder.get(i).val < inorder.get(error2).val) {
                error2 = i;
            }
        }

        int tmp = inorder.get(error1).val;
        inorder.get(error1).val = inorder.get(error2).val;
        inorder.get(error2).val = tmp;
        // the original node value are fixed, tot need to return anything

    }

    public void inorderTraversal(TreeNode root, ArrayList<TreeNode> results) {
        if (root == null) return;

        inorderTraversal(root.left, results);
        results.add(root);
        inorderTraversal(root.right, results);
    }

}
