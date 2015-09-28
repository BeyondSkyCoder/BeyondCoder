package leetcode;

public class TrieWordDictionaryUsingArray {
	private TrieArray input;
	
	public TrieWordDictionaryUsingArray() {
		input = new TrieArray();
	}
	
    // Build the Trie by addWord
    public void addWord(String word) {
    	TrieArrayNode cur = input.root;
    	int len = word.length();
    	
    	for (int i=0; i<len; i++) {
    		int c = word.charAt(i) - 'a';
    		if (cur.children[c] == null) {
    			// if not exist, allocate the new array element to point to child TrieNode
    			cur.children[c] = new TrieArrayNode();
    		}
    		cur = cur.children[c];
    	}
    	cur.isWord = true;
    }

    // search2 the Trie for one word
    public boolean search2(String word) {
    	TrieArrayNode cur = input.root;
    	int len = word.length();
    	for (int i=0; i<len; i++) {
    		int c = word.charAt(i) - 'a';
    		if (cur.children[c] == null) {
    			return false;
    		}
    		cur = cur.children[c];
    	}
    	return cur.isWord;
    }
    
    // search prefix
    public boolean startsWith(String word) {
       	TrieArrayNode cur = input.root;
    	int len = word.length();
    	for (int i=0; i<len; i++) {
    		int c = word.charAt(i) - 'a';
    		if (cur.children[c] == null) {
    			return false;
    		}
    		cur = cur.children[c];
    	}
    	return true;
    }
    
    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean searchWithDot(String word) {
    	TrieArrayNode cur = input.root;
    	int len = word.length();
    	return trieSearchDFS(input.root, word, 0, len);
    }
	
	public boolean trieSearchDFS(TrieArrayNode node, String word, int pos, int len) {
		// !! length mismatch, return false
		if (node == null || (pos == len && !node.isWord))
			return false;
		
		// only true exit
		if (pos == len && node.isWord)
			return true;
		
		Character c = word.charAt(pos);
		if (c == '.') {
    			for (int j=0; j<26; j++) {
    				boolean res = trieSearchDFS(node.children[j], word, pos+1, len);
    				
    				// if fail, continues. Otherwise, found and must return true now
    				if (res)
    						return res;
    			}
    			return false;
    	} else {
    		int off = c - 'a';
    	   	return trieSearchDFS(node.children[off], word, pos+1, len); 
    	}
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean res;
		TrieWordDictionaryUsingArray outer = new TrieWordDictionaryUsingArray();
		
		outer.addWord("bad");
		outer.addWord("dad");
		outer.addWord("mad");
		res = outer.searchWithDot("pad"); System.out.println("found pad ->" + res);
		res = outer.searchWithDot("bad"); System.out.println("found bad ->" + res);
		res = outer.searchWithDot(".ad"); System.out.println("found .ad ->" + res);
		res = outer.searchWithDot("b.."); System.out.println("found b.." + res);
		res = outer.searchWithDot("bad."); System.out.println("found bad. " + res);	// must be false
		
	}

}
