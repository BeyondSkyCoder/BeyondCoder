package leetcode;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

 Note: If the given node has no in-order successor in the tree, return null.
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessorTBD(TreeNode root, TreeNode p) {
        if (root == null) return null;

        TreeNode res;
        if (root.val <= p.val) {
            return inorderSuccessorTBD(root.right, p);
        } else {
            TreeNode left = inorderSuccessorTBD(root.left, p);
            return ( left != null && left.val > p.val ) ? left : root;
        }
    }
}