package AlgoBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumI_II_III_BT {
    /*
     * I. Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
        The same repeated number may be chosen from C unlimited number of times.

        Note:
        All numbers (including target) will be positive integers.
        Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
        The solution set must not contain duplicate combinations.
        For example, given candidate set 2,3,6,7 and target 7,
        A solution set is:
        [7]
        [2, 2, 3]
    */

    // Algorithm: BackTracking without order, without full set, with dup
    //      Note, the pos doesn't move forward for each recursion as it can be reused

    public List<List<Integer>> combinationSumI(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target < 0) return res;
        if (candidates == null) return res;
        int len = candidates.length;
        if (len == 0) return null;

        Arrays.sort(candidates);

        List<Integer> levelList = new ArrayList<>();

        combinationSumIHelper(candidates, res, levelList, 0, target);
        // combinationSumIIHelper(candidates, res, levelList, 0, target);

        return res;
    }

    public void combinationSumIHelper(int[] num, List<List<Integer>> res, List<Integer> levelList, int pos, int target_remain) {

        if (target_remain < 0) {
            return;
        } else if (target_remain == 0) {
            if (!res.contains(levelList)) {
                res.add(new ArrayList<>(levelList));
            }
            return;
        }


        for (int i = pos; i < num.length; i++) {
            if (i > 0 && num[i] == num[i - 1])    // optimization, as same number has been reused, can skip the dup
                continue;

            if (target_remain < num[i]) {   // prune, early break as all numbers are positive
                break;
            }

            levelList.add(num[i]);

            // !! Must pass i to allow reuse the same number multiple times
            combinationSumIHelper(num, res, levelList, i, target_remain - num[i]);

            levelList.remove(levelList.size() - 1);
        }
    }

    /*
     * II. Given a collection of candidate numbers (C) and a target number (T),
	 * find all unique combinations in C where the candidate numbers sums to T.
	 * Each number in C may only be used once in the combination.
	 */
    // Algorithm: BackTracking without order, without full set, without dup
    //      Note, the pos can move forward for each recursion as each number can only be used once
    //      Note, can't skip dup as they are "different" position number even the value could be the same i.e. (1, 1)

    public void combinationSumIIHelper(int[] num, List<List<Integer>> res, List<Integer> levelList, int pos, int target_remain) {

        if (target_remain < 0) {
            return;
        } else if (target_remain == 0) {
            if (!res.contains(levelList)) {
                res.add(new ArrayList<>(levelList));
            }
            return;
        }

        // BackTracking main loop
        for (int i = pos; i < num.length; i++) {
            /*
            if (i > 0 && num[i] == num[i - 1])
                continue;
            */

            if (target_remain < num[i]) {   // prune, early break as all numbers are positive
                break;
            }

            levelList.add(num[i]);

            // ONLY DIFFERENCE, move to next value
            combinationSumIIHelper(num, res, levelList, i + 1, target_remain - num[i]);

            levelList.remove(levelList.size() - 1);
        }
    }

	/* 
	 * III. Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9
	 * can be used and each combination should be a unique set of numbers.
	 * Ensure that numbers within the set are sorted in ascending order.
	 * Example 1:
	 *      Input: k = 3, n = 7
	 *      Output: [[1,2,4]]
	 */
    // Algorithm: BackTracking without order, without full set, without dup
    //  Note: need to prepare num array from 0-9
    
    public List<List<Integer>> combinationSumIII(int k, int target) {

        // prepare number array, ONLY DIFFERENCE
        int len = 9;
        int[] num = new int[len];
        for (int i = 0; i < len; i++) num[i] = i + 1;

        List<List<Integer>> res = new ArrayList<>();
        if (target < 0) return res;

        List<Integer> levelList = new ArrayList<>();
        combinationSumIIIHelper(num, res, levelList, 0, k, target);

        return res;
    }

    public static void combinationSumIIIHelper(int[] num, List<List<Integer>> res, List<Integer> levelList,
                                               int pos, int limit, int target_remain) {
        if (target_remain < 0) {
            return;
        }

        if (levelList.size() == limit) {
            if (target_remain == 0) {
                if (!res.contains(levelList))
                    res.add(new ArrayList<>(levelList));
                return;
            }
            return;
        }

        for (int i = pos; i < num.length; i++) {

            if (target_remain < num[i]) break;

            levelList.add(num[i]);

            combinationSumIIIHelper(num, res, levelList, i + 1, limit, target_remain - num[i]);

            levelList.remove(levelList.size() - 1);
        }
    }
  
    /*
     * IV, Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * For example,
     * If n = 4 and k = 2, a solution is:
     * [
          [2,4],
          [3,4],
          [2,3],
          [1,2],
          [1,3],
          [1,4],
        ]
     */

    // Algorithm: stripped down version of CombineSum3 with no target
    public List<List<Integer>> combine(int n, int k) {
        int[] num = new int[n];
        for (int i = 0; i < n; i++) num[i] = i + 1;

        List<List<Integer>> res = new ArrayList<>();

        List<Integer> levelList = new ArrayList<>();
        combinationSumHelper(num, res, levelList, 0, k);

        return res;
    }

    public static void combinationSumHelper(int[] num, List<List<Integer>> res, List<Integer> levelList,
                                               int pos, int limit) {

        if (levelList.size() == limit) {
                if (!res.contains(levelList))
                    res.add(new ArrayList<>(levelList));
                return;
        }

        for (int i = pos; i < num.length; i++) {

            levelList.add(num[i]);

            combinationSumHelper(num, res, levelList, i + 1, limit);

            levelList.remove(levelList.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumI_II_III_BT outer = new CombinationSumI_II_III_BT();
        int[] candidate = {2};
        List<List<Integer>> res = outer.combinationSumI(candidate, 1);
        res.forEach(System.out::println);
    }
}
