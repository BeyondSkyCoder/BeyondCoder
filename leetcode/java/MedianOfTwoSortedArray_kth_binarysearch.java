package leetcode;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 * 
 * Tags: Divide and Conquer, Array, Binary Search
 * 把所有的同类数据按照大小的顺序排列。如果数据的个数是奇数，则中间那个数据就是这群数据的中位数；
 * 如果数据的个数是偶数，则中间那2个数据的算术平均值就是这群数据的中位数。
 * 
 * 1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
 * 2) If m1 and m2 both are equal then we are done, and return m1 (or m2)
 * 3) If m1 is greater than m2, then median is present in one of the below two subarrays.
		a) From first element of ar1 to m1 (ar1[0...|_n/2_|])
		b) From m2 to last element of ar2 (ar2[|_n/2_|...n-1])
 * 4) If m2 is greater than m1, then median is present in one of the below two subarrays.
		a) From m1 to last element of ar1 (ar1[|_n/2_|...n-1])
		b) From first element of ar2 to m2 (ar2[0...|_n/2_|])
 * 5) Repeat the above process until size of both the subarrays becomes 2.
 * 6) If size of the two arrays is 2 then use below formula to get the median.
		Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
 */

public class MedianOfTwoSortedArray_kth_binarysearch {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	    int m = nums1.length;
        int n = nums2.length;
        int k = (m + n)/2; 
        if ((m + n) % 2 == 0) // even
        	return (findKthMedian(nums1, nums2, k-1, 0, m-1, 0, n-1) + 
        			findKthMedian(nums1, nums2, k, 0, m-1, 0, n-1)) / 2.0;
        else // odd
        	return findKthMedian(nums1, nums2, k, 0, m-1, 0, n-1); 	
     }
        
    public double findKthMedian(int[] A, int[] B, int k, int aStart, int aEnd,int bStart, int bEnd) {
    	int aLen = aEnd - aStart + 1;
    	int bLen = bEnd - bStart + 1;
    	if (aLen == 0) return B[bStart + k];
    	if (bLen == 0) return A[aStart + k];
    	if (k == 0) return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
    	
    	int aMid = aLen * k / (aLen + bLen);	// this is the key split
    	int bMid = k - aMid - 1;
    	
    	// make them array index
    	aMid += aStart;
    	bMid += bStart;
    			
    	// check and throw half of the search space
    	if (A[aMid] > B[bMid]) {
    		k = k - (bMid - bStart + 1);
    		aEnd = aMid;
    		bStart = bMid + 1;
    	} else {
    		k = k - (aMid - aStart + 1);
    		bEnd = bMid;
    		aStart = aMid + 1;
    	}
    	
    	return findKthMedian(A, B, k, aStart, aEnd, bStart, bEnd);
	}		

/*
    public int kthInTwoSortedArray(int[] v1, int n1, int[] v2, int n2, int k) {
    		if (k == 1) return Math.min(v1[0], v2[0]);
    		if (k == n1 + n2) return Math.max(v1[n1-1], v2[n2-1]);
    		
    		int i1 = k * n1 / (n1 + n2);	// key split for i1 and i2
    		if (i1 > n1-1) i1 = n1 - 1;
    		int i2 = k - i1;
    		
    		// [ --------i1 -----------]
    		// [ ------------i2------------]
    		if (v1[i1 - 1] > v2[i2 -1] && (i2 == n2 - 1 || v2[i2] > v1[i1 - 1]))
    			return v1[i1-1];
    		if (v2[i2 - 1] > v1[i1 -1] && (i1 == n1 - 1 || v1[i1] > v2[i2 - 1]))
    			return v2[i2-1];
    		
    		if (v1[i1-1] > v1[i2-1])	// throw v2 high segment
    			return kthInTwoSortedArray(v1, n1, v2 + i2, n2 - i2, k - i2);
    		else
    			return kthInTwoSortedArray(v1 + i1, n1 -i1, v2, n2, k - i1);
    }
*/
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MedianOfTwoSortedArray_kth_binarysearch m = new MedianOfTwoSortedArray_kth_binarysearch();
        // int[] A = {1, 2, 3, 4, 5}; int[] B = {2, 4, 5, 6, 7};
        // int [] A = { }; int [] B = {1};
		int [] A = { 2 }; int [] B = {1, 3};
        System.out.println(m.findMedianSortedArrays(A, B));
	}

}
