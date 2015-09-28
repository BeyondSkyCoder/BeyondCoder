package leetcode;

import java.util.HashMap;
import java.util.Map;

/* 
 * Trie TreeNode use MAP(Character, Node) for children, instead of Array
 * 
 */

class TrieNodeHM {
	boolean isWord;
	private Map<Character, TrieNodeHM> child;

    public TrieNodeHM() {
    	this.child = new HashMap<>();
    }

	public boolean getIsWord() { return isWord; }

	public void setIsWord() { this.isWord = true; }
	
	public TrieNodeHM addChar(Character ch) {
		if (!child.containsKey(ch)) {
			child.put(ch, new TrieNodeHM());
		}
		return child.get(ch);
	}
	
	public TrieNodeHM getChild(Character ch) {
		return child.get(ch);
	}
}

public class Trie {
    private TrieNodeHM root;

    public Trie() {
        root = new TrieNodeHM();
    }

    // Inserts a word into the trie.
    public void addWord(String word) {
    	TrieNodeHM curNode = root;
    	for (int i= 0; i < word.length(); i++) {
    		curNode = curNode.addChar(word.charAt(i)); 		
    	}
    	curNode.setIsWord();
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	TrieNodeHM curNode = root;
    	for (int i=0; i< word.length(); i++) {
    		curNode = curNode.getChild(word.charAt(i));
    		if (curNode == null) {
    			return false;
    		}
    	}
    	return curNode.getIsWord();
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	TrieNodeHM curNode = root;
    	for (int i=0; i< prefix.length(); i++) {
    		curNode = curNode.getChild(prefix.charAt(i));
    		if (curNode == null) {
    			return false;
    		}
    	}
    	return true;
    }
}


class TrieNodeHMSaveStr{
    boolean isWord;
    Map<Character, TrieNodeHMSaveStr> children;	// hashmap to save children

    // extra, save the whole word to help retrieve/printing
    char c;
    String word;		
        
    // additional static members to save instances
    static int counter = 0;
    static Map<Integer, TrieNodeHMSaveStr> nodemap = new HashMap<>();	// label each node with global counter
    
    // additional members for parents, depth and total no.
    TrieNodeHMSaveStr p;
    int no;
    int depth;

    public TrieNodeHMSaveStr(TrieNodeHMSaveStr parent, char c){
    	
        children = new HashMap<>();
        this.c = c;
        isWord = false;
        
        p = parent;
        depth = (p == null) ? 0 : p.depth+1;
        
        no = counter++;
        nodemap.put(no, this);
    }
    
    public void addChar(char c){
        assert(children.get(c) == null);
        children.put(c, new TrieNodeHMSaveStr(this, c));
    }
    public TrieNodeHMSaveStr getChildFromChar(char c){
        return children.get(c);
    }
    public static TrieNodeHMSaveStr findNode(int num){
        assert(num < counter);
        return nodemap.get(num);
    }
}