package DataStructureTreeTrie;

/*
 * TrieNode I. HashMap instead of Array
 *      HashMap[char] -> TrieNode for child, more flexible, dynamic allocation.
 *      method: addChar, getChild
 */

import java.util.HashMap;
import java.util.Map;

class TrieNodeHM {
    public boolean isWord;
    public Map<Character, TrieNodeHM> child;


    // extra stuff for complex algorithm
    char c;
    String word;
    TrieNodeHM parent;
    int tot;
    int depth;
    static int counterGlobal = 0;
    static Map<Integer, TrieNodeHM> numToNodeGlobal = new HashMap<>();

    TrieNodeHM() {
        this.child = new HashMap<>();
        this.isWord = false;
    }

    TrieNodeHM(TrieNodeHM parent, char c) {
        this(); // call simple constructor

        this.c = c;
        this.parent = parent;
        this.depth = (parent == null) ? 0 : parent.depth + 1;

        this.tot = counterGlobal++;   // increase global counterGlobal
        numToNodeGlobal.put(tot, this);
    }

    public boolean getIsWord() {
        return isWord;
    }
    public void setIsWord() {
        this.isWord = true;
    }
    public TrieNodeHM getChild(Character ch) {
        return child.get(ch);
    }

    public TrieNodeHM addChar(Character ch) {
        if (!child.containsKey(ch)) {
            child.put(ch, new TrieNodeHM());
        }
        return child.get(ch);
    }

    public TrieNodeHM getChildFromChar(char c) {
        return child.get(c);
    }

    public static TrieNodeHM findNodeFromGlobalNumber(int num) {
        assert (num < counterGlobal);
        return numToNodeGlobal.get(num);
    }
}
