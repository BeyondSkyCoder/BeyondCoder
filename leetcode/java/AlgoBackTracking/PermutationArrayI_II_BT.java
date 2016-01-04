package AlgoBackTracking;

import java.util.*;

public class PermutationArrayI_II_BT {
    /*
        I. Given a collection of numbers, return all possible permutations.

        For example,
        [1,2,3] have the following permutations:
        [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
     */
    // Algorithm: Backtracking with ordering (position matters) with full set without dup
    //      full array scan in each recursion(not pos->) + used[] flag
    //      no_dup means no need to shellSort the original list

    public List<List<Integer>> permuteNoDupI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        List<Integer> levelList = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        permuteNoDupHelperI(nums, res, levelList, used);

        return res;
    }

    public void permuteNoDupHelperI(int[] num, List<List<Integer>> res, List<Integer> levelList, boolean[] used) {

        if (levelList.size() == num.length) {   // full set, only output here, no partial set output
            res.add(new ArrayList<>(levelList));
            return;
        }

        // full array scan because order matters
        for (int i = 0; i < num.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            levelList.add(num[i]);
            permuteNoDupHelperI(num, res, levelList, used);
            used[i] = false;
            levelList.remove(levelList.size() - 1);
        }
    }

    /*
        II, follow problem I, the number can has dup inside
     */
    // Algorithm: Backtracking with ordering (position matters) with full set with dup
    //      full array scan in each recursion(not pos->) + used[pos] flag
    //      require shellSort because of dup
    //      when a number has the same value with its previous, we can use this number only if his previous is used

    public List<List<Integer>> permuteDupII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        List<Integer> levelList = new ArrayList<>();
        Arrays.sort(nums);    // must shellSort first as we only check dup for neighbors

        boolean[] used = new boolean[nums.length];
        permuteDupIIHelper(nums, res, levelList, used);

        return res;
    }

    public void permuteDupIIHelper(int[] num, List<List<Integer>> res, List<Integer> levelList, boolean[] used) {

        if (levelList.size() == num.length) {
            res.add(new ArrayList<>(levelList));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (used[i])
                continue;

            // ONLY different with problem I. skip dup
            //  Funny thing is that either used[i-1] or used[i-1] can work
            //          due to the i>0 check, all permutation will be reached
            //          but the condition can't be omitted
            if (i > 0 && num[i] == num[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            levelList.add(num[i]);
            permuteDupIIHelper(num, res, levelList, used);

            used[i] = false;
            levelList.remove(levelList.size() - 1);
        }
    }

    /*
     * III. Next Permutation

        Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
        The replacement must be in-place, do not allocate extra memory.
        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
        1,2,3 → 1,3,2
        3,2,1 → 1,2,3   : wrapover, rearrange to lowest
        1,1,5 → 1,5,1
     */
    // Algorithm: Array Math, not BT or DFS
    //  O(2N) time, O(1) space
    //  scan from right to left, find the first id breaking the descending order, reverse id-end_of_array to be ascending
    //  then from left to right, find the first number bigger than id, swap it
    //  E.g.: 6 8 7 4 3 2 ->(reverse from 8 to 2) 6 2 3 4 7 8 ->(swap 6 and 7) 7 2 3 4 6 8
    public void nextPermutation(int[] num) {
        int r = num.length - 1;

        while (r > 0 && num[r - 1] >= num[r]) { r--; }

        HelperArrayReverse(num, r, num.length - 1);
        // swapArrayElement num[r - 1] and the first larger element on its right side
        if (r > 0) {
            int next = r;
            int left = r - 1;

            while (num[left] >= num[next]) next++;

            swapArrayElement(num, left, next);
        }
    }

    private void HelperArrayReverse(int[] num, int start, int end) {
        while (start < end) {
            swapArrayElement(num, start++, end--);
        }
    }

    private void swapArrayElement(int[] num, int a, int b) {
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

    /*
     * IV. The set [1,2,3,…,n] contains a total of n! unique permutations.

        By listing and labeling all of the permutations in order, we get the following sequence (ie, for n = 3):
            "123"
            "132"
            "213"
            "231"
            "312"
            "321"
        Given n and k, return the kth permutation sequence.
        Note: Given n will be between 1 and 9 inclusive.
	 */

    // Algorithm1: BT + DFS -> TLE !!
    public String permutationSequence1_BT(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();

        if (k < 1) return null;
        assert (n >= 1 && n <= 9);

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;


        boolean[] used = new boolean[n];

        permutationSequence1BTHelper(nums, res, levelList, used, k);

        return res.get(k).toString();
    }

    public int permutationSequence1BTHelper(int[] num, List<List<Integer>> res, List<Integer> levelList, boolean[] used, int k) {

        if (levelList.size() == num.length) {
            res.add(new ArrayList<>(levelList));
            return 0;
        }

        for (int i = 0; i < num.length; i++) {
            if (used[i])
                continue;

            used[i] = true;
            levelList.add(num[i]);
            int ret = permutationSequence1BTHelper(num, res, levelList, used, k);
            if (ret == -1) {
                return -1;
            }

            if (res.size() == k) {
                return -1;    // kth counted, we are done
            }

            used[i] = false;
            levelList.remove(levelList.size() - 1);
        }

        return 0;
    }


    /* 
     * Algorithm2, Math
     *  Time ~ O(N^2), Space ~ O(N)
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
        Note deleteCharAt() takes linear time
     */
    public static String permutationSequence2_Math(int n, int k) {

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
            int index = k / factorial[i - 1];    // num[] starts with '1', then '2'
            str.append(num.charAt(index));

            System.out.println("i is " + i + " index is " + index + " str is " + str);

            num.deleteCharAt(index);
            k = k % factorial[i - 1];
        }
        return str.toString();
    }


    public static void main(String[] args) {
        PermutationArrayI_II_BT outer = new PermutationArrayI_II_BT();

        int[] num = new int[]{1, 2, 3, 4};
        List<List<Integer>> rr = new ArrayList<>();

        // String str = getPermutationMath(5, 2);

        rr = outer.permuteNoDupI(num);
        System.out.println(rr);
        rr = outer.permuteDupII(num);
        System.out.println(rr);
    }
}

