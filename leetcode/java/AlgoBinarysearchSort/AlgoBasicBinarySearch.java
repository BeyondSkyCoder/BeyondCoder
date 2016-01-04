package AlgoBinarysearchSort;

import java.util.Arrays;
import java.util.Random;

/**
 * Binary Search Framework
 */

public class AlgoBasicBinarySearch {
    public int binarySearchIn(int[] ar, int target) {
        if (ar == null) {
            return -1;
        }
        return binarySearch(ar, 0, ar.length - 1, target);
    }

    // standard non-recursion, Java API is "Arrays.binarySearch(data, val)"
    public int binarySearch(int[] ar, int left, int right, int target) {

        while (left <= right) {
            int mid = left + (right - left) / 2; // (left + right)/2 may overflow
            if (ar[mid] > target) {

                right = mid - 1;

            } else if (ar[mid] < target) {

                left = mid + 1;

            } else {
                return mid;
                // better to be the last to save excessive comparison to this rare case
            }
        }

        return -1;
    }

    public int binarySearchRecursive(int[] ar, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;
        if (ar[mid] == target) {
            return mid;
        } else if (ar[mid] > target) {
            return binarySearchRecursive(ar, left, mid - 1, target);
        } else {
            return binarySearchRecursive(ar, mid + 1, right, target);
        }
    }

    // prepare some class data with constructor
    private static final Random gen = new Random();
    private int[] data;

    public AlgoBasicBinarySearch(int size) {
        data = new int[size];
        for (int k = 0; k < size; k++) {
            data[k] = gen.nextInt(50);
        }
        Arrays.sort(data);
        System.out.printf("array sorting done\n");

    }

    // variation: if found, return the location; if not found, return insertion position
    public int binarysearch1(int val) {

        int left = 0;
        int right = data.length - 1;
        int location = -1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (val == data[mid]) {
                location = mid;
                return location;
            } else if (val > data[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;    // this is the only difference comparing with above

    }

    // variation2: if array has dup, find the smallest one(first appear)
    public int binarysearch2(int val) {
        int left = 0;
        int right = data.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (data[mid] == val && data[mid - 1] < val) {
                return mid;
            } else if (data[mid] > val) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // variation3: if array has dup, find the biggest one
    public int binarysearch3_TBD(int val) {
        return 0;
    }

    // variation4: if array has dup, find the smallest one with arr[i] > v
    public int binarysearch4(int val) {
        int left = 0;
        int right = data.length - 1;
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (data[mid] > val && data[mid - 1] <= val) {
                return mid;
            } else if (data[mid] > val) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // variation5: if array has dup, find the biggest one with arr[i] < v
    public int binarysearch4_TBD(int val) {
        return 0;
    }
}
