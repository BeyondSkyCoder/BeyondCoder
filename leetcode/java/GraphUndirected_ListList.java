package leetcode;

import java.util.ArrayList;
import java.util.List;

public class GraphUndirected_ListList {
    int V;
    int E;
    List<List<Integer>> adj;
    
    GraphUndirected_ListList(int V) {
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>();
        
        for (int i=0; i<V; i++) {
            adj.add(i, new ArrayList<>());
        }
    }
    void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
}