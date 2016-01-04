package AlgoTwoPointers;

import AlgoGreedy.IntervalMeeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeInsertIntervals_2P {
    /*
     * Given a collection of IntervalMeetings, merge all overlapping IntervalMeetings.

        For example,
        Given [1,3],[2,6],[8,10],[15,18],
        return [1,6],[8,10],[15,18].
     */

    public List<IntervalMeeting> merge(List<IntervalMeeting> IntervalMeetings) {
        // use Java shellSort tool first
        Collections.sort(IntervalMeetings, (x, y) -> { return x.start - y.start; });
        /*
                new Comparator<IntervalMeeting>() {
                    public int compare(IntervalMeeting a, IntervalMeeting b) {
                        return a.start - b.start;
                    }
                }
         */

        int i, j;
        List<IntervalMeeting> res = new ArrayList<IntervalMeeting>();
        IntervalMeeting cur, next;

        for (i = 0; i < IntervalMeetings.size(); ) {
            cur = IntervalMeetings.get(i);

            // search all future IntervalMeetings, merge end if they are overlapping
            for (j = i + 1; j < IntervalMeetings.size(); j++) {
                next = IntervalMeetings.get(j);
                // if end overlap, extend and check next
                if (cur.end >= next.start) {
                    cur.end = Math.max(cur.end, next.end);
                } else {
                    break;
                }
            }

            res.add(new IntervalMeeting(cur.start, cur.end));
            // skip all to j, at least i+1
            i = j;
        }

        return res;
    }
}

/*
Given a set of non-overlapping IntervalMeetings, insert a new IntervalMeeting into the IntervalMeetings (merge if necessary).

You may assume that the IntervalMeetings were initially sorted according to their start times.

Example 1:
Given IntervalMeetings [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new IntervalMeeting [4,9] overlaps with [3,5],[6,7],[8,10].
 */

class InsertIntervalMeetingTBD {

}
