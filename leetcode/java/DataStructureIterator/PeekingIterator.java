package DataStructureIterator;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {

    /**
     * Given an Iterator class interface with methods: next() and hasNext(),
     *
     * design and implement a DataStructureIterator.PeekingIterator
     * that support the peek() operation -- it essentially peek() at the element that will be
     * returned by the next call to next().

     * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
     * Call next() gets you 1, the first element in the list.
     * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
     * You call next() the final time and it returns 3, the last element.
     * Calling hasNext() after that should return false.
     * Hint:
     * Think of "looking ahead". You want to cache the next element.
     * Is one variable sufficient? Why or why not?
     * Test your design with call order of peek() before next() vs next() before peek().
     * For a clean implementation, check out Google's guava library source code.
     */

    // Algorithm: save the lookahead value with flag of whether it's consumed or not

    Iterator<Integer> iter;
    boolean hasPeeked;
    Integer peakedVal;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iter = iterator;
        this.hasPeeked = false;
        this.peakedVal = 0;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!hasPeeked) {
            hasPeeked = true;
            peakedVal = iter.next();
        }
        return peakedVal;
    }

    @Override
    public Integer next() {
        int tmp;
        if (!hasPeeked) {
            return iter.next();
        } else {
            hasPeeked = false;
            Integer res = peakedVal;
            peakedVal = null;
            return res;
        }
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || iter.hasNext();
    }
}
