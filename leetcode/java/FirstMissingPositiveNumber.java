package leetcode;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 */


// Algorithm, put the number to right index +1, then rescan the array to find first mismatch

public class FirstMissingPositiveNumber {
    public int firstMissingPositive(int[] A) {

        if (A == null || A.length == 0)
            return 1;

        for (int i = 0; i < A.length; i++) {

            // if A[i] is valid positive integer, put it to right place
            if (A[i] <= A.length && A[i] > 0 && A[A[i] - 1] != A[i]) {
                int temp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
                i--;    // retreat one step to recheck the current position as new number may be swapped in
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1)
                return i + 1;
        }

        return A.length + 1;
    }
}
