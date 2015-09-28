package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * DFS + Bit Manipulation: Time ~ O(N!), Space ~ O(N)
 * 
 * 经典的DFS递归回溯解法，大体思路就是对每一行，按每一列挨个去试，试到了就保存结果没试到就回溯。
 * == 难点大概就是用1个一维数组存皇后所在的坐标值。对于一个棋盘来说，每个点都有横纵坐标，用横纵坐标可以表示一个点。
	而这道题巧就巧在，每一行只能有一个皇后，也就是说，对于一行只能有一个纵坐标值，所以用1维数组能提前帮助解决皇后不能在同一行的问题。
	那么用一维数组表示的话，方法是：一维数组的下标表示横坐标（哪一行），而数组的值表示纵坐标（哪一列）
 * 正是这个一维数组，在回溯找结果的时候不需要进行remove重置操作了，因为回溯的话正好就回到上一行了，就可以再重新找下一个合法列坐标了
 * 
	 * design 1: decide an efficient datastructure for flag/visited/used
	 * 		int matrix[n][n] can work, but unnecessary, will TLE
	 * 		int [] col, is enough to record which col to place queen for EACH array index row !
	 * 		// unlike permutationI, like permutation II, this iteration is one-directional scan
	 * 
	 * design 2: check queen placement by applying rule
	 * design 3: convert final result to String
	 
 */
public class NQueensI_II_backtracking {
	private static int tot;	// global variable is the easiest way to avoid passing value around and incrementing...
	
	// pass OJ
    public int totalNQueens(int n) {
    	int [] col_inrow = new int[n];
    	tot = 0;
    	
    	solveNQueensIIHelper(n, col_inrow, 0);
    	return tot;        
    }
    
    public static void solveNQueensIIHelper(int n, int[] col, int nextcol) {
		if (nextcol == n) {
			tot++;
			return;
		}
			
	   	for (int i=0; i<n; i++) {
	   		col[nextcol] = i;
    			
    		if (validQueen(col, nextcol))
    			solveNQueensIIHelper(n, col, nextcol+1);
    		
    		// no need to remove
    	}
    } 
    
    // pass OJ
    public List<List<String>> solveNQueensI(int n) {
    	List<List<String>> res = new ArrayList<>();
    	
    	int [] col_inrow = new int[n];
    	
    	solveNQueensHelper(n, res, col_inrow, 0);
    	return res;
    }
    
	public static void solveNQueensHelper(int n, List<List<String>> res, int[] col, int nextcol) {
		if (nextcol == n) {
			queenSave(res, col, n);
			return;
		}
			
	   	for (int i=0; i<n; i++) {
	   		col[nextcol] = i;
    			
    		if (validQueen(col, nextcol))
    			solveNQueensHelper(n, res, col, nextcol+1);
    		
    		// no need to remove
    	}
    } 	

    public static boolean validQueen(int [] col, int nextcol) {
    	
   		for (int j=0;j<nextcol;j++) {
   		 	if (col[j] == col[nextcol])
   		 		return false;
   		 		
            int rowdiff = col[j] - col[nextcol];
            int coldiff = nextcol - j;
            
            if (Math.abs(rowdiff) == coldiff)
            	return false;
   		}
   		return true;
    }
    
    public static void queenSave(List<List<String>> res, int [] col, int n) {
    	List<String> ls = new ArrayList<>();
    	
    	for (int i=0; i<n; i++) {
    		StringBuilder s = new StringBuilder();
    		for (int j=0; j<n; j++) {
    			if (col[i] == j)
    				s.append("Q");
    			else
    				s.append(".");
    		}
    		ls.add(s.toString());
    	}
    	res.add(ls);
    }
    
    public static void main(String [] args) {
    	NQueensI_II_backtracking nq = new NQueensI_II_backtracking();
    	List<List<String>> res = nq.solveNQueensI(8);
    	for (List<String> l : res) {
			l.forEach(System.out::println);
    	}
    }
}
