package AlgoBinarysearchSort;

public class SearchInRotatedSortedArray_BS {

    /*
     * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     *
     * A graph is easy to understand
     *
     * left							right
	 * 	   			-
	 * 	  		-
		 	-
		-
									-
								-
							-
						-
                ^
                |
           mid_case_1
            ^
            |
         if target here, move right to mid-1, otherwise, move left to mid+1

	   						^
							| mid_case_2
							       ^
                                   |
                                if target here, move left to mid+1, otherwise, move right to mid-1
     *
	 */
    public int searchInRotatedArray(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int left = 0, right = A.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (A[mid] == target) {
                return mid;
            } else if (A[mid] >= A[left]) {    // mid_case_1
                // if target is in left uprun segment, focus that, otherwise, focus right uprun segment
                if (A[left] <= target && target < A[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {    // mid_case_2
                // if target is in right uprun segment, focus that, otherwise, focus left uprun segment
                if (A[mid] < target && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
