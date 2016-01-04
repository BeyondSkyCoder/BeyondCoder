package DataStructureTreeTrie;

/*
 * Given a binary tree, determine if it is height-balanced.
    For this problem, a height-balanced binary tree is defined as a binary tree in which
    the depth of the two subtrees of every node never differ by more than 1.
 */

public class BinaryTreeIsBalanced {
    // I. Algorithm, Dynamic Programming
    //  optimized, O(N) time and O(log(N)) space using DP, abort in the middle if failure already

    public boolean isBalancedIn(TreeNode root) {
        return countHeightMax_DP(root) != -1;
    }

    public static int countHeightMax_DP(TreeNode root) {
        if (root == null) return 0;

        int l = countHeightMax_DP(root.left);
        if (l == -1) return -1;

        int r = countHeightMax_DP(root.right);
        if (r == -1) return -1;

        if (Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
    }

    // II. Algorithm: divide and conquer w/o DP, less efficient
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int l, r;
        l = BinaryTreeCommon.countHeightMax(root.left);
        r = BinaryTreeCommon.countHeightMax(root.right);

        if (Math.abs(l - r) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }


}
