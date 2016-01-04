package DataStructureTreeTrie;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class TreeNodeSD {

    public int val;
    public TreeNodeSD left;
    public TreeNodeSD right;
    public boolean visited;
    public boolean flag; // use for different purpose depending on algorithms

    public TreeNodeSD(int x) {
        val = x;
    }

    public int size() {
        return 0;
    }
}

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection link to
 * be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be
 * deserialized to the original tree structure.
 * For example, you may serialize the following tree
 *   1
 *  /  \
 *  2  3
 * / \
 * 4  5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format,
 *
 * so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
// Algorithm: JSON with stack recursion

public class BinaryTreeSerializeDeserialize {

    public static void main(String[] args) {
        TreeNodeSD root = new TreeNodeSD(1);
        root.left = new TreeNodeSD(2);
        root.right = new TreeNodeSD(3);
        root.right.left = new TreeNodeSD(4);
        root.right.right = new TreeNodeSD(5);

        CodecLeetcodeOJ codec = new CodecLeetcodeOJ();
        String res = codec.serialize(root);
        System.out.print(res);
        // [v:1,l:[v:2,l:n,r:n],r:[v:3,l:[v:4,l:n,r:n],r:[v:5,l:n,r:n]]]
        TreeNodeSD nt = codec.deserialize(res);
        System.out.print(nt);
    }
}

class CodecJSON {

    // Encodes a tree to a single string.
    public String serialize(TreeNodeSD root) {
        if (root == null) return "n";

        String left = serialize(root.left);
        String right = serialize(root.right);

        return "[v:" + root.val + ",l:" + left + ",r:" + right + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNodeSD deserialize(String data) {
        Deque<String> stack = new LinkedList<>();
        Deque<TreeNodeSD> nodeStack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (char c : data.toCharArray()) {
            if (c == '[' || c == ']' || c == ',') {
                stack.push(sb.toString());
                sb.setLength(0);
                stack.push(String.valueOf(c));
                // ???
                if (c == ']') {
                    TreeNodeSD t = new TreeNodeSD(0);
                    String str = null;
                    while (!(str = stack.pop()).equals("[")) {
                        if (str.startsWith("v:"))
                            t.val = Integer.parseInt(str.substring(2));
                        else if (str.equals("l:"))
                            t.left = nodeStack.pop();
                        else if (str.equals("r:"))
                            t.right = nodeStack.pop();
                    }
                    nodeStack.push(t);
                }

            } else {
                sb.append(c);
            }
        }
        return nodeStack.isEmpty() ? null : nodeStack.pop();
    }
}

// comma separated tree value, MIN_VALUE->null
// Algorithm: BFS

class CodecLeetcodeOJ {
    private static final String DELIMITER = ",";

    // Encodes a tree to a single string, preorder DFS
    public String serialize(TreeNodeSD root) {
        if (root == null) {
            return "null";
        }

        StringBuffer serTree = new StringBuffer();
        Queue<TreeNodeSD> q = new LinkedList<>();
        q.add(root);
        TreeNodeSD node;

        while (!q.isEmpty()) {
            node = q.poll();
            if (node.val == Integer.MIN_VALUE) {
                serTree.append("null,");
                continue;
            } else {
                serTree.append(node.val + DELIMITER);
            }

            if (node.left != null) {
                q.add(node.left);
            } else {
                q.add(new TreeNodeSD(Integer.MIN_VALUE));
            }

            if (node.right != null) {
                q.add(node.right);
            } else {
                q.add(new TreeNodeSD(Integer.MIN_VALUE));
            }
        }

        return serTree.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNodeSD deserialize(String data) {
        if (data == null || data.isEmpty() || data.startsWith("null")) {
            return null;
        }

        String[] nodes = data.split(DELIMITER);
        if (nodes.length == 0) {
            return null;
        }

        TreeNodeSD root = new TreeNodeSD(0);
        Queue<TreeNodeSD> q = new LinkedList<>();
        q.add(root);

        TreeNodeSD node;
        int i = 0;
        while (!q.isEmpty()) {
            node = q.poll();
            node.val = Integer.parseInt(nodes[node.val]);

            int left = i + 1;
            int right = i + 2;

            if (left < nodes.length - 1 && !nodes[left].equals("null")) {
                TreeNodeSD leftNode = new TreeNodeSD(left);
                node.left = leftNode;
                q.add(leftNode);
            }
            if (right < nodes.length - 1 && !nodes[right].equals("null")) {
                TreeNodeSD rightNode = new TreeNodeSD(right);
                node.right = rightNode;
                q.add(rightNode);
            }

            i += 2;
        }

        return root;
    }
}