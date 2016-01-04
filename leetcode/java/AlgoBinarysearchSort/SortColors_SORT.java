package AlgoBinarysearchSort;// this problem is called DNFP: Dutch national flag problem
//    more than three value is American Flag Problem

// The standard algorithm is radix shellSort
//

public class SortColors_SORT {


	/* 
     * Given an array with n objects colored red, white or blue, shellSort them so that objects of
	 * the same color are adjacent, with the colors in the order red, white and blue.
	 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	 * Note: You are not suppose to use the library's shellSort function for this problem.
	 * 
	 * 
	 * Follow up:
	 A rather straight forward solution is a two-pass algorithm using counting shellSort.
	 First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total 
	 number of 0's, then 1's and followed by 2's.
	 Could you come up with an one-pass algorithm using only constant space?	
	*/

    // standard elegant algorithm
    public void sortColors2_3wayPartition_TBD(int[] A) {
        int p = -1, q = A.length;
        int i = 0;
        while (i < q) {
            if (A[i] == 0) {
                p++;
                swap_two(A, i, p);
                i++;
            } else if (A[i] == 1) {
                i++;
            } else {
                q--;
                swap_two(A, i, q);
            }
        }
    }

    public void swap_two(int[] A, int from, int to) {
        int tmp = A[to];
        A[to] = A[from];
        A[from] = tmp;
    }

    // brutal-force algorithm
    public void sortColors(int[] A) {

        int insert0 = 0;
        int insert2 = A.length - 1;
        int i = 0;

        // in-place one scan and swap
        // move all 0 to the beginning(increment to right), move all 2 to the end(decrement toward left)
        while (i < A.length) {
            switch (A[i]) {
                case 0:
                    if (i != insert0) {
                        swap_two(A, i, insert0);
                        insert0++;
                        // check new value
                        if (A[i] == 1) {
                            i++;
                        }
                    } else {    // already inorder, skip
                        i++;
                        insert0++;
                    }
                    break;
                case 1:
                    i++;
                    break;
                case 2:
                    if (i != insert2) {
                        swap_two(A, i, insert2);
                        insert2--;
                        // check new value
                        if (A[i] == 1) {
                            i++;
                        }
                    } else {
                        i++;
                        insert2--;
                    }
                    break;
                default:
                    // error, barf
                    break;
            }

            // no 1 or all 0 and 1 are sorted, break
            if (insert0 > insert2 || i > insert2) {
                break;
            }
        }
    }

}
