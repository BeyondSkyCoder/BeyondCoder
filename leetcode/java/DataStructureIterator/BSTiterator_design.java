package DataStructureIterator;

import DataStructureTreeTrie.TreeNode;

import java.util.NoSuchElementException;
import java.util.Stack;

public class BSTiterator_design {
    public static void main (String [] args) {
        TreeNode root = null;
        BSTIteratorInorder o1 = new BSTIteratorInorder(root);
        BSTIteratorPostorder o2 = new BSTIteratorPostorder(root);
    }
}

class BSTIteratorInorder {
    /*
        Implement an iterator over a binary search tree (BST).
        Your iterator will be initialized with the root node of a BST.

        Calling next() will return the next smallest number in the BST.

        Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
        where h is the height of the tree.
     */
    // Algorithm: for next(), use stack to push left and right+left

    private Stack<TreeNode> st;

    // constructor
    public BSTIteratorInorder(TreeNode root) {
        st = new Stack<>();
        TreeNode tmp = root;

        if (root == null) return;

        // push all left child DFS
        while (tmp != null) {
            st.push(tmp);
            tmp = tmp.left;
        }
    }

    public boolean hasNext() {
        return (!st.isEmpty());
    }

    // pop one then
    //   check if right is not null, push it AND continue push all its left child
    public int next() {
        if (!st.isEmpty()) {
            TreeNode res = st.pop();
            TreeNode tmp = res;

            if (tmp.right != null) {
                st.push(tmp.right);
                tmp = tmp.right;
                while (tmp.left != null) {
                    st.push(tmp.left);
                    tmp = tmp.left;
                }
            }
            return res.val;
        } else
            return -1;
    }
}


class BSTIteratorPostorder {
    /*
        Do postOrder iterator for binary tree
     */

    /* Algorithm:
        I. A straightforward way is, to traverse the tree with mirrorred pre-order, i.e. root-right-left,
            and store all of them in a stack. Then next() just need to pop nodes out from the stack.

        By doing that, it requires O(n) extra spaces since pre-process stores all nodes in the tree.

        II. Alternatively, we can do it on the fly somehow.
            Initially, we find the first leaf that is going to be visited first and store all intermediate nodes in a stack;
            Each time we pop out a node from the stack, we check whether it is the left child of the current top of the stack.
                If so, repeat the step above on the right sub-tree of the current top.
            Note, We only need to check whether current is left of top since
                if it is right, we know top will be the next-to-be-popped node and thus no need to do anything.
        space O(h) at the worse case, where h is the height of the tree
     */
    private Stack<TreeNode> st;

    private void findNextLeaf(TreeNode cur) {
        while (cur != null) {
            st.push(cur);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
    }

    // constructor
    public BSTIteratorPostorder(TreeNode root) {
        findNextLeaf(root);
    }

    @Override
    public boolean hasNext() {
        return (!st.isEmpty());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("All nodes have been visited");
        }

        TreeNode tmp = st.pop();
        if (!st.isEmpty()) {
            TreeNode top = st.peek();
            if (tmp == top.left) {
                findNextLeaf(top.right);    // find next leaf in right sub-tree
            }
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}
