package DataStructureGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// II. Directed Graph-in-class
//      1. Node is simple Integer
//      2. adj is List of Integer Array
//      3. inDegree is array of Integer

public class DAGArray {
    public final int V;        // number of total vertices
    private int E;            // number of total edges
    private List<Integer>[] adj;    // array of adjancecy list
    public int[] indegree;  // store indegreee to array as it's just integer

    public DAGArray(int V) {
        this.E = 0;

        this.V = V;
        indegree = new int[V];
        // no need to create top level arraylist as it's []
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();    // create each adj list
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // add edge v->w
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        // adj[w].add(v);		// remove this from Graph implementation as this is directed edge
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public DAGArray reverseEdge() {
        DAGArray R = new DAGArray(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v))
                R.addEdge(w, v);
        }
        return R;
    }
}

// standard direct Graph traversal with DFS, preOrder/PostOrder

class graphTravesal_DFS {
    private boolean[] marked;
    private Queue<Integer> pre; // vertices in preorder
    private Queue<Integer> post; // vertices in postorder
    private Stack<Integer> reversePost; // vertices in reverse postorder

    public graphTravesal_DFS(DAGArray G) {
        pre = new LinkedList<Integer>();
        post = new LinkedList<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) {
                dfs(G, v);
            }
    }

    private void dfs(DAGArray G, int v) {
        pre.add(v); // preOrder goes first

        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.remove(v); // postOrder go last
        reversePost.push(v);    // reverse postOrder
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}

// graph cycle detection and print
class GraphDirectHasCycle {
    private boolean[] marked;
    private int[] edgeTo;

    private Stack<Integer> cycle;    // vertices on a cycle (if one exists)
    private boolean[] onStack;        // vertices on recursive call stack

    public GraphDirectHasCycle(DAGArray G) {
        onStack = new boolean[G.V()];    //new
        edgeTo = new int[G.V()];

        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfsGraphDirectHasCycleHelper(G, v);
            }
        }
    }

    private void dfsGraphDirectHasCycleHelper(DAGArray G, int v) {
        onStack[v] = true;        // new

        marked[v] = true;

        // check neighbor list of each vertex
        for (int w : G.adj(v)) {

            if (this.hasCycle()) {
                return;    // early return if cycle is detected
            } else if (!marked[w]) {

                // if not reached yet, record the edgeTo and check it recursive
                edgeTo[w] = v;
                dfsGraphDirectHasCycleHelper(G, w);

            } else {
                if (onStack[w]) {
                    cycle = new Stack<Integer>();
                    for (int x = v; x != w; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}