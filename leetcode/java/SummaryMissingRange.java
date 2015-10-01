package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

// Algorithm, search, similar to MissingRange();

public class SummaryMissingRange {

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int curval = 0;
        int start = 0;
        boolean newseg = true;
        for (int i = 0; i < nums.length; i++) {
            if (newseg) {
                newseg = false;
                start = i;
                curval = nums[i];
            } else {

                if (nums[i] == curval + 1) {
                    curval++;

                } else {    // current is mismatch, termiante the range with previous one i-1
                    newseg = true;

                    res.add(getRange(nums[start], nums[i - 1]));
                    i--;    // rewinde to trigger the newseg flow
                }
            }

            // special case: close it right away when we reach the end
            if (i == nums.length - 1) {
                res.add(getRange(nums[start], nums[i]));
            }
        }

        return res;
    }

    private String getRange(int from, int to) {
        return (from == to) ? Integer.toString(from) : from + "->" + to;
    }
}

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 * <p>
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * <p>
 * testcase [], 1, 1 ===> ["1"]
 * [-1], -1, -1 ==> []
 */

// Algorithm: search
// assume lower <= min <= max <= upper

class MissingRange {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {

        List<String> res = new ArrayList<>();

        int prev = lower - 1;

        for (int i = 0; i <= nums.length; i++) {
            // after the last one, prev = last one, assign cur to upper + 1 due to below comparision
            int cur = (i == nums.length) ? upper + 1 : nums[i];

            if (cur - prev >= 2) res.add(getRange(prev + 1, cur - 1));

            prev = cur;
        }

        return res;
    }

    private String getRange(int from, int to) {
        return (from == to) ? Integer.toString(from) : from + "->" + to;
    }
}
