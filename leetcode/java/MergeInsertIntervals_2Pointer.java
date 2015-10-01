package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* 
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 */

@SuppressWarnings("ALL")
public class MergeInsertIntervals_2Pointer {

	public List<Interval> merge(List<Interval> intervals) {
		// use Java sort tool first
 		Collections.sort(intervals, 
			new Comparator<Interval>() {
				public int compare (Interval a, Interval b) {
					return a.start - b.start;
				}
			}
		);
	
		int i, j;
		List<Interval> res = new ArrayList<Interval>();
		Interval cur, next;
		
		for (i = 0; i < intervals.size();  ) {
			cur = intervals.get(i);
			
			// search all future intervals, merge end if they are overlapping
			for  (j = i+1; j < intervals.size(); j++) {
				next = intervals.get(j);
				// if end overlap, extend and check next
				if (cur.end >= next.start) {
					cur.end = Math.max(cur.end, next.end);
				} else {
					break;
				}
			}
			
			res.add(new Interval(cur.start, cur.end));
			// skip all to j, at least i+1
			i = j;
		}
		
		return res;
	}
}

/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */

class InsertIntervalTBD {

}
