package AlgoGreedy;

import java.util.Arrays;

public class Candy_GREEDY {
    /*
     * There are N children standing in a line. Each child is assigned a rating value.

        You are giving candies to these children subjected to the following requirements:

        Each child must have at least one candy.
        Children with a higher rating get more candies than their neighbors.
        What is the minimum candies you must give?
     */

    // Algorithm: Greedy with two scans
    //      scan one from left, scan two from right
    //   NOTE, in the rating[i]==rating[i-1] case, no requirement, so the candy can start from 1, no comparison

    public int candyTwoScanFast(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int len = ratings.length;
        int[] candy = new int[len];

        // initialize everybody with 1
        Arrays.fill(candy, 1);

        // scan 1, left to right, ascending
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }

        // scan 2, right to left, descending
        for (int i = len - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
                candy[i - 1] = candy[i] + 1;
            }
        }

        int tot = 0;
        for (int tmp : candy) {
            tot += tmp;
        }
        return tot;
    }
}
