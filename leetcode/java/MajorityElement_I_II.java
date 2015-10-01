package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class MajorityElement_I_II {
    /*
    Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
     */

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];

        Map<Integer, Integer> hm = new HashMap<>();

        for (Integer i : nums) {
            if (!hm.containsKey(i)) {
                hm.put(i, 1);
                if (nums.length == 2) {
                    return i;
                }
            } else {
                hm.put(i, hm.get(i) +1);

                if (hm.get(i) >= (nums.length+1) / 2) {
                    return i;
                }
            }
        }
        return -1;
    }

    /*
    Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
    The algorithm should run in linear time and in O(1) space.
     */
    public List<Integer> majorityElementII(int[] nums) {
        return null;
    }
}
