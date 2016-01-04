package AlgoTwoPointers;

public class RotateArray_2P {

	/*
	 * Rotate an array of n elements to the right by k steps.

        For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

        Note:
        Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

        Hint
            Could you do it in-place with O(1) extra space?
            Related problem: Reverse Words in a String II
	 */
    // Algorithm:
    //  rotate segment then rotate whole array
    //  similar to reverseWord
    //  uss helper to do this in-place swap without extra memory

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        if (n == k) return;
        k %= n;

        // rotate
        rotateHelper(nums, 0, n - 1 - k);
        rotateHelper(nums, n - k, n - 1);

        rotateHelper(nums, 0, n - 1);
    }

    public void rotateHelper(int[] ar, int left, int right) {
        int tmp;
        while (left < right) {
            tmp = ar[left];
            ar[left] = ar[right];
            ar[right] = tmp;
            left++;
            right--;
        }
    }
}
