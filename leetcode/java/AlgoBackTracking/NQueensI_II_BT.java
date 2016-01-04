package AlgoBackTracking;

import java.util.ArrayList;
import java.util.List;

public class NQueensI_II_BT {
    /*
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
     *
     * Given an integer n, return all distinct solutions to the n-queens puzzle.

        Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

        For example,
        There exist two distinct solutions to the 4-queens puzzle:

        [
         [".Q..",  // Solution 1
          "...Q",
          "Q...",
          "..Q."],

         ["..Q.",  // Solution 2
          "Q...",
          "...Q",
          ".Q.."]
        ]
     *
     * Algorithm: DFS + Backtracking, Bit Manipulation: Time ~ O(N!), Space ~ O(N)
     *
     *
     *  design 1: decide an efficient datastructure for flag/visited/used
     * 		int matrix[n][n] can work, but unnecessary, will TLE
     * 	    trick is to use 1-D array to save the result because only one queen is possible on each row
     *      i.e. array[row] -> col
     * 		like permutation II, this iteration is one-directional scan
     *
     * design 2: check queen placement by applying rule
     * design 3: convert final result to String
     */

    private static int tot;    // global variable is the easiest way to avoid passing value around and incrementing...


    public List<List<String>> solveNQueensI(int n) {
        List<List<String>> res = new ArrayList<>();

        int[] col = new int[n];

        solveNQueensIHelper(n, res, col, 0);
        return res;
    }

    public static void solveNQueensIHelper(int n, List<List<String>> res, int[] col, int row) {
        if (row == n) {
            solveQueenSaveI(res, col, n);
            return;
        }

        for (int i = 0; i < n; i++) {

            col[row] = i;

            if (validQueen(col, row)) {
                solveNQueensIHelper(n, res, col, row + 1);
            }

            // backtracking, no need to remove because col[row] will be overwritten to new value for next loop
        }
    }

    // check if col[row] value is valid: 1. not on same col with any previous row, 2. not diagonal
    public static boolean validQueen(int[] col, int row) {

        for (int j = 0; j < row; j++) {
            if (col[j] == col[row]) {
                return false;
            }

            int rowdiff = col[j] - col[row];
            int coldiff = row - j;
            if (Math.abs(rowdiff) == coldiff) {
                return false;
            }
        }
        return true;
    }

    public static void solveQueenSaveI(List<List<String>> res, int[] col, int n) {
        List<String> ls = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (col[i] == j)
                    s.append("Q");
                else
                    s.append(".");
            }
            ls.add(s.toString());
        }
        res.add(ls);
    }

    /*
        II. Follow up for N-Queens problem.

        Now, instead outputting board configurations, return the total number of distinct solutions.

        Algorithm: simplier, just output the total number.
     */

    public int totalNQueensII(int n) {
        int[] col = new int[n];
        tot = 0;

        solveNQueensIIHelper(n, col, 0);
        return tot;
    }

    public static void solveNQueensIIHelper(int n, int[] col, int nextcol) {
        if (nextcol == n) {
            tot++;
            return;
        }

        for (int i = 0; i < n; i++) {

            col[nextcol] = i;

            if (validQueen(col, nextcol))
                solveNQueensIIHelper(n, col, nextcol + 1);

            // backtracking, no need to remove because col[row] will be overwritten to new value for next loop
        }
    }

    public static void main(String[] args) {
        NQueensI_II_BT nq = new NQueensI_II_BT();
        List<List<String>> res = nq.solveNQueensI(8);
        for (List<String> l : res) {
            l.forEach(System.out::println);
        }
    }
}
