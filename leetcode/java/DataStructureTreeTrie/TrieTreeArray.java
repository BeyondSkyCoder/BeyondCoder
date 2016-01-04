package DataStructureTreeTrie;

public class TrieTreeArray {

    public TrieNodeArray root;

    public TrieTreeArray() {
        root = new TrieNodeArray();
    }

    public void addWord(String word) {

        TrieNodeArray cur = root;

        for (int i = 0; i < word.length(); i++) {
            int offset = word.charAt(i) - 'a';   // convert char to integer used as offset/index

            if (cur.child[offset] == null) {
                cur.child[offset] = new TrieNodeArray();
                cur.child[offset].val = word.charAt(i);

                // extra, store
                cur.child[offset].parent = cur;
            }
            cur = cur.child[offset];
        }
        cur.isWord = true;
    }

    public boolean searchWord(String word) {
        TrieNodeArray cur = root;

        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.child[c] == null) {
                return false;
            }
            cur = cur.child[c];
        }
        return cur.isWord;
    }

    public boolean startsWithPrefix(String word) {
        TrieNodeArray cur = root;

        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (cur.child[c] == null) {
                return false;
            }
            cur = cur.child[c];
        }
        return true;
    }

    // searchWord with dot. The dot character '.' to represent any one letter.
    public boolean searchWordWithDot(String word) {
        TrieNodeArray cur = root;

        return trieSearchDFS(cur, word, 0, word.length());
    }

    public boolean trieSearchDFS(TrieNodeArray node, String word, int pos, int len) {
        // !! length mismatch, return false
        if (node == null || (pos == len && !node.isWord)) {
            return false;
        }

        // The only true exit for this function
        if (pos == len && node.isWord) {
            return true;
        }

        Character c = word.charAt(pos);
        if (c == '.') {
            for (int j = 0; j < 26; j++) {

                boolean res = trieSearchDFS(node.child[j], word, pos + 1, len);

                // if fail, continues. Otherwise, found and must return true now
                if (res)
                    return res;
            }
            return false;
        } else {

            int off = c - 'a';
            return trieSearchDFS(node.child[off], word, pos + 1, len);
        }
    }
}

