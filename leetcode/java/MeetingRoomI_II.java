package leetcode;

import java.util.*;


public class MeetingRoomI_II {

    /*
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

    Note, same day overlap is accepted for a single room(no gap needed)
     */
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (x, y) -> x.start - y.start);

        List<Interval> rooms = new ArrayList<>();
        rooms.add(intervals[0]);
        int j;
        for (int i=1; i <intervals.length; i++) {
            for (j=0; j < rooms.size(); j++) {

                if (intervals[i].start >= rooms.get(j).end) {
                    rooms.get(j).end = intervals[i].end;
                    break;
                }
            }
            if (j == rooms.size()) {
                rooms.add(intervals[i]);
            }
        }

        return rooms.size();
    }

    /**
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

     For example,
     Given [[0, 30],[5, 10],[15, 20]],
     return false.
     */

    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (x, y) -> x.start - y.start);

        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[j].start < intervals[i].end) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canAttendMeetingsList(List<Interval> intervals) {
        Collections.sort(intervals,
                (o1, o2) -> o1.start - o2.start
        );

        // can be optimized to one for loop by updating the end while traverse the array
        for (int i=0; i <intervals.size()-1; i++) {
            for (int j = i + 1; j < intervals.size(); j++) {
                if (intervals.get(j).start < intervals.get(i).end) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MeetingRoomI_II outer = new MeetingRoomI_II();
        List<Interval>  input = new ArrayList<>();
        input.add(new Interval(0, 30));
        input.add(new Interval(5, 10));
        input.add(new Interval(15, 20));
        System.out.println(outer.canAttendMeetingsList(input));
    }
}
