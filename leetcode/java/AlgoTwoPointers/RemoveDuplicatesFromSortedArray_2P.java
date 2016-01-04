package AlgoTwoPointers;

public class RemoveDuplicatesFromSortedArray_2P {
    /*
        Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

        Do not allocate extra space for another array, you must do this in place with constant memory.

        For example,
        Given input array nums = [1,1,2],

        Your function should return length = 2, with the first two elements of nums being 1 and 2
        respectively. It doesn't matter what you leave beyond the new length.
     */
    public int removeDuplicates(int[] nums) {
        int insert_pos = 0;
        int i = 1;
        int val;

        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return 1;
        }

        val = nums[insert_pos];
        insert_pos++;

        while (i < nums.length) {
            if (nums[i] != val) {
                nums[insert_pos] = nums[i];

                val = nums[insert_pos];
                insert_pos++;
            }
            i++;
        }

        return insert_pos;
    }

    // advanced solution
    public int removeDuplicatesExpert(int[] A) {
        if (A.length == 0) return 0;
        int i = 0, j = 1;
        for (; j < A.length; j++) {
            if (A[j] != A[j - 1]) {
                A[++i] = A[j];
            }
        }
        return ++i;
    }

    /*
     *  Follow up for "Remove Duplicates":
        What if duplicates are allowed at most twice?

        For example,
        Given sorted array A = [1,1,1,2,2,3],

        Your function should return length = 5, and A is now [1,1,2,2,3].
     */

    public int removeDuplicatesAtmostTwice(int[] A) {
        int insert_pos = 0;
        int i;
        int len = A.length;
        boolean duplicate_once = false;

        if (len == 0) {
            return 0;
        } else if (len == 1) {
            return 1;
        }

        int val = A[insert_pos];
        insert_pos++;

        i = 1;
        while (i < len) {
            if (A[i] != val) {
                A[insert_pos++] = A[i];
                val = A[i];
                duplicate_once = false;
            } else {
                if (!duplicate_once) {
                    duplicate_once = true;
                    A[insert_pos++] = A[i];
                }
            }

            i++;
        }

        return insert_pos;
    }

    // advanced solution
    public int removeDuplicatesAtmostTwiceExpert(int[] A) {
        if (A.length == 0) return 0;
        int i = 0;
        int j = 1;
        boolean seen = false;
        for (; j < A.length; j++) {
            if (A[j] != A[j - 1]) {
                A[++i] = A[j];
                seen = false;
            } else if (!seen) {
                A[++i] = A[j];
                seen = true;
            }
        }
        return ++i;
    }
}
