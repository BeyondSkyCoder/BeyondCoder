package leetcode;

public class SearchInRotatedSortedArray {

	// binary search variant
	
	// draw a graph to understand
	/* left							right
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
							| mid_case_2
	*/
	
	// refer picture http://fisherlei.blogspot.com/2013/01/leetcode-search-in-rotated-sorted-array.html
	
    public int searchInRotatedArray(int[] A, int target) {
        int len = A.length;
        int left = 0, right = len - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (A[mid] == target) {
                return mid;
                
            } else if (A[left] <= A[mid]) {	// mid_case_1
            	// if target is in left uprun, focus that segment, otherwise, focus right uprun segment
                if (A[left] <= target && target < A[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                
            } else {	// mid_case_2
            	// if target is in right uprun segment, focus that , otherwise, focus left uprun segment
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
