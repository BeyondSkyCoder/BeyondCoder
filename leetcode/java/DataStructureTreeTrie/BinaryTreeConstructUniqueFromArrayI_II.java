package DataStructureTreeTrie;

import java.util.ArrayList;
import java.util.List;

/*
 * Math: http://www.cnblogs.com/springfor/p/3884009.html
 * when array 1，2，3，4，.. i，.. n，construct unique BST with
		use i as root，left is constructed from [1, i-1] and right is constructed from [i+1, n]
 */
public class BinaryTreeConstructUniqueFromArrayI_II {

    // number of uniquer BT

    // I. recursion
    public int numTrees_Recursion(int n) {
        // for any root i, the left 1,i-1 subtree and right i+1, n tree can generate all
        // unique combinations

        if (n <= 1) return 1;

        int maxsum = 0;
        for (int i = 1; i <= n; i++) {
            maxsum += numTrees_Recursion(i - 1) * numTrees_Recursion(n - i);
        }
        return maxsum;
    }

    // II. DP to speed up
    public int numTreeUnique_DP(int n) {
        if (n < 0) return 0;
        else if (n <= 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                // combinatorics of left * right
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }

    // generate those unique BT
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();

        ArrayList<TreeNode> tmp = getTrees(1, n);
        result.addAll(tmp);
        return result;
    }

    public ArrayList<TreeNode> getTrees(int min, int max) {
        ArrayList<TreeNode> result = new ArrayList<>();

        if (min > max) {
            result.add(null);
            return result;
        }

        for (int pivot = min; pivot <= max; pivot++) {

            ArrayList<TreeNode> l = getTrees(min, pivot - 1);
            ArrayList<TreeNode> r = getTrees(pivot + 1, max);

            // build combinatorics of lefts and rights
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < r.size(); j++) {
                    TreeNode rootNode = new TreeNode(pivot);
                    rootNode.right = r.get(j);
                    rootNode.left = l.get(i);
                    result.add(rootNode);
                }
            }
        }
        return result;
    }
}
