package AlgoGreedy;/* for meeting */

public class IntervalMeeting {
    public int start;
    public int end;
    int weight;

    IntervalMeeting() {
        start = 0;
        end = 0;
        weight = 0;
    }

    IntervalMeeting(int s, int e, int w) {
        start = s;
        end = e;
        this.weight = w;
    }

    public IntervalMeeting(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }
}

