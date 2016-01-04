package AlgoBackTracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsArrayI_II_BT {
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
    // Algorithm: Backtracking without ordering. i.e. (1 2 3) is the same as (1 3 2) without full set
    //      MUST SORT first !!
    //      next dfs must use (pos + 1)
    //      one-dimensional move forward(not rescan) because ordering is not different here

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        List<Integer> levelList = new ArrayList<>();
        Arrays.sort(nums);

        subsetsHelperI(nums, result, levelList, 0);
        return result;
    }

    public static void subsetsHelperI(int[] num, List<List<Integer>> res, List<Integer> levelList, int pos) {
        res.add(new ArrayList<>(levelList));

        for (int i = pos; i < num.length; i++) {

            levelList.add(num[i]);

            subsetsHelperI(num, res, levelList, i + 1);

            levelList.remove(levelList.size() - 1);
        }
    }

    /*
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
	 */

    // Algorithm: trick is how to skip dup

    public List<List<Integer>> subsetsII_dup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        List<Integer> levelList = new ArrayList<>();
        Arrays.sort(nums);

        subsetsHelperII(nums, res, levelList, 0);
        return res;
    }

    public static void subsetsHelperII(int[] num, List<List<Integer>> res, List<Integer> levelList, int pos) {
        res.add(new ArrayList<>(levelList));

        for (int i = pos; i < num.length; i++) {

            // SKIP DUP:  -- This is the only difference with subsetI()
            //  A. condition 1, always let the first pos goes through, (1, 2) and (1, 2, 2) are different
            //  B. condition 2, skip the remaining duplicates
            if (i > pos && (num[i] == num[i-1])) {
                continue;
            }

            levelList.add(num[i]);

            subsetsHelperII(num, res, levelList, i + 1);

            levelList.remove(levelList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] num1 = {0};
        int[] num2 = {1, 2, 2, 3};

        SubsetsArrayI_II_BT outer = new SubsetsArrayI_II_BT();

        List<List<Integer>> r;

        // r = outer.subsets(num2);

        r = outer.subsetsII_dup(num2);
        for (List<Integer> l : r) {
            System.out.println(l);
        }
    }
}
