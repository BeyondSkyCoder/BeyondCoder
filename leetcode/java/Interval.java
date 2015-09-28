package leetcode;

public class Interval {
	public int start;
	public int end;
	int weight;

	Interval() {
		start = 0;
		end = 0;
		weight = 0;
	}

	Interval(int s, int e, int w) {
		start = s;
		end = e;
		this.weight = w;
	}

	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
}

