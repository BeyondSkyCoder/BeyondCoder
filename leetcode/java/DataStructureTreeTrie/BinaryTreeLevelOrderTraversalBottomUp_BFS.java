package DataStructureTreeTrie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeLevelOrderTraversalBottomUp_BFS {
    /*
     * BFS bottomup using one Queue
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
     * (ie, from left to right, level by level from leaf to root).
     */

    public ArrayList<ArrayList<Integer>> levelOrderBottomBFS(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Stack<ArrayList<Integer>> sk = new Stack<>();   // use stack to do reverse at the end

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (q.size() > 0) {
            ArrayList<Integer> level = new ArrayList<>();

            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = q.poll();
                level.add(temp.val);

                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }

            sk.add(level);
        }

        while (!sk.isEmpty()) {
            result.add(sk.pop());
        }
        return result;
    }
}
