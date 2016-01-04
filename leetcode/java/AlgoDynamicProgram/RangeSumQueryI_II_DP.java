package AlgoDynamicProgram;

import java.util.HashMap;

public class RangeSumQueryI_II_DP {
    /*
    Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

    Example:
    Given nums = [-2, 0, 3, -5, 2, -1]

    sumRange(0, 2) -> 1
    sumRange(2, 5) -> -1
    sumRange(0, 5) -> -3
    Note:
    You may assume that the array does not change.
    There are many calls to sumRange function.
     */

    HashMap<Integer, Integer> hm;
    int len;

    public RangeSumQueryI_II_DP(int[] nums) {
        hm = new HashMap<>();
        len = 0;

        if (nums == null || nums.length == 0) return;

        len = nums.length;
        hm.put(0, nums[0]);

        if (len < 2) return;
        hm.put(1, nums[0] + nums[1]);

        for (int i = 2; i < len; i++) {
            hm.put(i, hm.get(i - 1) + nums[i]);
        }
    }

    public int sumRange(int i, int j) {
        if (i < 0 || j >= len) return 0;
        return hm.get(j) - (i >= 1 ? hm.get(i - 1) : 0);
    }
}
