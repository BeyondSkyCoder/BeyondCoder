package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.cnblogs.com/springfor/p/3884009.html
 * 当数组为 1，2，3，4，.. i，.. n时，基于以下原则的BST建树具有唯一性：
		以i为根节点的树，其左子树由[1, i-1]构成， 其右子树由[i+1, n]构成。 
 */
public class UniqueBinaryTreeI_II {
    public List<TreeNode> generateTrees(int n) {
    	List<TreeNode> res = new ArrayList<>();
    	ArrayList<TreeNode> tmp = getTrees(res, 1, n);
    	res.addAll(tmp);
        return res;
    }
    
    public ArrayList<TreeNode> getTrees(List<TreeNode> res, int min, int max){
        ArrayList<TreeNode> result = new ArrayList<>();
        if (min > max){
            result.add(null);
            return result;
        }
        
        for (int root = min; root <= max; root++){
        	
            ArrayList<TreeNode> lefts = getTrees(res, min, root - 1);
            ArrayList<TreeNode> rights = getTrees(res, root + 1, max);
            
            // combinatorics of lefts and rights
            for (int i = 0; i < lefts.size(); i++ ){
                for (int j = 0; j < rights.size(); j ++){
                    TreeNode rootNode = new TreeNode(root);
                    rootNode.right = rights.get(j);
                    rootNode.left = lefts.get(i);
                    result.add(rootNode);
                }
            }
        }
        return result;
    }	
	
	// DP
    public int numTreesI_DP(int n) {
        if (n<0) return 0;
        else if (n<=1) return 1;

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i=2; i<=n; i++) {
            dp[i] = 0;
            for (int j=0; j<i; j++) {
                dp[i] += dp[j] * dp[i-1-j];
            }
        }
        
        return dp[n];
    }
    
	public int numTreesIRecursion(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        // for any root i, the left 1,i-1 subtree and right i+1, n tree can generate all 
		// unique combinations
        
        if (n<=1) return 1;
        
        int maxsum = 0;
        for (int i = 1; i <= n; i++) {
            maxsum += numTreesIRecursion(i-1) * numTreesIRecursion(n-i);
        }
        return maxsum;
    }
}
