package leetcode;

import java.util.HashSet;

public class ValidSudoku_array {

	/*
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
	 */
	
	// three scan to check three different constraints
	
	public boolean isValidSudoku(char[][] board) {

        int SIZE = board.length;
        HashSet<Character> hs = new HashSet<>();
        
        // A. check each row, no duplicate
        for (int i = 0; i < SIZE; i++) {
            hs.clear();
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '.') 
                	continue;
                if (hs.contains(board[i][j]))
                	return false;
                hs.add(board[i][j]);
            }
        }
        
        // B. check each column, no duplicate
        for (int j = 0; j < SIZE; j++) {
            hs.clear();
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] == '.')
                	continue;
                if (hs.contains(board[i][j]))
                	return false;
                hs.add(board[i][j]);
            }
        }
        
        // C. check each 3x3 subboard, no duplicate
        for (int i = 0; i < SIZE; i = i + 3) {
            for (int j = 0; j < SIZE; j = j + 3) {
                hs.clear();
                for (int m = 0; m < 3; m++) {
                    for (int n = 0; n < 3; n++) {
                        if (board[i + m][j + n] == '.') continue;
                        if (hs.contains(board[i + m][j + n])) return false;
                        hs.add(board[i + m][j +n]);
                    }
                }
            }
        }
        
        return true;
	}
}
