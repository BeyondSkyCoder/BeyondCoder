package leetcode;

public class ImplementTrie {

	// must implement addWord, searchWord
	public static void main(String[] args) {
		boolean res;
		// TODO Auto-generated method stub
		// Your Trie object will be instantiated and called as such:
		
		// TriePrefixTreeImplement outer = new TriePrefixTreeImplement();
		// TriePrefixTreeImplement.Trie t = outer.new Trie();
		Trie t = new Trie();
		
		t.addWord("somestring");
		t.addWord("some");
		t.addWord("somes");
		t.addWord("string");
		res = t.search("some");
		System.out.println("found some: " + res);
		res = t.search("somes");
		System.out.println("found somes: " + res);
		res = t.search("string");
		System.out.println("found string: " + res);
		res = t.search("str");
		System.out.println("found str: " + res);
	}
}