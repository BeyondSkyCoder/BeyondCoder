package leetcode;

import java.util.Stack;

public class BSTiterator_design {
    Stack<TreeNode> st;

    public BSTiterator_design(TreeNode root) {
        st = new Stack<>();
        TreeNode tmp = root;
        if (root == null) return;
        
        // push all left child DFS
        while (tmp != null) {
            st.push(tmp);
            tmp = tmp.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!st.isEmpty());
    }

    /** @return the next smallest number */
    
    // pop, if right is not null, push it and continue push all its left child
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
