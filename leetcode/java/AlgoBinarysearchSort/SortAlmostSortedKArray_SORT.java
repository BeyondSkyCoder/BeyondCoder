package AlgoBinarysearchSort;

import java.util.PriorityQueue;

public class SortAlmostSortedKArray_SORT {
    /*
	 * Given an array of n elements, where each element is at most k away from its target position, devise an algorithm
	 * that sorts in O(n log k) time. For example, let us consider k is 2, an element at index 7 in the sorted array,
	 * can be at indexes 5, 6, 7, 8, 9 in the given array.
	 */

	 /*
	  * Algorithm:  Insertion shellSort is the best for almost sorted array !!
	  * 		O(nlogK)
	  * 	    	InsertionSort complexity is O(nK) since the inner loop will be executed at most k times
	  * 		    futher improvement is to use PriorityQueue, which results in time complexity
	  * 		        O(k) + O((n-k)log(k))
	  * 		        if original array element is Exactly K away, or n>>K, it becomes O(nlogK)
	  * 		O(K)
	  * 	   	    only use k extra space (Priority queue)
	  *
	  * reference:
	  * 			http://www.geeksforgeeks.org/nearly-sorted-algorithm/
	  * 			https://gist.github.com/paveytel/243f891c6ddf5052f1f1
	  */

    public int[] sortAlmostSortedK(int A[], int k) {
        int n = A.length;

        // Create a Min Heap of first (k+1) elements from input array, the head must be smallest to move to result array
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= k && i < n; i++) {
            // i < n condition is needed when k > n
            pq.add(A[i]);
        }

        int pos = 0;
        int tmp;

        // scan remaining n-k-1 item, shellSort and output
        for (int i = k + 1; i < n; i++) {
            // put the first element from PQ(sorted) to head of result array
            tmp = pq.poll();
            A[pos++] = tmp;        // reuse the original array

            // add the cur element
            pq.add(A[i]);   // java handles the heap insertion in O(log(k))
        }

        // flush the remaining PQ
        while (!pq.isEmpty()) {
            tmp = pq.poll();
            A[pos++] = tmp;
        }
        return A;
    }

    public static void main(String[] args) {

        int arr[] = {2, 6, 3, 12, 56, 8};
        int[] expected = {2, 3, 6, 8, 12, 56};

        SortAlmostSortedKArray_SORT outer = new SortAlmostSortedKArray_SORT();

        int[] actual = outer.sortAlmostSortedK(arr, 3);

        for (int i = 0; i < expected.length; i++) {
            System.out.println(actual[i]);
            assert (expected[i] == actual[i]);
            assert (expected[i] == arr[i]);
        }
    }
}
