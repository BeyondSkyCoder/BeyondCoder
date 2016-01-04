/*
 * Implement int sqrt(int x).
    Compute and return the square root of x.
 * Algorithm: Binary Search
 */

public class Sqrt_BS {

    // non-recursive
    public int sqrt(int x) {

        long mid = x / 2;       // use long to avoid mid*mid overflow
        int right = (int) mid;  // start with half, which is safe
        int left = 0;

        while (left <= right) {
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                // check next higher number
                if ((mid + 1) * (mid + 1) > x) {
                    return (int) mid;
                } else if ((mid + 1) * (mid + 1) == x) {
                    return (int) (mid + 1);
                }

                // still too small, move left up
                left = (int) mid;
                mid = (right + left) / 2;
            } else {
                if ((mid - 1) * (mid - 1) < x) {
                    return (int) (mid - 1);
                } else if ((mid - 1) * (mid - 1) == x) {
                    return (int) mid;
                }
                // too big, move right down
                right = (int) mid;
                mid = (right + left) / 2;
            }
        }

        return left;
    }


    // recursive
    public int sqrt2(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        return sqrtL((long) x, 0, x);
    }

    private int sqrtL(long x, int start, int end) {
        int mid = start + (end - start) / 2;
        if ((long) mid * mid == x) {
            return mid;
        } else if ((long) mid * mid < x) {
            if (Math.abs((long) mid * mid - x) < Math.abs((long) (mid + 1) * (mid + 1) - x)) {
                return mid;
            } else {
                return sqrtL(x, mid + 1, end);
            }
        } else {
            if (Math.abs((long) mid * mid - x) < Math.abs((long) (mid - 1) * (mid - 1) - x)) {
                return mid - 1;
            } else {
                return sqrtL(x, start, mid - 1);
            }
        }
    }
}
