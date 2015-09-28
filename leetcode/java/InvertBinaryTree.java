package leetcode;

/* 
 * Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */

// mirror tree

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        invertTreeHelper(root);
        return root;
    }
    
    public void invertTreeHelper(TreeNode root) {
        if (root == null) return ;
        
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        if (root.left != null) invertTreeHelper(root.left);
        if (root.right != null) invertTreeHelper(root.right);
    }
}
