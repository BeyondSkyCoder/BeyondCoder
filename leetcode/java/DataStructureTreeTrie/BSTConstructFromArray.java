package DataStructureTreeTrie;

public class BSTConstructFromArray {
    /*
       BST is simpler than general Binary Tree(refer to BinaryTreeConstructFromTraversalI_II)
       we can store it by either storing preorder or postorder traversal. In case of Binary Search Trees,
       only preorder or postorder traversal is sufficient to store structure information.
       The constructed BST will be a "perfect" or "complete" tree
     */
    public TreeNode BSTConstructFromArrayPreOrder(int[] preorder) {
        if (preorder == null) return null;

        int len = preorder.length;

        return helper(preorder, 0, len - 1);
    }

    public TreeNode helper(int[] preorder, int l, int r) {
        if (l > r) return null;

        TreeNode root = new TreeNode(preorder[l]);
        int mid = (l + r) / 2;
        root.left = helper(preorder, l + 1, mid);
        root.right = helper(preorder, mid + 1, r);
        return root;
    }

    public void printTree(TreeNode root) {
        if (root == null) return;

        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String [] args) {
        int[] test = {4, 2, 1, 3, 6, 5, 7, };
        BSTConstructFromArray outer = new BSTConstructFromArray();
        TreeNode res = outer.BSTConstructFromArrayPreOrder(test);
        outer.printTree(res);
    }
}
