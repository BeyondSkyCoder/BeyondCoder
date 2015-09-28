package leetcode;

import java.util.*;

/*
 * Given a set of distinct integers, nums, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

public class SubSetsI_II_backtracking {
	/* 
	 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
	 */
	
    public List<List<Integer>> subsetsII_dup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> word = new ArrayList<>();

        if (nums == null) return res;
        
        int len = nums.length;
        if (len == 0) return null;
                
        boolean[] visited = new boolean[len];
        Arrays.sort(nums);
        
        subsetsHelperII(nums, visited, word, res, -1);
        res.add(new ArrayList<>());
        
        return res;
    }

    public static void subsetsHelperII(int[] num, boolean[] visited, List<Integer> word, List<List<Integer>> res, int limit) {
        
        for (int i = 0; i < num.length; i++) {
        	
        	if (i <= limit) continue;		// non descending order
        	
            if (visited[i])					// can't use set in order to accept dup number
                continue;
            
            visited[i] = true;
            word.add(num[i]);
            
            if (!res.contains(word))
            	res.add(new ArrayList<>(word));		// partial results are good
            
            subsetsHelperII(num, visited, word, res, i);

            // backtracking        
            visited[i] = false;
            word.remove(word.size() - 1);
        }
    }
	
	
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> word = new ArrayList<>();

        if (nums == null) return res;
        
        int len = nums.length;
        if (len == 0) return null;
                
        Set<Integer> visited = new HashSet<>();
        Arrays.sort(nums);
        
        subsetsHelper(nums, visited, word, res, -1);
        res.add(new ArrayList<>());
        
        return res;
    }

    public static void subsetsHelper(int[] num, Set<Integer> visited, List<Integer> word, List<List<Integer>> res, int limit) {
        
        for (int i = 0; i < num.length; i++) {
        	
        	if (i <= limit) continue;		// non descending order
        	
            if (visited.contains(i))
                continue;
            
            visited.add(i);
            word.add(num[i]);
            
            res.add(new ArrayList<>(word));		// partial results are good
            
            subsetsHelper(num, visited, word, res, i);

            // backtracking        
            visited.remove(i);
            word.remove(word.size() - 1);
        }
    }
    
    public static void main(String [] args) {
    	int [] num = {1, 2, 2};
    	SubSetsI_II_backtracking outer = new SubSetsI_II_backtracking();
    	
    	List<List<Integer>> r = outer.subsetsII_dup(num);
    	for (List<Integer> l : r) {
    		System.out.println(l);
    	} 	 
    }
}
