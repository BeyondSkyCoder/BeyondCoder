package AlgoBinarysearchSort;

import java.util.Arrays;

/**
 *
 */
public class AlgoBasicSortingCountRadix {
    // Bucket Sort
    // ========================
    public void bucketSort(int[] arr) {
        int N = arr.length;

        // find min and max;
        int min = arr[0];
        int max = min;

        for (int i = 0; i< N; i++) {
            if (arr[i] > max) max = arr[i];
            else if (arr[i] < min) min = arr[i];
        }

        // create and fill in bucket
        int [] bucket = new int[max - min + 1];
        for (int i=0; i < N; i++) {
            bucket[arr[i] - min]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                arr[outPos++] = i + min;
            }
        }
    }

    // Counting Sort, O(n + k)
    // ========================
    void countSortArray(int arr[], int n, int exp) {
        int[] res = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // change count[i] so that count[i] now contains actually position of this digit in output
        for (i = 1; i < n; i++) {
            count[i] += count[i - 1];
        }

        // build the output
        for (i = n - 1; i >= 0; i--) {
            res[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // copy the output array back to arr[]
        System.arraycopy(res, 0, arr, 0, n);
    }


    // Radix Sort
    // ========================
    public void LSDStringSortRadixCount(String[] a, int W) {
        int R = 256;
        int N = a.length;

        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }


    void radixSortArray(int arr[], int n) {
        int m = getMax(arr, n);

        // do counting shellSort for every digit, exp = 10^i, where i is the current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSortArray(arr, n, exp);
        }
    }

    // a utility to get maximum value in arr[]
    int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        AlgoBasicSortingCountRadix outer = new AlgoBasicSortingCountRadix();
        outer.radixSortArray(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }

}
