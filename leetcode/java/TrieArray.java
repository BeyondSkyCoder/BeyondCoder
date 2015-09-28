package leetcode;

/*
 * Trie TreeNode use Array[char] = TreeNode
 */
class TrieArrayNode {

	boolean isWord;
	char val;
	TrieArrayNode father;
	TrieArrayNode[] children;
	
	public TrieArrayNode() {
		isWord = false;
		children = new TrieArrayNode[26];
	}
	
	public String getWord() {
		TrieArrayNode cur = this;
		StringBuilder sb = new StringBuilder();
		while (cur.father != null) {
			sb.insert(0,  cur.val);
			cur = cur.father;
		}
		return sb.toString();
	}	
}

public class TrieArray {
	TrieArrayNode root;
	
	public TrieArray() {
		root = new TrieArrayNode();
	}
	
	public void addWord(String word) {
		TrieArrayNode cur = root;
		int len = word.length();
		for (int i = 0; i < len; i++) {
			int c = word.charAt(i) - 'a';
			if (cur.children[c] == null) {
				cur.children[c] = new TrieArrayNode();
				cur.children[c].val = word.charAt(i);
				cur.children[c].father = cur;
			}
			cur = cur.children[c];
		}
		cur.isWord = true;
	}
}

