package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PathSumI_II_Max_Tree {

	/*
	 * Path Sum I
	 *  Given a binary tree and a sum, determine 
	 *  if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
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
	
    public boolean hasPathSum(TreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) return false;
        
        int depth = TreeCommonUtils.countHeight(root);
        int[] res = new int[depth];
        for (int i = 0; i < depth; i++) { res[i] = 0; }
        
        return hasPathSumHelper(root, 0, res, sum);
    }
    
    public boolean hasPathSumHelper(TreeNode root, int level, int[] res, int sum) {
        int tot = 0;
        
        if (root == null) return false;
               
        res[level] = root.val;
        
        if (root.left == null && root.right == null) {
        	// reach leaf, calculate the sum and return
            for (int i = level; i >= 0; i--) {
                tot += res[i];
            }
            return (tot == sum);
        }
        
        return hasPathSumHelper(root.left, level + 1, res, sum)
                 || hasPathSumHelper(root.right, level + 1, res, sum);
    }
     
    // OTHERS, BFS: using Queue(like preorder traversal)
    //		instead of array, then sum up, store the partial sum into another in-sync queue

    public boolean hasPathSumBFSNonRecursive(TreeNode root, int sum) {
        if(root==null) return false;
        
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<Integer> accSums = new LinkedList<>();
        nodes.add(root);
        accSums.add(root.val);
        
        while(!nodes.isEmpty()){
        	
            TreeNode node = nodes.poll();
            Integer accSum = accSums.poll();
            
            if(node.left==null && node.right==null && accSum==sum)
                return true;
            if (node.left!=null){
                nodes.add(node.left);
                accSums.add(accSum + node.left.val);
            }
            if (node.right!=null){
                nodes.add(node.right);
                accSums.add(accSum + node.right.val);
            }
        }
        return false;
    }
   


    // OTHER ACCEPTED, DFS: using Stack(like preorder traversal
    //		instead of array, then sum up, store the partial sum into another in-sync stack
    
    public boolean hasPathSumDFSnonRecursive(TreeNode root, int sum) {
        if(root==null) return false;
        
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> accSums = new Stack<>();
        nodes.add(root);
        accSums.add(root.val);
        
        while(!nodes.isEmpty()){            
            TreeNode node = nodes.pop();
            
            Integer accSum = accSums.pop();
            
            if (node.left==null && node.right==null && accSum==sum)
                return true;
            
            if (node.right!=null){
                nodes.add(node.right);
                accSums.add(accSum + node.right.val);
            }
            
            if (node.left!=null){
                nodes.add(node.left);
                accSums.add(accSum+node.left.val);
            }	           
        }
        return false;
    }

    /* 
     * Path Sum II
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
    // path sum II, gather the node and print them for the sum path
    //  need to use ArrayList.
    //  choose the simple Recursive solution like original pathsum() algorithm
    
    public ArrayList<ArrayList<Integer>> hasPathSum2(TreeNode root, int sum) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        ArrayList<Integer> chainl = new ArrayList<>();
      
        if (root == null) return results;
	      
        hasPathSumHelper2(root, 0, results, chainl, sum);
        return results;
    }
    
    public void hasPathSumHelper2(TreeNode root, int level, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> chain, int sum) {
        int tot = 0;
        
    	if (root == null) return;
        
        chain.add(root.val);
        if (root.left == null && root.right == null) {
            for (int i = level; i >= 0; i--) {
                tot += chain.get(i);
            }
            if (tot == sum) {
                res.add(new ArrayList<>(chain));
            }
            chain.remove(level);
            return;
        }
        
        hasPathSumHelper2(root.left, level + 1, res, chain, sum);
        hasPathSumHelper2(root.right, level + 1, res, chain, sum);
        
        // this node is done, next will move to parent level, need to remove current level value
        chain.remove(level);
        return;
    }
    
    /*
     * Path Sum Maximum (ANY path, any negative, positive value)
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
    
    private int maxpath;
    
    public int maxPathSum(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) return 0;
        
        maxpath = root.val;
        maxPathSumHelper(root);
        return maxpath;
    }
    
    public int maxPathSumHelper(TreeNode root) {
        if (root == null) return 0;
        
        int maxleft = maxPathSumHelper(root.left);
        int maxright = maxPathSumHelper(root.right);
        
        // since all are Integers, they could be positive or negative. So need to compare all cases
        
        // case 1, left->cur->right path (can't accumulate parents)
        maxpath = Math.max(maxpath, maxleft+root.val+maxright);
        // case 2, left->cur or right->cur or cur (can accumulate parents)
        int tmp = Math.max(maxleft+root.val, maxright+root.val);
        int maxEndCur = Math.max(tmp, root.val);
        maxpath = Math.max(maxpath, maxEndCur);
        return maxEndCur;
    }		    
}
