package DataStructureTreeTrie;

import java.util.*;

public class BinaryTreePathMaxPathSumI_II {

	/*
     * Path Sum I
	 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
	 *    adding up all the values along the path equals the given sum.
        For example:
        Given the below binary tree and sum = 22,

                      5
                     / \
                    4   8
                   /   / \
                  11  13  4
                 /  \      \
                7    2      1

        return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	 */
    // Algorithm,
    //   DFS, save all level result to an array, sum and compare

    public boolean hasPathSumRoofToLeaf(TreeNode root, int sum) {
        if (root == null) return false;

        int depth = BinaryTreeCommon.countHeightMax(root);
        int[] res = new int[depth];

        return hasPathSumRoofToLeafHelper(root, 0, res, sum);
    }

    public boolean hasPathSumRoofToLeafHelper(TreeNode root, int level, int[] res, int sum) {
        if (root == null) return false;

        res[level] = root.val;

        // Checkpoint if reaching leaf, calculate the sum and return
        if (root.left == null && root.right == null) {
            int tot = 0;
            for (int i = level; i >= 0; i--) {
                tot += res[i];
            }
            return (tot == sum);
        }

        return hasPathSumRoofToLeafHelper(root.left, level + 1, res, sum)
                || hasPathSumRoofToLeafHelper(root.right, level + 1, res, sum);
    }

    // DFS: using Stack(like preorder traversal
    //		instead of array, then sum up, store the partial sum into another in-sync stack

    public boolean hasPathSumRoofToLeaf_DFS_nR(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<TreeNode> q = new Stack<>();
        Stack<Integer> accSums = new Stack<>();
        q.add(root);
        accSums.add(root.val);

        while (!q.isEmpty()) {
            TreeNode node = q.pop();

            Integer accSum = accSums.pop();

            if (node.left == null && node.right == null && accSum == sum)
                return true;

            if (node.right != null) {
                q.add(node.right);
                accSums.add(accSum + node.right.val);
            }

            if (node.left != null) {
                q.add(node.left);
                accSums.add(accSum + node.left.val);
            }
        }
        return false;
    }

    // BFS: using Queue(like preorder traversal)
    //		instead of array, then sum up, store the partial sum into another in-sync queue

    public boolean hasPathSumRoofToLeaf_BFS_nR(TreeNode root, int sum) {
        if (root == null) return false;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        Queue<Integer> accSums = new LinkedList<>();
        accSums.add(root.val);

        while (!q.isEmpty()) {

            TreeNode node = q.poll();
            Integer accSum = accSums.poll();

            if (node.left == null && node.right == null && accSum == sum)
                return true;
            if (node.left != null) {
                q.add(node.left);
                accSums.add(accSum + node.left.val);
            }
            if (node.right != null) {
                q.add(node.right);
                accSums.add(accSum + node.right.val);
            }
        }
        return false;
    }


    /* 
     * Path Sum II (ALL)
     * 
     *  Given a binary tree and a sum, find
     *   == ALL ==
     *  root-to-leaf paths where each path's sum equals the given sum.
        For example:
        Given the below binary tree and sum = 22,

                      5
                     / \
                    4   8
                   /   / \
                  11  13  4
                 /  \    / \
                7    2  5   1

        return

        [
           [5,4,11,2],
           [5,8,4,5]
        ]

     */
    // Algorithm:
    //  gather the node and print them for the sum path
    //  choose the simple Recursive solution like original pathsum() algorithm
    // NOTE: use dynamic ArrayList to be able to accurate append and remove the last element

    public ArrayList<ArrayList<Integer>> hasPathSumRootToLeafAll(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        ArrayList<Integer> pathSum = new ArrayList<>();
        hasPathSumRootToLeafAllHelper(root, 0, result, pathSum, sum);
        return result;
    }

    public void hasPathSumRootToLeafAllHelper(TreeNode root, int level, ArrayList<ArrayList<Integer>> result,
                                              ArrayList<Integer> PathSum, int sum) {
        if (root == null) return;

        PathSum.add(root.val);
        if (root.left == null && root.right == null) {
            int tot = 0;
            for (int i = level; i >= 0; i--) {
                tot += PathSum.get(i);
            }
            if (tot == sum) {
                result.add(new ArrayList<>(PathSum));
            }
            // backtrack this level before return
            PathSum.remove(level);
            return;
        }

        hasPathSumRootToLeafAllHelper(root.left, level + 1, result, PathSum, sum);
        hasPathSumRootToLeafAllHelper(root.right, level + 1, result, PathSum, sum);

        // backtrack this level before return
        PathSum.remove(level);
    }
    
    /*
     * Path Sum Maximum (ANY path, node value can be any negative/positive value)
     * 			left + root + right,
     * 			left + root
     * 			right + root
     * 			root 
	 *  Given a binary tree, find the maximum path sum. The path may start and end at any node in the tree.
	 *  For example: Given the below binary tree,

               1
              / \
             2   3

        Return 6.
	*/
    // Algorithm: DFS
    //      compare several cases for each DFS due to non-positive values
    //  Note, use one Global max

    private int maxGlobal;    // need this global variable

    public int maxSumAnyPath(TreeNode root) {
        if (root == null) return 0;

        maxGlobal = root.val;
        maxSumAnyPathHelper(root);
        return maxGlobal;
    }

    public int maxSumAnyPathHelper(TreeNode root) {
        if (root == null) return 0;

        int l = maxSumAnyPathHelper(root.left);
        int r = maxSumAnyPathHelper(root.right);

        // since all are Integers, they could be positive or negative. So need to compare all cases

        // case 1, left->cur_root->right path (can't accumulate parents)
        maxGlobal = Math.max(maxGlobal, l + root.val + r);

        // case 2, left->cur_root or right->cur_root or cur_root (can accumulate parents)
        int tmp = Math.max(l + root.val, r + root.val);
        int maxEndCur = Math.max(tmp, root.val);
        maxGlobal = Math.max(maxGlobal, maxEndCur);

        return maxEndCur;
    }

    /*
        Given a binary tree, return all root-to-leaf paths in a chain-> format
        For example, given the following binary tree:
           1
         /   \
        2     3
         \
          5
        All root-to-leaf paths are:

        ["1->2->5", "1->3"]
     */
    public List<String> pathRootToLeafPrint(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        StringBuilder sb = new StringBuilder();

        pathRootToLeafPrintHelper(root, result, sb);
        return result;
    }

    public void pathRootToLeafPrintHelper(TreeNode root, List<String> result, StringBuilder sb) {

        if (root.left == null && root.right == null) {
            sb.append(root.val);
            result.add(sb.toString());
            return;
        }

        sb.append(root.val);
        sb.append("->");

        if (root.left != null) {
            pathRootToLeafPrintHelper(root.left, result, new StringBuilder(sb));
        }
        if (root.right != null) {
            pathRootToLeafPrintHelper(root.right, result, new StringBuilder(sb));
        }
    }

    /*
     * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

        An example is the root-to-leaf path 1->2->3 which represents the number 123.

        Find the total sum of all root-to-leaf numbers.

        For example,

            1
           / \
          2   3

        The root-to-leaf path 1->2 represents the number 12.
        The root-to-leaf path 1->3 represents the number 13.

        Return the sum = 12 + 13 = 25.
    */

    public int sumIn(TreeNode root) {
        if (root == null) return 0;

        return sum(root, 0);
    }

    public int sum(TreeNode root, int prev) {

        int cursum = 10 * prev + root.val;

        if (root.left == null && root.right == null) {
            return cursum;
        } else if (root.left != null && root.right != null) {
            return sum(root.left, cursum) + sum(root.right, cursum);
        } else if (root.left != null) {
            return sum(root.left, cursum);
        } else {
            return sum(root.right, cursum);
        }
    }

}
