package AlgoBinarysearchSort;

public class SearchForARange_BS {

	/* 
     * Given a sorted array of integers, find the starting and ending position of a given target value.
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * If the target is not found in the array, return [-1, -1].
	 * For example,
		Given [5, 7, 7, 8, 8, 10] and target value 8,
		return [3, 4]. 
	 */

    // Algorithm: two times scan with binary search

    public int[] searchRange(int[] A, int target) {

        int lower = getLowerBound(A, target, 0, A.length);
        if (lower == -1) {
            return new int[]{-1, -1};
        } else {
            int upper = getUpperBound(A, target, 0, A.length);
            return new int[]{lower, upper};
        }
    }

    public int getLowerBound(int[] A, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (A[mid] < target) {
                left = mid + 1;
            } else {    // A[mid] >= target
                right = mid;    // don't go mid-1 due to duplicates
            }
        }
        if (left < A.length && A[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    public int getUpperBound(int[] A, int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (A[mid] > target) {
                right = mid;
            } else {    // A[mid] <= target
                left = mid + 1;
            }
        }
        return left - 1;
    }
}
