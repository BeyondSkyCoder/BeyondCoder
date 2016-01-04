package DataStructureTreeTrie;

public class BinaryTreeCountUnivalueSubtree {
    /*
        Given a binary tree, count the number of uni-value subtrees.
        A Uni-value subtree means all nodes of the subtree have the same value.

        For example:
                      5
                     / \
                    1   5
                   / \   \
                  5   5   5
        return 4.
     */

    public int countUnivalSubtrees(DataStructureTreeTrie.TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = new int[1];   // to pass value back from function

        univalueSubTreeHelper(root, count, root.val);
        return count[0];
    }

    public boolean univalueSubTreeHelper(DataStructureTreeTrie.TreeNode root, int[] count, int parent_val) {
        if (root == null) {
            return true;
        }

        // divide
        boolean l = univalueSubTreeHelper(root.left, count, root.val);
        boolean r = univalueSubTreeHelper(root.right, count, root.val);

        // conquer
        if (l && r) {   // if left and right are both tree, add global count
            count[0]++;
        }
        // only return true if this node value equals to parent
        return l && r && (root.val == parent_val);
    }

}
