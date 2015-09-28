package leetcode;

public class RotateArray {
	
	/*
	 * Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Related problem: Reverse Words in a String II
	 */
	// use similar method like reverseWord to do this in-place without extra memory
	
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return;
        if (n == k) return;
        k %= n;
        rotateHelper(nums, 0, n-1-k);
        rotateHelper(nums, n-k, n-1);
        rotateHelper(nums, 0, n-1);
    }
    
    public void rotateHelper(int[] ar, int start, int end) {
        int tmp;
        while (start < end) {
            tmp = ar[start];
            ar[start] = ar[end];
            ar[end] = tmp;
            start++;
            end--;
        }
    }
}
