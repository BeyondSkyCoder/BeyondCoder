package DataStructureTreeTrie;

// Binary Tree
//      height, minDepth, maxDepth, sameTree, isSymmetric


import java.util.ArrayList;

public class BinaryTreeCommon {

    // count the max height of a Binary TRee
    public static int countHeightMax(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // need to add one as recursion return 0 for leaf node
        return Math.max(countHeightMax(root.left), countHeightMax(root.right)) + 1;
    }

    /*
     * count the minimum depth
     *  The minimum depth is the number of nodes along the shortest path from the root node
     *  down to the nearest leaf node.
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l, r;
        l = minDepth(root.left);
        r = minDepth(root.right);

        if (l * r != 0) return Math.min(l, r) + 1;
        else if (l == 0) return r + 1;
        else { // (r == 0)
            return l + 1;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l, r;
        l = maxDepth(root.left);
        r = maxDepth(root.right);

        if (l * r != 0) return Math.max(l, r) + 1;
        else if (l == 0) return r + 1;
        else {  // (r == 0)
            return l + 1;
        }
    }

    // tell if two Binary Trees are the same
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

    public boolean isSubTree(TreeNode r1, TreeNode r2) {
        if (r1 == null) return false;

        if (r1.val == r2.val) {
            if (sameTree(r1, r2)) return true;
        }

        return isSubTree(r1.left, r2) || isSubTree(r1.right, r2);
    }

    /*
     * Invert/Mirror a binary tree.

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

    public TreeNode mirrorInvertTree(TreeNode root) {
        return mirrorTree(root);
    }

    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = mirrorTree(root.left);
        root.left = mirrorTree(root.right);
        root.right = temp;
        return root;
    }

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

    // I. Algorithm, use mirrorTree/SameTree utility
    public boolean isSymmetric(DataStructureTreeTrie.TreeNode root) {
        if (root == null) {
            return true;
        }

        return sameTree(root.left, mirrorTree(root.right));
    }


    // II. Algorithm, preOrder travesal to List, then check palindromic
    public boolean isSymmetric2(DataStructureTreeTrie.TreeNode root) {
        ArrayList<Integer> treeToArray = new ArrayList<>();
        if (root == null) {
            return true;
        }

        traversalPreOrderDFS(root, treeToArray);

        int len = treeToArray.size();
        for (int i = 0; i < len / 2; i++) {
            if (!treeToArray.get(i).equals(treeToArray.get(len - 1 - i)))
                return false;
        }
        return true;
    }

    // In-order traversal
    public static void traversalPreOrderDFS(DataStructureTreeTrie.TreeNode root, ArrayList<Integer> res) {
        if (root == null) return;

        traversalPreOrderDFS(root.left, res);
        res.add(root.val);
        traversalPreOrderDFS(root.right, res);
    }
}
