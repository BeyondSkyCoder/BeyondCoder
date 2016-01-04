package DataStructureGraph;

import java.util.ArrayList;
import java.util.List;

// III. Directed Graph Node-in-class
//      1. Node is a class
//      2. adj is List of class
//      3. inDegree is Map of class, integer

//      with complex vertex, array visited[] is not enough, need to use others like HashMap[vertex] -> count

public class GNode {
    int val;
    List<GNode> adj;
    GNode(int x) {
        val = x;
        adj = new ArrayList<>();
    }
}