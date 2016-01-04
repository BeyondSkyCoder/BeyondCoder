package DataStructureTreeTrie;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeRightSideView_BFS {
    /*
     * Given a binary tree, imagine yourself standing on the right side of it,
     * return the values of the nodes you can see ordered from top to bottom.
     * For example:
     * Given the following binary tree,
           1            <---
         /   \
        2     3         <---
         \     \
          5     4       <---
        You should return [1, 3, 4]
     */
    // Algorithm
    //  BFS tree traversel, collect the right most node for each level

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightview = new ArrayList<>();
        if (root == null) return rightview;

        List<List<Integer>> result = new ArrayList<>();

        Deque<TreeNode> q = new LinkedList<>();
        List<Integer> levellist;

        q.add(root);

        while (q.size() > 0) {
            levellist = new ArrayList<>();

            int size = q.size();
            for (int i = 0; i < size; i++) {

                TreeNode tmp = q.poll();

                levellist.add(tmp.val);

                if (tmp.left != null) q.offer(tmp.left);
                if (tmp.right != null) q.offer(tmp.right);
            }

            result.add(levellist);
        }

        // final conversion for required right view
        for (List<Integer> l : result) {
            rightview.add(l.get(l.size() - 1));
        }

        return rightview;
    }

}
