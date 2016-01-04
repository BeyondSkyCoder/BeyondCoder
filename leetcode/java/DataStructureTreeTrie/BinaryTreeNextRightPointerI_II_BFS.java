package DataStructureTreeTrie;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeNextRightPointerI_II_BFS {
    /*
     * Given a binary tree
     * Populate each next pointer to point to its next right node. If there is tot next right node,
     * the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.

        Note:

        You may only use constant extra space.
        You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
        For example,
        Given the following perfect binary tree,
                 1
               /  \
              2    3
             / \  / \
            4  5  6  7
        After calling your function, the tree should look like:
                 1 -> NULL
               /  \
              2 -> 3 -> NULL
             / \  / \
            4->5->6->7 -> NULL
     */
    // Algorithm
    //      BFS
    //      tot need to save list of list result, do the connect for each level on the fly
    //      Note, this uses queue of O(n) space. can optimize that with different algorithm using two pointers

    public void connectI(TreeLinkNode root) {
        if (root == null) return;

        Deque<TreeLinkNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {

            int size = q.size();    // record the size now for this round of iteration
            TreeLinkNode tmp = new TreeLinkNode(0);

            for (int i = 0; i < size; i++) {
                tmp.next = q.poll();    // connect each one from the queue
                tmp = tmp.next;
                if (tmp.left != null) q.offer(tmp.left);
                if (tmp.right != null) q.offer(tmp.right);
            }

        }
    }

    /*
     * Follow up for problem "Populating Next Right Pointers in Each Node".
     * What if the given tree could be any binary tree? Would your previous solution still work?
     * Note:
     *      You may only use constant extra space.
     */

    // Algorithm: BFS with levellist
    public void connectII(TreeLinkNode root) {

        if (root == null) return;

        List<List<TreeLinkNode>> res = new ArrayList<>();

        Deque<TreeLinkNode> q = new LinkedList<>();
        List<TreeLinkNode> levellist;

        q.add(root);

        while (q.size() > 0) {
            levellist = new ArrayList<>();

            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode tmp = q.poll();
                levellist.add(tmp);     // add to level list first
                if (tmp.left != null) q.offer(tmp.left);
                if (tmp.right != null) q.offer(tmp.right);
            }

            res.add(levellist);
        }

        // scan each level list, connect the next pointer, last one connects to null
        for (List<TreeLinkNode> l : res) {
            for (int i = 1; i < l.size(); i++) {
                TreeLinkNode t = l.get(i - 1);
                t.next = l.get(i);
            }
            l.get(l.size() - 1).next = null;
        }
    }
}
