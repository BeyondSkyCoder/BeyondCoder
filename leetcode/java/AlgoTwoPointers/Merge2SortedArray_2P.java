package AlgoTwoPointers;

public class Merge2SortedArray_2P {
    /*
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

    Note:
    You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
    elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

     * Algorithm: start from right end
     */
    public void mergeTwoSortedArray(int[] nums1, int m, int[] nums2, int n) {

        int offset = m + n - 1;

        while (m > 0 && n > 0) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[offset] = nums1[m - 1];
                m--;
                offset--;
            } else {
                nums1[offset] = nums2[n - 1];
                n--;
                offset--;
            }
        }

        // if nums has element left(nums1 is done), copy the remaining
        if (n > 0) {
            while (n > 0) {
                nums1[offset--] = nums2[n - 1];
                n--;
            }
        }
        // no need to handle m > 0, just keep them there
    }

    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int tmp;
        while (left < right) {
            if (nums[left] == 0) {
                for (int j = left + 1; j <= right; j++) {
                    nums[j - 1] = nums[j];
                }
                nums[right] = 0;
                right--;
            } else {
                left++;
            }
        }
    }
}
