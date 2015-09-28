package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// This is critical, illustrating the DFS and BFS algorithms

public class BinaryTreeLevelOrderTraversalI_II {
	/*
	 * Given a binary tree, return the level order traversal of its nodes' values. 
	 * (ie, from left to right, level by level).

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

	===============LEVEL from top==============

	 */


    // I. Level order(BFS) with RECURSION, preorder traversal. using parameter level to track list results.
	
    public List<List<Integer>> levelOrder1(TreeNode root) {
    	List<List<Integer>> res = new ArrayList<>();
        levelOrderRecurvie(root, 0, res);
        return res;
    }
    
    public void levelOrderRecurvie(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        
        List<Integer> levellist;
        if (res.size() == level) { levellist = new ArrayList<>(); res.add(levellist); }
        else { levellist = res.get(level); }
        
        
        levellist.add(root.val);
            
        if (root.left != null) levelOrderRecurvie(root.left, level + 1, res);
        if (root.right != null) levelOrderRecurvie(root.right, level + 1, res);
    }
    
    // II. Level order(BFS) without recursion -- using two Lists
    //		
    //		list itself must contain the Node in order to retrieve in while loop
    //	 	since the list acts like a queue (iterating through)
    //		注意LeetCode要求的返回值是ArrayList<Integer>，不是ArrayList<TreeNode>.
    
    public List<List<Integer>> levelOrderNonRecursion(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        // final conversion is needed. so use ArrayList to store intermedia results for convenience
        ArrayList<ArrayList<TreeNode>> nodelist = new ArrayList<>();
    
        ArrayList<TreeNode> levellist = new ArrayList<>();
        ArrayList<TreeNode> parents;
        levellist.add(root);
        
        while (levellist.size() > 0) {		// core algorithm to tranverse each level
            // save and iterator previous level list
            nodelist.add(levellist);
            
            parents = levellist;
            
            levellist = new ArrayList<>();
            for (TreeNode pa : parents) {
         
                if (pa.left != null) levellist.add(pa.left);
                if (pa.right != null) levellist.add(pa.right);
            }
        } 
        
        // convert TreeNode to final Integer
        for (ArrayList<TreeNode> l : nodelist) {
            List<Integer> level = new ArrayList<>();
            for (TreeNode t : l) {
                level.add(t.val);
            }
            res.add(level);
        }

        /* use java 8 stream
        for (ArrayList<TreeNode> l : nodelist) {
            List<Integer> level = l.stream().map(t -> t.val).collect(Collectors.toList());
            res.add(level);
        }
        */

        return res;
    }
    
    // III. Level order(BFS) without recursion, using one Queue, more flexible
    //	use Node in queue with dummy while using LinkedList<Integer> for results
    // 
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> nq = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        List<Integer> levellist;
        
        nq.add(root);
        nq.add(dummy);  // level boundary
        
        while (nq.size() > 0) {
            levellist = new ArrayList<>();
            
            while (nq.peek() != dummy) {
                TreeNode tmp = nq.poll();
                
                levellist.add(tmp.val);
                
                if (tmp.left != null) nq.offer(tmp.left);
                if (tmp.right != null) nq.offer(tmp.right);            
            }
            
            res.add(levellist);
            
            if (nq.size() == 1 && nq.peek() == dummy)
            	break;	// only dummy left, we are done
            
            nq.offer(nq.poll());     
        }

        return res;
    }
  
    
    /* III. Level order(BFS) without recursion, using one Queue, more flexible -- bottomUP
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
     * (ie, from left to right, level by level from leaf to root).
     */
    
    public ArrayList<ArrayList<Integer>> levelOrderBottomBFS(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        
        // !!! compared with levelOrderTop, this is difference ONE
        Stack<ArrayList<Integer>> sk = new Stack<>();
        
        if (root == null) return result;

        Queue<TreeNode> nodeQ = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        
        nodeQ.add(root);
        nodeQ.add(dummy);
        while(nodeQ.size() > 0){
        	
            ArrayList<Integer> level = new ArrayList<>();
            
            while(nodeQ.peek() != dummy){
                TreeNode temp = nodeQ.poll();
                level.add(temp.val);
                if (temp.left != null) nodeQ.add(temp.left);
                if (temp.right != null) nodeQ.add(temp.right);
            }

            // compared with levelOrderTop, this is difference TWO
            sk.add(level);
            if (nodeQ.size() == 1 && nodeQ.peek() == dummy) break;
            nodeQ.add(nodeQ.poll());	// poll out dummy and add to tail
        }
        
        while (!sk.isEmpty()) {
        	result.add(sk.pop());
        }
        return result;
    }
    
    
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
    
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        
        Queue<TreeNode> nodeq = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        
        nodeq.add(root);
        nodeq.add(dummy);
        int level = 0;
        
        while (nodeq.size() > 0) {
            ArrayList<Integer> levellist = new ArrayList<>();
            Stack<Integer> sk = new Stack<>();
            
            while (nodeq.peek() != dummy) {
                TreeNode tmp = nodeq.poll();
                
                if (level % 2 == 1) {
                    sk.push(tmp.val);
                } else {
                    levellist.add(tmp.val);
                }
                
                if (tmp.left != null)   nodeq.add(tmp.left);
                if (tmp.right != null)  nodeq.add(tmp.right);
            }
            
            if (level % 2 == 1) {
                while (sk.size() > 0) {
                    levellist.add(sk.pop());
                }
            }
                
            results.add(levellist);
            
            if (nodeq.size() == 1 && nodeq.peek() == dummy) break;  // we are done
            
            level++;
            nodeq.add(nodeq.poll());
        }
        
        return results;
    }
    

}
