package DataStructureTreeTrie;

/*
    Trie Tree utility methods
        addWord
        searchWord
        startWithPrefix
 */
public class TrieTree {
    private TrieNodeHM root;

    public TrieTree() {
        root = new TrieNodeHM();
    }

    // Insert each char of a word to build a trieTree
    public void addWord(String word) {

        TrieNodeHM curNode = root;
        for (int i = 0; i < word.length(); i++) {
            curNode = curNode.addChar(word.charAt(i));
        }
        curNode.setIsWord();
    }

    public boolean search(String word) {
        TrieNodeHM cur = root;
        for (int i = 0; i < word.length(); i++) {
            cur = cur.getChild(word.charAt(i));
            if (cur == null) {
                return false;
            }
        }
        return cur.getIsWord();
    }

    // prefix search
    public boolean startsWithPrefix(String prefix) {
        TrieNodeHM cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            cur = cur.getChild(prefix.charAt(i));
            if (cur == null) {
                return false;
            }
        }
        return true;
    }
}