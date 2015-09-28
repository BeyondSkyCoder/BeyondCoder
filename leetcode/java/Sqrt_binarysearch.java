package leetcode;

/*
 * Implement int sqrt(int x).

Compute and return the square root of x.
 */

public class Sqrt_binarysearch {
	
	// BINARY search variation
	// must change the pivot to long type to avoid pivot * pivot overflow
	
	// non-recursive
    public int sqrt(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
    	
        long pivot = x/2;
        int up = (int)pivot;
        int down = 0;
        
        while (down <= up) {
            if (pivot * pivot == x) {
                return (int)pivot;
            } else if (pivot * pivot < x) {
                if ((pivot + 1) * (pivot + 1) > x) {
                    return (int)pivot;
                } else if ((pivot + 1) * (pivot + 1) == x) {
                    return (int)(pivot + 1);
                }
                down = (int)pivot;
                pivot = (up + down) / 2;
            } else {
                if ((pivot - 1) * (pivot - 1) < x) {
                    return (int)(pivot - 1);
                }
                up = (int) pivot;
                pivot = (up + down) / 2;
            }
        }
        
        return down;
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
