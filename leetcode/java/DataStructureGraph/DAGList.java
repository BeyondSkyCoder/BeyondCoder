package DataStructureGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// I. Directed Graph-in-class
//      1. Node is simple Integer
//      2. adj is List of List of Integer
//      3. inDegree is array of Integer

public class DAGList {

    public final int V;        // number of total vertices
    public int E;            // number of total edges
    public List<List<Integer>> adj; // list of adjancecy lists
    public int[] inDegree;  // store indegreee to array as it's just integer
    public int[] visited;   // for traversal

    // add edge v->w
    public void addEdge(int v, int w) {
        E++;
        adj.get(v).add(w);
    }

    // simple constructor
    DAGList(int V) {
        this.E = 0;

        this.V = V;
        visited = new int[V];
        inDegree = new int[V];
        adj = new ArrayList<>();    // create top level list
        for (int v = 0; v < V; v++) {   // create each adj list
            adj.add(v, new LinkedList<>());
        }
    }

    // complex constructor to take input edges of [[1, 2], [2, 3] ...] to help some coding problem
    DAGList(int V, int[][] edges) {
        this(V);    // calls simple constructor

        this.E = edges.length;
        for (int i = 0; i < E; i++) {
            addEdge(edges[i][1], edges[i][0]);
        }

        // update inDegree
        for (int i = 0; i < V; i++) {
            for (Integer w : adj.get(i)) {
                inDegree[w]++;
            }
        }
    }
}

