package DataStructureGraph;

import java.util.*;

// Undirected Graph and its standard algorithms like DFS, BFS, pathTo, hasCycle

// I. Graph-in-class
//      1. Node is simple Integer
//      2. adj is List of List of Integer
//      3. visited is an array of Integer

public class GList {
    int V;
    int E;
    List<List<Integer>> adj;
    boolean[] visited;     // travesal flag
    int count;  // traversal count

    GList(int V) {
        this.V = V;
        this.E = 0;

        adj = new ArrayList<>();    // create top level list
        for (int i = 0; i < V; i++) {
            adj.add(i, new ArrayList<>());  // create list for each vertex
        }

        visited = new boolean[V];
    }

    // bi-directional edges
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    // Graph standard DFS algorithm, start from any vertex, traversal all others vertex
    public void dfs(GList g, int v) {
        g.visited[v] = true;
        count++;

        for (int w : g.adj.get(v)) {

            if (!g.visited[w]) {

                // normally, it will do something here during tranverse

                dfs(g, w);
            }
        }
    }

    // DFS application: PATH recording and print
    private int[] edgeTo;   // last vertex on known path to this vertex
    public boolean hasPathTo(int v) { return visited[v]; }

    public void dfsPathRecorder(GList g, int s) {
        edgeTo = new int[g.V];    // unique new comparing with above
        dfs(g, s);
    }

    // 1. path recording
    private void dfsRecordEdge(GList g, int v) {
        g.visited[v] = true;
        for (int w : g.adj.get(v)) {
            if (!g.visited[w]) {

                edgeTo[w] = v;        // do something: add edge from parent

                dfsRecordEdge(g, w);
            }
        }
    }

    // 2. path output, return Iterable of element inside
    public Iterable<Integer> PathTo(int v, int from_s) {
        if (!hasPathTo(v)) return null;

        Stack<Integer> st = new Stack<>();
        for (int x = v; x != from_s; x = edgeTo[x]) {
            st.push(x);
        }
        st.push(from_s);
        return st;
    }


    // BFS application: solve path recording
    public void bfsPathRecorder(GList g, int s) {
        edgeTo = new int[g.V];
        bfsRecordEdge(g, s);
    }

    private void bfsRecordEdge(GList g, int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        g.visited[s] = true;

        while (!q.isEmpty()) {
            int v = (int) (q.remove());
            for (int w : g.adj.get(v)) {
                if (!g.visited[w]) {
                    edgeTo[w] = v;        // do something, add parent
                    g.visited[w] = true;

                    q.add(w);            // keep the queue rolling
                }
            }
        }
    }


    // DFS application: isGraphAcylic, assuming UndirectedGraph has no self-loops or parallel edges
    private boolean hasCycle;
    public boolean hasCycle() { return hasCycle; }

    public void GraphCheckCycle(GList g) {
        // check every Vertex for cycle
        for (int s = 0; s < g.V; s++) {
            if (!g.visited[s]) {
                dfsHasCycle(g, s, s);
            }
        }
    }

    private void dfsHasCycle(GList g, int v, int parent) {
        g.visited[v] = true;

        for (int w : g.adj.get(v)) {

            if (!g.visited[w]) {
                dfsHasCycle(g, w, v);

            } else if (w != parent) {
                // already visited and it's not parent of current dfs vertex, then cycle exists
                hasCycle = true;
            }
        }
    }
}
