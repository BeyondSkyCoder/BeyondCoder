package DataStructureTreeTrie;

public class TreeNode {

    public int val;
    public DataStructureTreeTrie.TreeNode left;
    public DataStructureTreeTrie.TreeNode right;
    public boolean visited;
    public boolean flag; // use for different purpose depending on algorithms

    public TreeNode(int x) {
        val = x;
    }

    public int size() {
        return 0;
    }
}
