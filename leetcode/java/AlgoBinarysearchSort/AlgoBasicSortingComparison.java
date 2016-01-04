package AlgoBinarysearchSort;

/*
  SORT is well studied, classic algorithms
  trade off is memory vs speed

  Other factors are stable, adaptive, worse case O(?)

  O(NlogN) is practically used
  MergeSort and QuickSort are efficient and good for general cases

  O(n^2)
  other sorts like selection/insertion/bubblesort are only used for special case
  	like small or almost sorted array.
*/

import java.util.Random;

public class AlgoBasicSortingComparison {

    // Selection Sort, O(n^2)
    // ========================
    //  start from offset 0, then 1, record it as smallest
    //      compare with the rest of array, if there is smaller, swap
    //
    //  in-place algorithm, not stable
    //  requires at most n-1 swaps, good for expensive data moving system
    public void SelectionSort() {
        int smallest;
        for (int out = 0; out < data.length - 1; out++) {

            // find the index of smallest value
            smallest = out;
            for (int inner = out + 1; inner < data.length; inner++) {
                if (data[inner] < data[smallest])
                    smallest = inner;
            }
            swap(out, smallest);
        }
    }

    // Insertion Sort, O(n^2)
    // ========================
    //  outer loop starts from 1, inner loop start from 0 to outer loop
    // in-place algorithm, stable

    public void InsertionSort() {
        int cur;

        for (int out = 1; out < data.length; out++) {
            // save data to be inserted
            cur = data[out];

            for (int inner = 0; inner < out; inner++) {

                if (cur <= data[inner]) {

                    // shift right one space to empty a spot
                    System.arraycopy(data, inner, data, inner + 1, out - inner);
                    data[inner] = cur;
                    // after one insert, break, no need to check others in this inner loop
                    //  this is because outer loop start from 1 and it ensures all previous elements
                    //  are already sorted in previous inner loop runs
                    break;
                }
            }
        }
    }

    public void InsertionSort_passinData(int[] da1, int start, int end) {
        int insert;

        // Note, outer loop starts from 1, inner loop start from 0 to outer loop
        for (int next = start + 1; next < end; next++) {
            insert = da1[next];

            for (int i = start; i < next; i++) {

                if (data[i] > insert) {
                    // shift right to empty a spot
                    System.arraycopy(data, i, data, i + 1, next - i);
                    data[i] = insert;
                    // after one insert, break, no need to check others in this inner loop
                    //  this is because outer loop start from 1 and it ensures all previous elements
                    //  are already sorted in previous inner loop runs
                    break;
                }
            }
        }
    }

    // Bubble Sort, O(n^2)
    // ========================
    //  scan through the whole list repeatedly. compare neighbor and swap
    //  stop when no swap was done in previous scan
    //  most simple one. used rarely

    public void bubblesort() {
        int j;
        boolean swapflag = true;

        while (swapflag) {
            swapflag = false;
            for (j = 0; j < data.length - 1; j++) {
                if (data[j] < data[j + 1]) {
                    swap(j, j + 1);
                    swapflag = true;
                }
            }
        }
    }

    //
    // Merge Sort, O(nlog(n)), very important concept.
    // ========================
    //	merge step does the heavylifting.
    //   PROS: the best, average and worse-case running times are all __O(nlog(n))__
    //   CONS: requires O(n) additional memory. For a large dataset, they need to be split into multiple smaller files to fit memory
    //
    public void MergeSort() {
        MergeSortRecursive(data, 0, data.length - 1);
    }


    public void MergeSortRecursive(int[] dat, int low, int high) {

        if ((high - low) < 2) { // can call other type of Sort algorithm like if ((high - low) < 10), insertionSort();
            return;
        }

        int middle = (low + high) / 2;

        MergeSortRecursive(dat, low, middle);
        MergeSortRecursive(dat, middle + 1, high);

        util_mergeArray(dat, low, middle, middle + 1, high);
    }

    // merge two sorted list. This alone is a typical interview coding question
    private void util_mergeArray(int[] dat, int left, int middle1, int middle2, int right) {
        int lIndex = left;
        int rIndex = middle2;
        int c = left;
        int[] combined = new int[dat.length];  // new array to save combining results

        // A. merge two subarrays while there are elements in both
        while (lIndex <= middle1 && rIndex <= right) {
            if (dat[lIndex] <= dat[rIndex]) {
                combined[c++] = dat[lIndex++];
            } else {
                combined[c++] = dat[rIndex++];
            }
        }

        // B. copy rest of whichever array remains
        if (lIndex == middle2) { // left array is empty
            while (rIndex <= right) {
                combined[c++] = dat[rIndex++];
            }
        } else {
            while (lIndex <= middle1) {
                combined[c++] = dat[lIndex++];
            }
        }

        // C. copy back, only touch those elements sorted in this round of merge
        for (int i = left; i <= right; i++) {   // this assumes middle2 = middle1+1. otherwise, need to handle that
            dat[i] = combined[i];
        }
    }

    public String util_subarray(int low, int high) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < low; i++) {
            sb.append("   ");
        }
        for (int i = low; i <= high; i++) {
            sb.append(" ");
            sb.append(data[i]);
        }
        return sb.toString();
    }

    public String toString() {
        return util_subarray(0, data.length - 1);
    }


    // Quicksort, most powerful
    // ========================
    //  PROS: best and average case are O(nlog(n))
    //  CONS: worse case is O(n^2)
    //      divide-and-conquer, efficiency depends on the pivot value
    //  Simple solution requires extra memory while optimized solution doesn't

    public int[] quicksortI_Simple(int[] dat) {
        if (dat.length < 2) {
            return dat;
        }

        int pivotIndex = dat.length / 2;
        int pivotValue = dat[pivotIndex];

        int leftCount = 0;

        // A. count how many are less than the pivot
        for (int i = 0; i < dat.length; i++) {
            if (dat[i] < pivotValue) {
                ++leftCount;
            }
        }

        // B. Allocate the arrays and create the subsets
        int[] left = new int[leftCount];
        int[] right = new int[dat.length - leftCount];
        int l = 0, r = 0;

        for (int i = 0; i < dat.length; i++) {
            if (i == pivotIndex)
                continue;

            int val = dat[i];
            if (val < pivotValue) {
                left[l++] = val;
            } else {
                right[r++] = val;
            }
        }

        // C. Sort the subsets
        left = quicksortI_Simple(left);
        right = quicksortI_Simple(right);

        // D. combine the sorted arrays and the pivot back into the original array
        System.arraycopy(left, 0, dat, 0, left.length);
        dat[left.length] = pivotValue;
        System.arraycopy(right, 0, dat, left.length + 1, right.length);
        return dat;
    }

    //
    // QuicksortII without extra memory
    //
    public void quicksortOptimized(int[] dat) {
        quicksortII_NoExtraMemory(dat, 0, data.length - 1);
    }

    public void quicksortII_NoExtraMemory(int[] dat, int left, int right) {

        int pivotValue = dat[(left + right) / 2];
        int i = left;
        int j = right;

        // create subsets, in-place swap
        while (i <= j) {
            // find the leftmost value greater or equal to pivot
            while (dat[i] < pivotValue) i++;
            // find the rightmost value less or equal to pivot
            while (dat[j] > pivotValue) j--;

            // value equals to the pivot may end up in either partition. But Sort is still correct

            // if the values are in the wrong order, swap them
            if (i <= j) {
                swaparrayindex(dat, i, j);
                i++;
                j--;
            }
        }

        // apply the algorithm to the partitions we made, if any
        quicksortII_NoExtraMemory(dat, left, i);
        quicksortII_NoExtraMemory(dat, j, right);
    }

    //
    // Shell Sort, O(NLogN)
    // ========================
    public void ShellSort(Comparable[] a) {
        int N = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // h-Sort the array
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && sortUtilLess(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }

    // is v < w ?
    private boolean sortUtilLess(Comparable v, Comparable w) {

        return (v.compareTo(w) < 0);
    }

    /* debugging routine */
    private boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (sortUtilLess(a[i], a[i - 1])) return false;
        return true;
    }

    // is the array h-sorted?
    private boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (sortUtilLess(a[i], a[i - h])) return false;
        return true;
    }

    // Heap Sort, O(NLogN)
    // ========================

    public void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(pq, k, N);
        }

        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    /* Helper functions to restore the heap invariant */
    private void sink(Comparable[] pq, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && heapSortUtilless(pq, j, j + 1)) {
                j++;
            }
            if (!heapSortUtilless(pq, k, j)) {
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /*
     * Indices are "off-by-one" to support 1-based indexing.
     */
    private boolean heapSortUtilless(Comparable[] pq, int i, int j) {

        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }


    // Other utilities
    // exchange a[i] and a[j]
    public void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public void swap(int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public void swaparrayindex(int[] indata, int i, int j) {
        int temp = indata[i];
        indata[i] = indata[j];
        indata[j] = temp;
    }

    private static final Random gen = new Random();
    private int[] data;

    public AlgoBasicSortingComparison(int size) {
        data = new int[size];
        for (int k = 0; k < size; k++) {
            data[k] = gen.nextInt(50);
        }
    }

    public static void main(String[] args) {

        // selectionsort ss = new SelectionSort();
        int i;

        final Random gen = new Random();

        int[] listtosort = new int[30];
        for (i = 0; i < 30; i++) {
            listtosort[i] = gen.nextInt(90);
        }
        for (int it : listtosort) {
            System.out.printf("%d  ", it);
        }
        System.out.printf("before Sort\n");
        // TODO listtosort = selectionsort (listtosort);

        for (int it : listtosort) {
            System.out.printf("%d  ", it);
        }

        System.out.printf("after Sort\n");

    }

    public String util_remainingElements(int low, int high) {
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < low; i++)
            temp.append("  ");
        for (int i = low; i <= high; i++)
            temp.append(data[i] + " ");

        temp.append("\n");
        return temp.toString();
    }

    public String toString_touse() {
        return util_remainingElements(0, data.length - 1);

    }
}
