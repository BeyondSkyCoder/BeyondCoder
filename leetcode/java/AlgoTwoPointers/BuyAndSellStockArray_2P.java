package AlgoTwoPointers;

public class BuyAndSellStockArray_2P {
    /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit
        if you were only permitted to complete at most one transaction
        (ie, buy one and sell one share of the stock),
     */

    // Algorithm: scan and find the min and GAP

    public int maxProfitI(int[] prices) {
        int previousMin = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; ++i) {
            if (previousMin > prices[i]) {
                previousMin = prices[i];
            }
            if (prices[i] - previousMin > maxProfit) {
                maxProfit = prices[i] - previousMin;
            }
        }

        return maxProfit;
    }

    /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit.

        You may complete as many transactions as you like (ie, buy one and sell one share of the
        stock multiple times).

        However, you may not engage in multiple transactions at the same time
        (ie, you must sell the stock before you buy again).
     */
    // Algorithm: Greedy, scan and accumulate profit

    public int maxProfitII(int[] prices) {
        int maxProfit = 0;

        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete at most two transactions.

        However, you may not engage in multiple transactions at the same time
        (ie, you must sell the stock before you buy again).
     */

    // Algorithm: ??

    public int maxProfitIII(int[] prices) {
        int minBefore = Integer.MAX_VALUE;
        int[] memo1 = new int[prices.length];
        for (int i = 0; i < prices.length; ++i) {
            minBefore = Math.min(minBefore, prices[i]);
            memo1[i] = prices[i] - minBefore;
            if (i > 0) {
                memo1[i] = Math.max(memo1[i - 1], memo1[i]);
            }
        }

        int maxAfter = Integer.MIN_VALUE;
        int[] memo2 = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; --i) {
            maxAfter = Math.max(maxAfter, prices[i]);
            memo2[i] = maxAfter - prices[i];
            if (i < prices.length - 1) {
                memo2[i] = Math.max(memo2[i], memo2[i + 1]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length; ++i) {
            maxProfit = Math.max(maxProfit, memo1[i] + memo2[i]);
        }
        return maxProfit;
    }

    /*
        Say you have an array for which the ith element is the price of a given stock on day i.
        Design an algorithm to find the maximum profit. You may complete at most k transactions.

        However, you may not engage in multiple transactions at the same time
        (ie, you must sell the stock before you buy again).
     */
    // Algorithm
    public int maxProfitIV(int[] prices) {

    }
}