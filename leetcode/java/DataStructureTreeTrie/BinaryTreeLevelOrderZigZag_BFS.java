package DataStructureTreeTrie;

import java.util.*;

public class BinaryTreeLevelOrderZigZag_BFS {
    /* ZIGZAG level traversal
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

        For example:
        Given binary tree {3,9,20,#,#,15,7},

            3
           / \
          9  20
            /  \
           15   7

        return its zigzag level order traversal as:

        [
          [3],
          [20,9],
          [15,7]
        ]
     */

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int level = 0;
        while (q.size() > 0) {
            List<Integer> levelList = new ArrayList<>();

            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = q.poll();
                levelList.add(tmp.val);

                if (tmp.left != null) q.add(tmp.left);
                if (tmp.right != null) q.add(tmp.right);
            }

            if (level % 2 == 1) {
                Collections.reverse(levelList);
            }
            level++;

            result.add(levelList);
        }

        return result;
    }
}
