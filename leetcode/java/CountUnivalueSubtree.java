package leetcode;

/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
 */

public class CountUnivalueSubtree {
	public int countUnivalSubtrees(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int[] count = new int[1];

		univalueSubTreeHelper(root, count, root.val);
		return count[0];
	}

	public boolean univalueSubTreeHelper(TreeNode root, int[] count, int parent_val) {

		if (root == null) {
			return true;
		}

		boolean l = univalueSubTreeHelper(root.left, count, root.val);
		boolean r = univalueSubTreeHelper(root.right, count, root.val);

		if (l && r) {
			count[0]++;
		}

		return l && r && (root.val == parent_val);
	}

}
