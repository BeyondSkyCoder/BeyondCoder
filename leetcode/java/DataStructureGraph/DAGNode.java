package DataStructureGraph;

import java.util.ArrayList;
import java.util.List;

// III. Directed Graph Node-in-class
//      1. Node is a class
//      2. adj is List of class
//      3. inDegree a Integer

public class DAGNode {
    int val;
    List<DAGNode> adj;
    int inDegree;

    DAGNode(int x) {
        this.val = x;
        adj = new ArrayList<>();
    }
}
