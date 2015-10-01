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

                result[0] = map.get(target - numbers[i]) + 1;    // add one since index of numbers starts from 1 not 0
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
        while (lo < num.length - 1 && num[lo] == num[lo + 1]) {
            lo++;
        }
        return lo;
    }

    private static int decrement(int[] num, int hi) {
        while (hi > 0 && num[hi] == num[hi - 1]) {
            hi--;
        }
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
                result[0] = lo + 1;
                result[1] = hi + 1;

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

        for (int i = 0; i < num.length - 2; i = increment(num, i)) {

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

    // Algorithm: O(N^2) time, O(1) space
    public static List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> listSet = new ArrayList<>();
        if (num.length < 4) return listSet;

        Arrays.sort(num);

        for (int j = 0; j < num.length - 3; j = increment(num, j)) {
            int d = num[j];

            for (int i = j + 1; i < num.length - 2; i = increment(num, i)) {

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


    /*
    Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
    Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        // cover this special case to avoid TLE
        if (nums.length == 3) return nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int val = 0;

        for (int i = 0; i < nums.length - 2; i = increment(nums, i)) {
            int lo = i + 1, hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];

                // cache the smallest delta and real value
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    val = sum;
                }

                if (sum == target) {
                    return sum; // the question says only one solution, so safe to return here for a match
                } else if (sum < target) {
                    lo = increment(nums, lo);
                } else {
                    hi = decrement(nums, hi);
                }
            }
        }
        return val;
    }

    /*
    Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:

[-2, 0, 1]
[-2, 0, 3]
Follow up:
Could you solve it in O(n2) runtime?
     */
    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;

        if (nums.length == 3) return (nums[0] + nums[1] + nums[2] < target) ? 1 : 0;

        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1, hi = nums.length - 1;

            // same loop as other xSum, except, before moving lo to higher, all between hi-lo are valid results
            while (lo < hi) {
                if (nums[i] + nums[lo] + nums[hi] < target) {
                    res += hi - lo;
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] al = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> results;
        // results = threeSum(al);
        // System.out.println(results);

        int[] a4 = {0, 0, 0, 0};
        /* results = fourSum(a4, 1);
        System.out.println(results);
        */

        int [] input2 = { -2, -1, 1, 2};
        int [] input3 = { -1, -1, 1, -1};

        System.out.println(threeSumSmaller(input3, -1));

    }
}
