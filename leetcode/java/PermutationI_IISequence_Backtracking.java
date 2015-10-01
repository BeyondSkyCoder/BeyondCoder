package leetcode;

import java.util.*;

public class PermutationI_IISequence_Backtracking {
	// Permutation sequence
	/*
	 * The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
	 */
	
	// DFS, TLE !!, see below math answer
    public String getPermutation(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> word = new ArrayList<>();

        if (k < 1) return null;
        
        assert(n>=1 && n<=9);   
        int[] nums = new int[n];
        for (int i=0; i<n; i++) nums[i] = i+1; 
        
        
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {	used[i] = false;	} 
        
        getPermutationHelper(nums, used, word, res, k);
        
        return res.get(k).toString();
    }

    public void getPermutationHelper(int[] num, boolean[] used, List<Integer> word, List<List<Integer>> res, int k) {
    	
    	if (word.size() == num.length) {
            res.add(new ArrayList<>(word));
            return;
        }
        
    	// backtracking, DFS will TLE
        for (int i = 0; i < num.length; i++) {
            if (used[i])
                continue;
            
            used[i] = true;
            word.add(num[i]);
            getPermutationHelper(num, used, word, res, k);
            
            if (res.size() == k)
            	return;	// kth counted, we are done
            			
            used[i] = false;
            word.remove(word.size() - 1);
        }
    }   
    
    
    /* 
     * Time ~ O(N^2), Space ~ O(N) 
No need to find out all permutations! We can use the formula:
[i_0 i_1 ... i_{n - 1}] is the kth permutation,
where k = i_0 * (n - 1)! + i_1 * (n - 2)! + ... + i_{n - 1} * 0!
We only need to determine the coefficients i.
Example:
"123"
4. "231": 4 - 1 = 3 = 1 * 2 + 1 * 1 + 0 * 1
5. "312": 5 - 1 = 4 = 2 * 2 + 0 * 1 + 0 * 1
Note:
start k from base 0: k--;
need to delete the number after adding it to the permutation.
     */
    public static String getPermutationMath_TBD(int n, int k) {
        // Time: O(N^2) deleteCharAt() takes linear time, Space: O(N)
        // k = i0 x (n-1)! + i1 x (n-2)! + ... + i{n-1} x 0! => "i0 i1 ... i{n-1}"
    	
        StringBuilder num = new StringBuilder(); // "12..n"
        
        // precompute and store 0!, 1!,..., n!
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            num.append(i);
            factorial[i] = factorial[i - 1] * i;
        }
                
        k--;    // start from base 0!!
        
        StringBuilder str = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            int index = k / factorial[i - 1];	// num[] starts with '1', then '2'
            str.append(num.charAt(index));	// ??
            
            System.out.println("i is " + i + " index is " + index + " str is " + str);

            num.deleteCharAt(index);
            k = k % factorial[i - 1];
        }
        return str.toString();
    }
    
    /*
     * Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place, do not allocate extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Solution

Time ~ O(2N), Space ~ O(1)
这道题是全排列中的一步，即给定一个排列，求下一个排列是什么。
方法：
从右向左扫，找到右端最长的降序列（记录该序列的开头为 num[curr]，则 num[curr - 1] 为分割数，即在 curr - 1 和 curr 间分割）；
反该转序列成升序；
在反转后的序列中从左至右找第一个大于分割数的元素，并与分割数交换位置。
    
     */
    
    public void nextPermutationTBD(int[] num) {
        // Time: O(N), Space: O(1)
        // E.g.: 6 8 7 4 3 2 -> 7 2 3 4 6 8 (partition number: 6, swap with 7)
        // find longest descending tail and reverse it, num[curr - 1] is the partition number
        int curr = num.length - 1;
        while (curr > 0 && num[curr - 1] >= num[curr])  curr--;
        nextPermutationReverse(num, curr, num.length - 1);
        // swap num[curr - 1] and the first larger element on its right side
        if (curr > 0) {
            int next = curr;
            curr--;
            while (num[curr] >= num[next])  next++;
            swap(num, curr, next);
        }
    }

    private void nextPermutationReverse(int[] num, int start, int end) {
        while (start < end) {
            swap(num, start++, end--);
        }
    }

    private void swap(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
    
	// num[] must be unique
	// passed OJ
	public static List<List<Integer>> permuteI_nodup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> word = new ArrayList<>();

        if (nums == null) return res;
        
        int len = nums.length;
        if (len == 0) return null;
        
        boolean[] used = new boolean[len];
        for (int i = 0; i < len; i++) {	used[i] = false;	} 
        
        // no dup
        permuteHelper(nums, used, word, res);

        return res;
    }
    
    public static void permuteHelper(int[] num, boolean[] used, List<Integer> word, List<List<Integer>> res) {
    	
    	if (word.size() == num.length) {
            res.add(new ArrayList<>(word));
            return;
        }
        
    	// backtracking, DFS will TLE
        for (int i = 0; i < num.length; i++) {
            if (used[i])
                continue;
            
            used[i] = true;
            word.add(num[i]);
            permuteHelper(num, used, word, res);
            used[i] = false;
            word.remove(word.size() - 1);
        }
    }    

    // Passed OJ
	public static List<List<Integer>> permuteII_Dup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> word = new ArrayList<>();

        if (nums == null) return res;
        
        int len = nums.length;
        if (len == 0) return null;
        
        Arrays.sort(nums);	// must sort first as we only check dup for neighbors
        
        Set<Integer> visited = new HashSet<>();
        
        permuteHelperDup(nums, visited, word, res);

        return res;
    }

    public static void permuteHelperDup(int[] num, Set<Integer> visited, List<Integer> word, List<List<Integer>> res) {
    	
    	if (word.size() == num.length) {	  
            // make a copy, add to result array of array
            res.add(new ArrayList<>(word));
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (visited.contains(i))
                continue;
            
            visited.add(i);
            word.add(num[i]);
            permuteHelperDup(num, visited, word, res);

            // backtracking
            
            visited.remove(i);
            word.remove(word.size() - 1);
            
            // !!! ONLY DIFFERENCE, skip same number
            while (i+1 < num.length && num[i+1] == num[i])
            	i++;
        }
    }  

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = new int[] { 1, 2, 3, 4 };
		List<List<Integer>> rr = new ArrayList<>();
		
		// String str = getPermutationMath(5, 2);
		
		rr = permuteI_nodup(num);
		System.out.println(rr);
		rr = permuteII_Dup(num);
		System.out.println(rr);
	}
        
}

