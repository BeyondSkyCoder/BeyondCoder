package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].

 */

	// algorithm: save target words to Trie, DFS. stop of current iterator doesn't match any prefix of Trie
	// once the target word is found, remove it from Trie

public class WordSearchI_II_backtracking {
	
	/* HINT
	 * You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
	 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
	 * What kind of data structure could answer such query efficiently? Does a hash table work? 
	 * Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, 
	 * please work on this problem: Implement Trie (Prefix Tree) first.
	 */

	Set<String> hs = new HashSet<>();	// ensure result has unique string
	
	// pass OJ
	public List<String> findWordsII_trie(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		int row = board.length;
	    int col = board[0].length;
	    if (row == 0 && col == 0) return res;
	    boolean[][] visited = new boolean[row][col];

	    // build Trie
		TrieArray tr = new TrieArray();
		for (String s : words) {
			tr.addWord(s);
		}
		
		TrieArrayNode root = tr.root;
	    
	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j < col; j++) {
	        	// prune search earlier
	        	int idx = board[i][j] - 'a';
	        	if (root.children[idx] == null)
	        		continue;
	        	
	        	visited[i][j] = true;
	            wordsearchTrieDFS(board, res, root.children[idx], visited, i, j);	// try each any cell
	            visited[i][j] = false;
	        }
	    }
				
		return res;
	}

	public void wordsearchTrieDFS(char[][] board, List<String> res, TrieArrayNode node, boolean[][] visited, int i, int j) {
		if (node.isWord) {
			String s = node.getWord();
			if (!hs.contains(s)) {
				hs.add(s);
				res.add(s);
			}		
		}
		    
		int idx;

	    // UP
	    if (i -1 >= 0 && !visited[i-1][j]) {
	    	idx = board[i-1][j] - 'a';
	    	if (node.children[idx] != null) {
	    		visited[i-1][j] = true;
	    		wordsearchTrieDFS(board, res, node.children[idx], visited, i-1, j);
	    		visited[i-1][j] = false;
	    	}
	    }

	    // DOWN
	    if (i + 1 < board.length && !visited[i+1][j]) {
	    	idx = board[i+1][j] - 'a';
	    	if (node.children[idx] != null) {
	    		visited[i+1][j] = true;
	    		wordsearchTrieDFS(board, res, node.children[idx], visited, i+1, j);
	    		visited[i+1][j] = false;
	    	}
	    }

	    // LEFT
	    if (j - 1 >= 0 && !visited[i][j-1]) {
	    	idx = board[i][j-1] - 'a';
	    	if (node.children[idx] != null) {
	    		visited[i][j-1] = true;
	    		wordsearchTrieDFS(board, res, node.children[idx], visited, i, j-1);
	    		visited[i][j-1] = false;
	    	}
	    }
	    
	    // RIGHT
	    if (j + 1 < board[0].length && !visited[i][j+1]) {
	    	idx = board[i][j+1] - 'a';
	    	if (node.children[idx] != null) {
	    		visited[i][j+1] = true;
	    		wordsearchTrieDFS(board, res, node.children[idx], visited, i, j+1);
	    		visited[i][j+1] = false;
	    	}
	    }
	}
	
	 
	// brutal force, TLE
	public List<String> findWordSearchII_simpleTLE(char[][] board, String[] words) {
		List<String> res = new ArrayList<>();
		int row = board.length;
	    int col = board[0].length;
	    if (row == 0 && col == 0) return res;
	    boolean[][] visited = new boolean[row][col];
	    
	    for (String s : words) {	// search word use WordSearchI()
	    	
		    for (int i = 0; i<row; i++) {
		        for (int j = 0; j<col; j++) {
		            visited[i][j] = false;
		        }
		    }
	    
		    for (int i = 0; i < row; i++) {
		        for (int j = 0; j < col; j++) {
		            if (wordsearchIhelper(board, s, visited, i, j, 0)) {	// try each any cell
		                res.add(s);
		            }
		        }
		    }
	    }
	    return res;
	}


	
	/* 
	 *  Given a 2D board and a word, find if the word exists in the grid.
	 *	The word can be constructed from letters of sequentially adjacent cell, 
	 *	where "adjacent" cells are those horizontally or vertically neighboring. 
	 *	The same letter cell may not be used more than once.
		For example,	Given board =
		[
		  ["ABCE"],
		  ["SFCS"],
		  ["ADEE"]
		]
	
		word = "ABCCED", -> returns true,
		word = "SEE", -> returns true,
		word = "ABCB", -> returns false.
	 */
	
	// heavy RECURSION algorithm, extensive searching for neighbor element
	
	public boolean existWordSearchI_BT(char[][] board, String word) {
	    int row = board.length;
	    int col = board[0].length;
	    if (row == 0 && col == 0) return false;
	    if (row * col < word.length()) return false;
	    
	    boolean[][] visited = new boolean[row][col];	// each cell can only be used ONCE
	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j < col; j++) {
	            visited[i][j] = false;
	        }
	    }
	    
	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j < col; j++) {
	            if (wordsearchIhelper(board, word, visited, i, j, 0)) {	// try each any cell
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public boolean wordsearchIhelper(char[][] board, String target_word, boolean[][] visited, int i, int j, int str_pos) {
		
	    if (board[i][j] != target_word.charAt(str_pos)) 
	        return false;
	    
	    if (str_pos == target_word.length() - 1)	// no error so far, done
	        return true;
	        
	    // this char is matched on [i, j]. continue matching next using char
	    str_pos++;
	    
	    // forward tracking
	    visited[i][j] = true;
	    
	    int row = board.length;
	    int col = board[0].length;
	    
	    // search i-1, UP
	    if (i > 0 && !visited[i-1][j] &&
	    		wordsearchIhelper(board, target_word, visited, i-1, j, str_pos)) return true;
	    // search i+1, DOWN
	    if (i < row-1 && !visited[i+1][j] &&
	    		wordsearchIhelper(board, target_word, visited, i+1, j, str_pos)) return true;
	    // search j-1, LEFT
	    if (j > 0 && !visited[i][j-1] &&
	    		wordsearchIhelper(board, target_word, visited, i, j-1, str_pos)) return true;
	    // search j+1, RIGHT
	    if (j < col-1 && !visited[i][j+1] &&
	    		wordsearchIhelper(board, target_word, visited, i, j+1, str_pos)) return true;
	    
	    // all the above further search failed, backtracking
	    visited[i][j] = false;   
	    return false;
	}
}
