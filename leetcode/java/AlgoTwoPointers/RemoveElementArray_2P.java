package AlgoTwoPointers;

public class RemoveElementArray_2P {

	/*
     * Given an array and a value, remove all instances of that value in place and return the new length.

        The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 */

    // Algorithm: two pointers
    public int removeElement(int[] nums, int elem) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;

        int i = 0;
        int j = 0;
        while (i < len) {
            if (nums[i] != elem) {
                nums[j] = nums[i];
                j++;
            }
            i++;
        }
        return j;
    }
}
