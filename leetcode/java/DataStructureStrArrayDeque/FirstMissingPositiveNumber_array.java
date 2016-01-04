package DataStructureStrArrayDeque;

public class FirstMissingPositiveNumber_array {
    /**
     * Given an unsorted integer array, find the first missing positive integer.
     * For example,
     * Given [1,2,0] return 3,
     * and [3,4,-1,1] return 2.
     * Your algorithm should run in O(n) time and uses constant space.
     */

    // Algorithm, two scans
    //  1st scan, move the number to its right position
    //      offset, note array start from 1 at offset 0 as 0 is not a positive number
    //  2nd scan, find the first mismatch
    //    after step 1, negative and out-of-bound numbers are untouched, but all valid numbers
    //    are in right position. So step 2 scan can return on first unpositioned value

    public int firstMissingPositive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 1;
        }

        int len = nums.length;

        int i = 0;
        while (i < len) {
            int val = nums[i];

            // if num[i] is valid positive integer, swap it to right place
            if (val > 0 && (val - 1 < nums.length) && (nums[val - 1] != val)) {
                int temp = nums[val - 1];
                nums[val - 1] = val;
                nums[i] = temp;
                // don't increment the index, recheck the swapped in number
            } else {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return nums.length + 1;
    }
}
