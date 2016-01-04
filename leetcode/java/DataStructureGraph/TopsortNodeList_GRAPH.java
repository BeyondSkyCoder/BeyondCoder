package DataStructureGraph;


import java.util.*;

public class TopsortNodeList_GRAPH {
    // Algorithm: Standard Top shellSort on a complex Node-in-class
    // The input is a complete graph: list of DAG node with adj List inside
    // no need those high level wrapper graph class for V/E/addEdge etc.
    public List<DAGNode> topSort(List<DAGNode> GNL) {

        List<DAGNode> result = new ArrayList<DAGNode>();

        // I. initialize and calculate inDegree
        Map<DAGNode, Integer> inDegree = new HashMap();
        for (DAGNode v : GNL) {
            for (DAGNode w : v.adj) {
                if (inDegree.containsKey(w)) {
                    inDegree.put(w, inDegree.get(w) + 1);
                } else {
                    inDegree.put(w, 1);
                }
            }
        }

        // II. queue and output inDegree == 0
        Deque<DAGNode> q = new LinkedList<DAGNode>();
        for (DAGNode v : GNL) {
            if (!inDegree.containsKey(v)) {
                q.offer(v);
                result.add(v);
            }
        }

        // III. iterate the rest, output inDegree == 0
        while (!q.isEmpty()) {
            DAGNode v = q.poll();

            if (v.adj == null) {    // the list may be incomplete
                continue;
            }

            for (DAGNode w : v.adj) {
                if (!inDegree.containsKey(w)) { // the list may be incomplete
                    continue;
                }
                inDegree.put(w, inDegree.get(w) - 1);
                if (inDegree.get(w) == 0) {
                    q.offer(w);
                    result.add(w);
                }
            }
        }

        return result;
    }
}
