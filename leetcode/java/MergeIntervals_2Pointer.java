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
public class MergeIntervals_2Pointer {

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
