package AlgoBinarysearchSort;

public class FirstBadVersion_BS {
    /*
     * You are a product manager and currently leading a team to develop a new product. Unfortunately,
     * the latest version of your product fails the quality check. Since each version is developed based on the previous version,
     * all the versions after a bad version are also bad.

        Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
        which causes all the following ones to be bad.

        You are given an API bool isBadVersion(version) which will return whether version is bad.
        Implement a function to find the first bad version. You should minimize the number of calls to the API.
     */

    // Algorithm: binary search, keep l, r moving toward each other until there is only one gap between

    public int firstBadVersion(int n) {
        if (n == 0) return -1;
        if (n == 1) return isBadVersion(1) ? 1 : -1;

        int l = 1, r = n;
        int mid;

        while (l + 1 < r) { // critical termination condition
            mid = l + (r - l) / 2;

            if (!isBadVersion(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        if (isBadVersion(l)) return l;
        else if (isBadVersion(r)) return r; // since l is good, if r is bad, that's the first
        else return -1; // there is no bad found at all
    }

    public boolean isBadVersion(int i) {
        return (i == 234);
    }
}
