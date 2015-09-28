package leetcode;

import java.util.Iterator;
import java.util.List;

/*
 * Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 */

// use Java Iterator

public class ZigZagIterator {
    Iterator<Integer> i, j, tmp;

    void ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v1.iterator();
        j = v2.iterator();
    }

    public int next() {
        if (i.hasNext()) {
            tmp = i; i = j; j = tmp;
        }
 
        return j.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }
}
