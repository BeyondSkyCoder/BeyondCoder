package DataStructureTreeTrie;

public class TrieAddandSearchWord_TRIE {
    /*
        Design a data structure that supports the following two operations:

        void addWord(word)
        bool search(word)
        search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

        For example:

        addWord("bad")
        addWord("dad")
        addWord("mad")
        search("pad") -> false
        search("bad") -> true
        search(".ad") -> true
        search("b..") -> true
        Note:
        You may assume that all words are consist of lowercase letters a-z.
     */
    // Algorithm: Trie, using simple array representation

    public static void main(String[] args) {
        boolean res;
        TrieTreeArray outer = new TrieTreeArray();

        outer.addWord("bad");
        outer.addWord("dad");
        outer.addWord("mad");

        res = outer.searchWordWithDot("pad");
        System.out.println("found pad ->" + res);
        res = outer.searchWordWithDot("bad");
        System.out.println("found bad ->" + res);
        res = outer.searchWordWithDot(".ad");
        System.out.println("found .ad ->" + res);
        res = outer.searchWordWithDot("b..");
        System.out.println("found b.." + res);
        res = outer.searchWordWithDot("bad.");
        System.out.println("found bad. " + res);    // must be false

    }

}
