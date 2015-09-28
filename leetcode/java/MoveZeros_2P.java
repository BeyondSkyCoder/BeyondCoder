package leetcode;

/*
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */

public class MoveZeros_2P {
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        int tmp;
        while (left < right) {
            if (nums[left] == 0) {
                for (int j=left+1; j<=right;j++) {
                    nums[j-1] = nums[j];
                }
                nums[right] = 0;
                right--;
            } else {
                left++;
            }
        }
    }
}
