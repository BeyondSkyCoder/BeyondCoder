package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSumThreeSumFourSum {

	/* 
	 * Two sum
	 * 
	 *  sort is optional, not much gain. but for Three+ sum, sort should be done first
	 *  	two pointers algorithm is then used
	 *  
	 * from Leetcode book
	 * a. brutal force, O(n^2) runtime
	 * b. hash, O(n) runtime, O(n) space
	 * c. if the array is already sorted
	 * 		binary search, O(nlogn) runtime, O(1) space
	 * 		two pointers from two side search: O(n) runtime, O(1) space -- best for already sorted
	 */
    /* 
     * Given an array of integers, find two numbers such that they add up to a specific target number.
	 *	The function twoSum should return indices of the two numbers such that they add up to the target, 
	 *	where index1 must be less than index2. Please note that your returned answers 
	 *	(both index1 and index2) are not zero-based.
	 *	You may assume that each input would have exactly one solution.
	 *	Input: numbers={2, 7, 11, 15}, target=9
	 *	Output: index1=1, index2=2 
     */
    
    // use HashMap to speed up by using more space: O(n) runtime, O(n) space
    
    public int[] twoSum_unsorted(int[] numbers, int target) {  
        // Start typing your Java solution below  
        // DO NOT write main() function  
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = numbers.length;  
        int[] result = new int[2];
        
        for (int i = 0; i < numbers.length; i++) {  
            if (map.containsKey(target - numbers[i])) {
            	
                result[0] = map.get(target-numbers[i]) + 1;	// add one since index of numbers starts from 1 not 0
                result[1] = i + 1;  
                break;  
            } else {  
                map.put(numbers[i], i);  
            }  
        }  
        return result;      
    } 
	
    
	// if array is sorted, find index is required for answer, save space by not using Hash
	//   O(nlogn)+O(n)+O(n) = O(nlogn)
    
	private static int increment(int[] num, int lo) {
	    while (lo < num.length - 1 && num[lo] == num[lo+1]) {lo++;}
	    return lo;
	}
	    
	private static int decrement(int[] num, int hi) {
	    while (hi > 0 && num[hi] == num[hi-1]) {hi--;}
	    return hi;
	}
    
    public int[] twoSum_Sorted(int[] num, int target) {
        int[] result = new int[2];
	    if (num.length < 2) return result;
		
	    // Arrays.sort(num);
	    	    
	    int lo = 0, hi = num.length - 1;
	        
	        while (lo < hi) {
	            int b = num[lo], c = num[hi];
	            int sum = b + c;
	            if (sum == target) {
                    result[0] = lo+1;
                    result[1] = hi+1;
	                
	                // only one solution
	                break;
	            } else if (sum > target) {
	                hi = decrement(num, hi); // hi--;
	            } else if (sum < target) {
	                lo = increment(num, lo); // lo++;
	            }
	        }
	        
	    return result;
	} 
	    
	/*
	 * Three Sum
	 * 
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * Note:
    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
    The solution set must not contain duplicate triplets.

    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
	 */
	
	// !! unlike twoSum(), threeSum() must sort and can use Binary Search
    
	// 	no need to keep original array and search index since only value is requested

	public static List<List<Integer>> threeSum(int[] num) {
		List<List<Integer>> listSet = new ArrayList<>();
	    if (num.length < 3) return listSet;
	    
	    int target = 0;
		
	    Arrays.sort(num);
	    	    
	    for (int i = 0; i < num.length-2; i = increment(num, i)) {
	    	
	        int a = num[i], lo = i + 1, hi = num.length - 1;
	        
	        while (lo < hi) {
	            int b = num[lo], c = num[hi];
	            if (a + b + c == target) {
	                List<Integer> list = Arrays.asList(a, b, c);
	                
	                // if (!listSet.contains(list))
	                	listSet.add(list);
	                	
	                lo = increment(num, lo); // lo++;
	                hi = decrement(num, hi); // hi--;
	            } else if (a + b + c > target) {
	                hi = decrement(num, hi); // hi--;
	            } else if (a + b + c < target) {
	                lo = increment(num, lo); // lo++;
	            }
	        }
	    }        
	    return listSet;
	}
    
    /* 
     * four sum
     * 
     * 	extend and reuse 3 sum algorithm
     */
    public static List<List<Integer>> fourSum(int[] num, int target) {
		List<List<Integer>> listSet = new ArrayList<>();
	    if (num.length < 4) return listSet;

    	Arrays.sort(num);

    	for (int j = 0; j < num.length - 3; j = increment(num, j)) {
    	    int d = num[j];
    	           	    
    	    for (int i = j+1; i < num.length - 2; i = increment(num, i)) {
	
    	        int a = num[i], lo = i + 1, hi = num.length - 1;    	        
    	        while (lo < hi) {
    	            int b = num[lo], c = num[hi];
    	            int sum = a + b + c + d;
    	            if (sum == target) {
    	                List<Integer> list = Arrays.asList(d, a, b, c);
    	                listSet.add(list); // if (!listSet.contains(list)) listSet.add(list);                     
    	                lo = increment(num, lo); // lo++;
    	                hi = decrement(num, hi); // hi--;
    	            } else if (sum > target) {
    	                hi = decrement(num, hi); // hi--;
    	            } else if (sum < target) {
    	                lo = increment(num, lo); // lo++;
    	            }
    	        }
    	    }        
    	}
    	return listSet;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] al = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> results;
		results = threeSum(al);
		// System.out.println(results);
		
		int [] a4 = {0, 0, 0, 0};
		results = fourSum(a4, 1);
		System.out.println(results);
		
	}    
}
