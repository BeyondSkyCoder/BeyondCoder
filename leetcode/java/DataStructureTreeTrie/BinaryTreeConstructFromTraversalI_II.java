package DataStructureTreeTrie;

public class BinaryTreeConstructFromTraversalI_II {

    /*
      Construct Binary Tree from Array:

        Special binary tree
        A. If given Binary Tree is Complete Tree?
            A Binary Tree is complete if all levels are completely filled except possibly the last level and
            all nodes of last level are as left as possible (Binary Heaps are complete Binary Tree).
            For a complete Binary Tree, level order traversal is sufficient to store the tree.
            We know that the first node is root, next two nodes are nodes of next level,
            next four nodes are nodes of 2nd level and so on.

        B. If given Binary Tree is Full Tree?
            A full Binary is a Binary Tree where every node has either 0 or 2 children.
            It is easy to serialize such trees as every internal node has 2 children. We can simply
            store preorder traversal and store a bit with every node to indicate whether the node is an
            internal node or a leaf node.

        General Binary Tree

        Postorder: { left tree - right tree - root }
                the end of postorder is always the new root !
        Inorder: { left tree - root - right tree }
                the root is at the middle
        Preorder: { root - left tree - right tree }
                the root is at the beginning
     */

    // Algorithm: find the root, divide the array by half, recursion.
    //  Note, for BST, it's simpler, refer to BSTConstructFromArray

    /*
     * I. Given inorder and postorder traversal of a tree, construct the binary tree.
	 */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (is > ie || ps > pe) return null;

        // root of preorder is at the end
        TreeNode root = new TreeNode(postorder[pe]);

        for (int i = is; i <= ie; i++) {
            // root of inorder is at the middle
            if (inorder[i] == root.val) {

                int len = i - 1 - is;
                // divide array into two parts, skip current root
                TreeNode l = buildTreeHelper(inorder, is, i - 1, postorder, ps, ps + len);
                len = ie - i - 1;
                TreeNode r = buildTreeHelper(inorder, i + 1, ie, postorder, pe - 1 - len, pe - 1);
                root.left = l;
                root.right = r;
            }
        }
        return root;
    }

    /*
     * II. Given preorder and inorder traversal of a tree, construct the binary tree.
        Note:
        You may assume that duplicates do not exist in the tree.
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return buildTreeHelper2(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

    public static TreeNode buildTreeHelper2(int[] inorder, int is, int ie, int[] preorder, int ps, int pe) {
        if (is > ie || ps > pe) return null;

        // root of preorder is at the beginning
        TreeNode root = new TreeNode(preorder[ps]);

        for (int i = is; i <= ie; i++) {
            // root of inorder is at the middle
            if (inorder[i] == root.val) {
                int len = i - 1 - is;
                // divide array into two parts, skip current root
                TreeNode l = buildTreeHelper2(inorder, is, i - 1, preorder, ps + 1, ps + 1 + len);
                len = ie - i - 1;
                TreeNode r = buildTreeHelper2(inorder, i + 1, ie, preorder, pe - len, pe);
                root.left = l;
                root.right = r;
            }
        }
        return root;
    }
}
