package leetcode;



public class LowestCommonAncestorOfBST_BT {

    /**
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes
     v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     _______6______
     /              \
     ___2__          ___8__
     /      \        /      \
     0      _4       7       9
     /  \
     3   5
     For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
     since a node can be a descendant of itself according to the LCA definition.
     */

    // Optimized
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if ((p.val - root.val) * (q.val - root.val) <= 0) { // p and q is on the both sides of root or one is root, return
            return root;
        } else if (p.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    // Simple
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode res;

        if (p.val > q.val) {
            res = lowestCommonAncestorHelper(root, q, p);
        } else {
            res = lowestCommonAncestorHelper(root, p, q);
        }

        return res;
    }

    public TreeNode lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {

        if (q.val == root.val) {
            return root;
        } else if (q.val < root.val) {
            if (root.left != null)
                return lowestCommonAncestorHelper(root.left, p, q);
        } else { // q.val > root
            if (p.val <= root.val) {
                return root;
            } else {
                if (root.right != null)
                    return lowestCommonAncestorHelper(root.right, p, q);
            }
        }
        return null;
    }

    /*
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

    // Algorithm: DFS,

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {

        // BT may have same value nodes, unordered. need to compare with TreeNode, not value
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestorII(root.left, p, q);
        TreeNode right = lowestCommonAncestorII(root.right, p, q);

        /* this complex return logic is crucial
           it will return 1) the right answer if both are found
                          2) the subtree node with one of p and q found
                          3) null if neither p or q found
           for 1), since both p and q are matched, this root will be poping to the end when recusion unwinds
                    because all other subtree search will result in null
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
        // can be simpified to a complex line: return left == null ? right : (right == null ? left : root);
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfBST_BT outer = new LowestCommonAncestorOfBST_BT();
        TreeNode root = new TreeNode(2);
        TreeNode right = new TreeNode(1);
        root.left = null;
        root.right = right;
        System.out.println(outer.lowestCommonAncestorII(root, root, right).val);
    }
}
