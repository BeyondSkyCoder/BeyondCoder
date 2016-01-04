package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class ContainDuplicateI_II_III_HM {
    /*
     * Given an array of integers, find if the array contains any duplicates. Your function should return true
     * if any value appears at least twice in the array, and it should return false if every element is distinct.
     */
    // Algorithm: HashMap[array_element] -> count

    public boolean containsDuplicateI_HM(int[] nums) {
        Map<Integer, Integer> hm = new HashMap<>();

        for (int num : nums) {
            if (hm.containsKey(num)) return true;
            else hm.put(num, 1);
        }
        return false;
    }

    /*
     * Given an array of integers and an integer k, find out whether there are two distinct indices i and j 
     * in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
     */
    // Algorithm: HashMap[array_element] => array_index

    public boolean containsNearbyDuplicate_HM(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i])) {
                if (i - hm.get(nums[i]) <= k) {
                    return true;
                } else {
                    // update the new index with bigger one as early smaller one is no longer needed
                    hm.put(nums[i], i);
                }
            } else {
                hm.put(nums[i], i);
            }
        }
        return false;
    }

    /*
	 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that
	 * the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
	 */

    // Algorithm: HashMap[array_element] -> count

    // use the special TreeSet for quick search
    //   it can do O(logN) for ts.floor(), ts.ceiling() search

    public boolean containsNearbyAlmostDuplicate_treeset(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;

        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int c = nums[i];
            if ((ts.floor(c) != null && ts.floor(c) >= c - t) ||
                    (ts.ceiling(c) != null && ts.ceiling(c) <= c + t))
                return true;
            else {
                // add new element to the TreeSet<>, it sorts automatically
                ts.add(c);
                if (i >= k) {
                    ts.remove(nums[i - k]);
                }
            }
        }

        return false;
    }
}
