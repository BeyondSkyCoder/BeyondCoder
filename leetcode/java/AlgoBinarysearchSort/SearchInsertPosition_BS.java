package AlgoBinarysearchSort;

public class SearchInsertPosition_BS {
	/* 
     * Given a sorted array and a target value, return the index if the target is found.
	 * If not, return the index where it would be if it were inserted in order.
	    You may assume no duplicates in the array.
		Here are few examples.
		[1,3,5,6], 5 → 2
		[1,3,5,6], 2 → 1
		[1,3,5,6], 7 → 4
		[1,3,5,6], 0 → 0 
	 */

    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int left = 0;
        int right = A.length - 1;

        // binary search
        while (left <= right) {
            int mid = (left + right) / 2;

            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // (1) At this point, low > high. That is, low >= high+1
        // (2) From the invariant, we know that the index is between [low, high+1],
        //      so low <= high+1. Follwing from (1), now we know low == high+1.
        // (3) Following from (2), the index is between [low, high+1] = [low, low],
        //      which means that low is the desired index
        //      Therefore, we return low as the answer. You can also return high+1 as the result, since low == high+1
        return left;
    }

}
