package AlgoBinarysearchSort;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * For example, given nums = [3, 5, 2, 1, 6, 4]
 * one possible answer is [1, 6, 2, 5, 3, 4].
 */
// Algorithm: in-place swap each pair

public class WiggleSort_SORT {

    // O(n) solution
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) swap(nums, i);
            } else if (i != 0 && nums[i - 1] < nums[i])
                swap(nums, i);
        }
    }

    public void swap(int[] nums, int i) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
    }

    // brutal force, shellSort first, then swap each pair
    public void wiggleSort_Slow(int[] nums) {
        // global shellSort, make the next move easier, but it's unnecessary for this problem
        Arrays.sort(nums);

        for (int i = 2; i < nums.length; i += 2) {
            int tmp = nums[i];
            nums[i] = nums[i - 1];
            nums[i - 1] = tmp;
        }
    }
}
