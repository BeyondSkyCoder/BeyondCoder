package DataStructureGraph;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphTraversal_Clone_GRAPH {
    /*
     * Clone an undirected graph. Each node in the graph contains a val and a list of its adj.
     */

    /*
     * Algorithm:
     * 		BFS using queue
     * 		DFS recursive
     * 		DFS non-recursive(using stack)
     * Note
     *      deviate from simple graph-in-a-class case, this one uses node with its own adj list
     *      Therefore, the simple boolean visited[] array needs to be changed to Hashmap
     * 		HashMap[oldnode] -> newnode
     * 	    i.e. use if (!containsKey(oldnode)) to replace if (!visited[oldnode])
     * 	    Since this problem is to clone, the newnode will be the cloned list
     */

    public GNode cloneGraph_BFS(GNode node) {
        if (node == null) {
            return null;
        }

        HashMap<GNode, GNode> hm = new HashMap<>(); // flag to record visited status, and store new clone

        Deque<GNode> q = new LinkedList<>();
        q.add(node);

        // CLONE: new GNode(copy itself)
        GNode head = new GNode(node.val);
        hm.put(node, head);

        while (!q.isEmpty()) {
            GNode v = q.poll();

            // traverse its neighbor list
            for (GNode n : v.adj) {

                if (!hm.containsKey(n)) {
                    q.add(n);

                    GNode tmp = new GNode(n.val);
                    hm.put(n, tmp);
                }

                // CLONE: find the new cloned vertex adj and add clone of n
                hm.get(v).adj.add(hm.get(n));
            }
        }

        return head;
    }

    // DFS with stack, iterative, non-recursion
    public GNode cloneGraph_DFS_stack(GNode node) {
        if (node == null) {
            return null;
        }

        HashMap<GNode, GNode> hm = new HashMap<>();

        GNode head = new GNode(node.val);
        hm.put(node, head);

        LinkedList<GNode> stack = new LinkedList<>();
        stack.push(node);

        while (!stack.isEmpty()) {

            GNode v = stack.pop();
            for (GNode n : v.adj) {

                if (!hm.containsKey(n)) {
                    stack.push(n);

                    // clone
                    GNode newneighbor = new GNode(n.val);

                    // update visited flag
                    hm.put(n, newneighbor);
                }

                // add clone
                hm.get(v).adj.add(hm.get(n));
            }
        }

        return head;
    }

    // DFS with recursion
    public GNode cloneGraph_DFS(GNode node) {
        if (node == null) {
            return null;
        }

        HashMap<GNode, GNode> hm = new HashMap<>();

        GNode head = new GNode(node.val);
        hm.put(node, head);

        cloneGraph_DFS_Helper(hm, node);
        return head;
    }

    public void cloneGraph_DFS_Helper(HashMap<GNode, GNode> hm, GNode node) {
        if (node == null)
            return;

        for (GNode n : node.adj) {

            if (!hm.containsKey(n)) {
                // clone node
                GNode newneighbor = new GNode(n.val);

                // set visited flag and continue DFS
                hm.put(n, newneighbor);
                cloneGraph_DFS_Helper(hm, n);
            }

            // add each newnode to neighbor list of parent
            hm.get(node).adj.add(hm.get(n));
        }
    }
}