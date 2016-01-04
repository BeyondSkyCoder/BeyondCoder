package DataStructureTreeTrie;

public class TrieImplement_TRIE {

    public static void main(String[] args) {
        boolean res;

        TrieTree t = new TrieTree();

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