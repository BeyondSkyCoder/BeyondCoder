package DataStructureTreeTrie;

public class BSTBinaryTreeLowestCommonAncestor {

    /**
     * BST LCA
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
     * v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
     *
     * _______6______
     * /              \
     * ___2__          ___8__
     * /      \        /      \
     * 0      _4       7       9
     * /  \
     * 3   5
     * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
     * since a node can be a descendant of itself according to the LCA definition.
     */

    // A. recursion, Optimized solution
    public TreeNode LCA_BST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if ((p.val - root.val) * (q.val - root.val) <= 0) {
            // if parent and q is on the both sides of root or one is root, return root
            return root;
        } else if (p.val < root.val) {
            return LCA_BST(root.left, p, q);
        } else {
            return LCA_BST(root.right, p, q);
        }
    }

    // B. non recursion, easy by leveraging the BST propertity
    public TreeNode findLowestCommonAncestorInBST(TreeNode root, TreeNode p, TreeNode q) {
        int val1 = p.val, val2 = q.val;

        while (root != null) {
            int v = root.val;

            if (v > val1 && v > val2) {
                root = root.left;
            } else if (v < val1 && v < val2) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }

    /* BT LCA

        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between
        two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

                _______3______
               /              \
            ___5__          ___1__
           /      \        /      \
           6      _2       0       8
                 /  \
                 7   4
        For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
     */

    // Algorithm: DFS
    //  It's more complicated than BST since BT may have same value nodes, unordered
    //  Note: need to compare with TreeNode directly(not its value)

    public TreeNode LCA_BT(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;

        TreeNode left = LCA_BT(root.left, p, q);
        TreeNode right = LCA_BT(root.right, p, q);

        /* this complex return logic order is crucial
           it will return 1) the right answer if both are found
                          2) the subtree node with one of either parent or q has been found
                          3) null if neither parent or q found
                for 1), since both parent and q are matched, this root, as the correct result, will be guaranteed to
                    pop to the top recusion because all other subtree search will result in null
           The whole logic can be simplified to a rather complex line: return left == null ? right : (right == null ? left : root);
         */
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        BSTBinaryTreeLowestCommonAncestor outer = new BSTBinaryTreeLowestCommonAncestor();
        TreeNode root = new TreeNode(2);
        TreeNode right = new TreeNode(1);
        root.left = null;
        root.right = right;
        System.out.println(outer.LCA_BT(root, root, right).val);
    }
}
