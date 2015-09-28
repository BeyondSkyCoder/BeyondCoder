package leetcode;

import java.util.Arrays;

/*
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

 * NOTE, in the == case, there is no requirement, so the candy can start from 1, no comparison
 * 
 */


public class Candy_Greedy {
	// faster. two scans from left, then from right
    public int candyTwoScanFast(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int len = ratings.length;
        int tot = 0;
        int[] candy = new int[len];
        Arrays.fill(candy, 1);

        
        // scan 1, left to right, ascending
        for (int i=1; i<len; i++) {
            if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            }
        }
        
        // scan 2, right to left, descending
        for (int i=len-1; i>0; i--) {
            if (ratings[i-1] > ratings[i] && candy[i-1] <= candy[i]) {
                candy[i-1] = candy[i] + 1;
            }
        }
        
        for (int tmp : candy) {
            tot += tmp;
        }
        return tot;
    }
    
	// Greedy, TLE
    public int candyGreedySlow(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int len = ratings.length;
        int tot = 0;
        int[] candy = new int[len];
        candy[0] = 1;
        
        for (int i=1; i<ratings.length; i++) {
            if (ratings[i] == ratings[i-1]) {
                candy[i] = candy[i-1];
            } else if (ratings[i] > ratings[i-1]) {
                candy[i] = candy[i-1] + 1;
            } else {
                // update all previous, this takes more time than DP two scans algorithm
                candy[i] = 1;
                if (candy[i-1] == 1) {  // bump all the previous ones
                    for (int j=i-1;j>0;j--) {
                        if (ratings[j] < ratings[j-1]) {
                            candy[j]++;
                            candy[j-1]++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        
        for (int tmp : candy) {
            tot += tmp;
        }
        return tot;
    }
}
