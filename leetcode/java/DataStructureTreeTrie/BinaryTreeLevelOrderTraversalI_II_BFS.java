package DataStructureTreeTrie;

import java.util.*;

// This illustrates the standard DFS and BFS algorithms on Tree

public class BinaryTreeLevelOrderTraversalI_II_BFS {
    /*
	 * Given a binary tree, return the level order traversal of its nodes' values. 
	 * (ie, from left to right, level by level).
	 *
        For example:
        Given binary tree {3,9,20,#,#,15,7},

            3
           / \
          9  20
            /  \
           15   7

        return its level order traversal as:

        [
          [3],
          [9,20],
          [15,7]
        ]
	 */

    // I. BFS w/o recursion, using one Queue
    //  result goes to List for each level

    public List<List<Integer>> levelOrder_BFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> levellist;

        queue.add(root);

        while (queue.size() > 0) {
            int size = queue.size();    // get the size, avoid dumy node

            levellist = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();

                levellist.add(tmp.val);

                // push its children for next level
                if (tmp.left != null) queue.offer(tmp.left);
                if (tmp.right != null) queue.offer(tmp.right);
            }

            result.add(levellist);
        }

        return result;
    }

    // II. BFS w/o recursion using list
    //
    //		list itself must contain the Node in order to retrieve in while loop
    //	 	since the list acts like a queue (iterating through)
    //		Note: Leetcode require return List<Integer>，not List<Tree.Tree.TreeNode>.

    public List<List<Integer>> levelOrder_BFS2(TreeNode root) {
        if (root == null) return null;

        List<ArrayList<TreeNode>> resList = new ArrayList<>();
        ArrayList<TreeNode> levellist = new ArrayList<>();

        ArrayList<TreeNode> prevLevel;  // use List in place of Queue
        levellist.add(root);

        while (levellist.size() > 0) {
            // save and iterator previous level list
            resList.add(levellist);

            prevLevel = levellist;

            levellist = new ArrayList<>();

            for (TreeNode pa : prevLevel) {
                if (pa.left != null) levellist.add(pa.left);
                if (pa.right != null) levellist.add(pa.right);
            }
        }

        // convert TreeNode to Integer to meet with requirement of returning interger
        List<List<Integer>> result = new ArrayList<>();
        for (ArrayList<TreeNode> l : resList) {
            List<Integer> levelInteger = new ArrayList<>();
            for (TreeNode t : l) {
                levelInteger.add(t.val);
            }
            result.add(levelInteger);
        }

        return result;
    }


    // III. DFS w/ recursion with level parameter

    public List<List<Integer>> levelOrder_DFSrecursive(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderRecursive(root, 0, res);
        return res;
    }

    public void levelOrderRecursive(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;

        List<Integer> levellist;
        if (res.size() == level) {
            levellist = new ArrayList<>();
            res.add(levellist);
        } else {
            levellist = res.get(level);
        }

        levellist.add(root.val);

        if (root.left != null) levelOrderRecursive(root.left, level + 1, res);
        if (root.right != null) levelOrderRecursive(root.right, level + 1, res);
    }
}
