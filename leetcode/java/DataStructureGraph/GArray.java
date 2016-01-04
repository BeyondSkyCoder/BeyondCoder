package DataStructureGraph;

import java.util.LinkedList;
import java.util.List;

// II. Graph-in-class
//      1. Node is simple Integer
//      2. adj is List of Integer Array
//      3. visited is an array of Integer

public class GArray {
    public final int V;        // number of vertices
    private int E;            // number of edges
    private List<Integer>[] adj;    // adjancecy lists
    int [] visited;

    public GArray(int V) {
        this.V = V;
        this.E = 0;

        // top level array already statically allocated, no need to create here in constructor
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
        visited = new int[V];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // bi-directional edges
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
