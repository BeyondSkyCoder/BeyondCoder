package leetcode;

public class SearchForARange_array {

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

    private int getLowerBound(int[] A, int target, int from, int to) {
        while (from < to) {
            int mid = (from + to) / 2;
            if (A[mid] < target) {
                from = mid + 1;
            } else {
                to = mid;
            }
        }
        if (from < A.length && A[from] == target) {
            return from;
        } else {
            return -1;
        }
    }

    private int getUpperBound(int[] A, int target, int from, int to) {
        while (from < to) {
            int mid = (from + to) / 2;
            if (A[mid] > target) {
                to = mid;
            } else {
                from = mid + 1;
            }
        }
        return from - 1;
    }
}
