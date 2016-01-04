package AlgoGreedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MeetingRoomI_II_Greedy {

    /*
        Given an array of meeting time IntervalMeetings consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
        find the minimum number of conference rooms required.

        For example,
        Given [[0, 30],[5, 10],[15, 20]],
        return 2.

        Note, same day overlap is accepted for a single room(no gap needed)
     */
    // Algorithm: Greedy
    //      find the first(Greedy) room to insert the current interval (meeting the condition of with no overlap)
    //      if none found, expand the room(add a new one) to fit the current interval

    public int minMeetingRooms(IntervalMeeting[] IntervalMeetings) {
        if (IntervalMeetings == null || IntervalMeetings.length == 0) return 0;

        Arrays.sort(IntervalMeetings, (x, y) -> x.start - y.start);

        List<IntervalMeeting> rooms = new ArrayList<>();
        rooms.add(IntervalMeetings[0]);
        int j;
        for (int i = 1; i < IntervalMeetings.length; i++) {
            for (j = 0; j < rooms.size(); j++) {

                if (IntervalMeetings[i].start >= rooms.get(j).end) {
                    rooms.get(j).end = IntervalMeetings[i].end;
                    break;
                }
            }
            if (j == rooms.size()) {
                rooms.add(IntervalMeetings[i]);
            }
        }

        return rooms.size();
    }

    /**
     * Given an array of meeting time IntervalMeetings consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * determine if a person could attend all meetings.
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return false.
     */
    // Algorithm: two loop for check no overlap for all intervals

    public boolean canAttendMeetingsArray_2P(IntervalMeeting[] IntervalMeetings) {

        Arrays.sort(IntervalMeetings, (x, y) -> x.start - y.start);

        for (int i = 0; i < IntervalMeetings.length - 1; i++) {
            for (int j = i + 1; j < IntervalMeetings.length; j++) {
                if (IntervalMeetings[j].start < IntervalMeetings[i].end) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean canAttendMeetingsList_2P(List<IntervalMeeting> IntervalMeetings) {
        Collections.sort(IntervalMeetings, (o1, o2) -> o1.start - o2.start);

        // can be optimized to one for loop by updating the end while traverse the array
        for (int i = 0; i < IntervalMeetings.size() - 1; i++) {
            for (int j = i + 1; j < IntervalMeetings.size(); j++) {
                if (IntervalMeetings.get(j).start < IntervalMeetings.get(i).end) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MeetingRoomI_II_Greedy outer = new MeetingRoomI_II_Greedy();
        List<IntervalMeeting> input = new ArrayList<>();
        input.add(new IntervalMeeting(0, 30));
        input.add(new IntervalMeeting(5, 10));
        input.add(new IntervalMeeting(15, 20));
        System.out.println(outer.canAttendMeetingsList_2P(input));
    }
}
