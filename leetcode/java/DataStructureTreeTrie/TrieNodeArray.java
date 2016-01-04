package DataStructureTreeTrie;

/*
 * 	TrieNode II
 * 	    use Array[char] = TrieNode
 * 	    The Array is preallocated, but needs to know the size and is less flexible than HashMap
 */
public class TrieNodeArray {

    public boolean isWord;
    public char val;
    public TrieNodeArray[] child;
    public TrieNodeArray parent;

    public TrieNodeArray() {
        this.isWord = false;
        child = new TrieNodeArray[26];  // just support 26 lower case chars
    }

    public String getWord() {
        TrieNodeArray cur = this;

        StringBuilder sb = new StringBuilder();
        while (cur.parent != null) {
            sb.insert(0, cur.val);
            cur = cur.parent;
        }
        return sb.toString();
    }
}
