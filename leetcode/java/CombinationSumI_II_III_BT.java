package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
 *
 *
 * 还是老问题，用DFS找解决方案，不同点是，这道题： The same repeated number may be chosen from C unlimited number of times.

所以，每次跳进递归不用都往后挪一个，还可以利用当前的元素尝试。


 * Backtracking has a few variations
 * 		original data dup, no dup ? use standard n[i]==n[i-1] to skip dup
 * 		resulting must be ordered like non-descending ? -- sort first
 * 		result can be dup, no dup ? use sublist.contains()		
 * 		subsequent recursion can go to previous position ? -- set start/limit position
 * 		track the current used pool ? visited[] flag vs hashset()
 * 
 */

public class CombinationSumI_II_III_BT {
	
	/* 
	 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Ensure that numbers within the set are sorted in ascending order.


Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]
	 */
	
	// pass OJ
    public List<List<Integer>> combinationSum3(int k, int target) {
    	
    	// prepare numbers
    	int len = 9;
    	int [] num = new int[len];
    	for (int i=0; i<len; i++) num[i] = i+1;
    	
    	List<List<Integer>> res = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();

    	if (target < 0) return res;
                
        combinationSum3Helper(num, target, sublist, res, 0, k);
        
        return res;    	
    }	

    public static void combinationSum3Helper(int[] num, int target, List<Integer> sublist, List<List<Integer>> res, int start, int limit) {
    	if (target < 0) {
    		return;
    	}
    		
    	if (sublist.size() == limit) {
    		if (target == 0) {
    			if (!res.contains(sublist))
        			res.add(new ArrayList<>(sublist));
        		return;
    		}
    		return;
    	}
    		
        for (int i = start; i < num.length; i++) {
        	
        	if (target < num[i]) break;
        	
            sublist.add(num[i]);
 
            combinationSum3Helper(num, target - num[i], sublist, res, i + 1, limit);	// DIFFERENCE, pass i + 1
            
            // backtracking        
            sublist.remove(sublist.size() - 1);         
        }
    }

  
    /*
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

     */
    
    // THis is easy, stripped down version of CombineSum3 with no target
    public List<List<Integer>> combine(int n, int k) {
    	
    	// prepare numbers
        int [] num = new int[n];
    	for (int i=0; i< n; i++) num[i] = i+1;
    	
    	List<List<Integer>> res = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();
                
        combinationSumHelper(num, sublist, res, 0, k);
        
        return res;          
    }
    
    public static void combinationSumHelper(int[] num, List<Integer> sublist, List<List<Integer>> res, int start, int limit) {
    		
    	if (sublist.size() == limit) {
    			if (!res.contains(sublist))
        			res.add(new ArrayList<>(sublist));
    			return;
    	}
    		
        for (int i = start; i < num.length; i++) {
        	
            sublist.add(num[i]);
 
            combinationSumHelper(num, sublist, res, i + 1, limit);	// DIFFERENCE, pass i + 1
            
            // backtracking        
            sublist.remove(sublist.size() - 1);         
        }
    }
    
    
    
	/*
	 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.
	 */

    public static void combinationSum2Helper(int[] num, int target, List<Integer> sublist, List<List<Integer>> res, int start) {
        
    	if (target < 0) {
    		return;
    	} else if (target == 0) {
    		if (!res.contains(sublist))
    			res.add(new ArrayList<>(sublist));
    		return;
    	}
    		
        for (int i = start; i < num.length; i++) {
        	
        	if (target < num[i]) break;
        	
            sublist.add(num[i]);
 
            combinationSum2Helper(num, target - num[i], sublist, res, i + 1);	// DIFFERENCE, pass i + 1
            
            // backtracking        
            sublist.remove(sublist.size() - 1);         
        }
    }
	
	
    // pass OJ
    public List<List<Integer>> combinationSumI(int[] candidates, int target) {
    	List<List<Integer>> res = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();

    	if (target < 0) return res;
        if (candidates == null) return res;
        int len = candidates.length;
        if (len == 0) return null;
        
        Arrays.sort(candidates);
        
        // combinationSumHelper(candidates, target, sublist, res, 0);
        combinationSum2Helper(candidates, target, sublist, res, 0);
        
        return res;
    }

    public static void combinationSumHelper(int[] num, int target, List<Integer> sublist, List<List<Integer>> res, int start) {
        
    	if (target < 0) {
    		return;
    	} else if (target == 0) {
        	res.add(new ArrayList<>(sublist));
    		return;
    	}
    		
        for (int i = start; i < num.length; i++) {
        	if (i > 0 && num[i] == num[i-1])	// skip dup
        		continue;
        	
        	if (target < num[i]) break;
        	
            sublist.add(num[i]);
 
            combinationSumHelper(num, target - num[i], sublist, res, i);	// can't pass i+1 because the same number can be used unlimited times

            // backtracking        
            sublist.remove(sublist.size() - 1);         
        }
    }
    
    public static void main(String[] args) {
    	CombinationSumI_II_III_BT outer = new CombinationSumI_II_III_BT();
    	int[] candidate = { 2 };
    	List<List<Integer>> res = outer.combinationSumI(candidate, 1);
        res.forEach(System.out::println);
     }
	
}
