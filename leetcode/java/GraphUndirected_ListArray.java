package leetcode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// from Algorithm book, This doesn't work on Java8, array of generic type is not allowed

public class GraphUndirected_ListArray {
	public final int V;		// number of vertices
	private int E;			// number of edges
	private List<Integer>[] adj;	// adjancecy lists

	
	public GraphUndirected_ListArray(int V) {
		this.V = V;
		this.E = 0;
		for (int v=0; v < V; v++) {
			adj[v] = new LinkedList<>();
		}
	}

	public int V() { return V; }
	public int E() { return E; }
	
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}

class DepthFirstSearch {
	private boolean[] marked;
	private int count;
		
	public DepthFirstSearch(GraphUndirected_ListArray G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	public boolean marked (int w) { return marked[w]; }

	public int count() {	return count;	}

	private void dfs(GraphUndirected_ListArray G, int v) {
		marked[v] = true;
		count++;
		for (int w : G.adj(v)) {
			if (!marked[w])
				dfs (G, w);
			
			// normally, it will do something here during tranverse
		}
	}
}

class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;		// the linked vertex, first accessed this vertex
	private final int s;		// source
	
	public DepthFirstPaths(GraphUndirected_ListArray G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];	// unique new comparing with above
		this.s = s;
		dfs (G, s);
	}
	
	private void dfs(GraphUndirected_ListArray G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				
				edgeTo[w] = v;		// do something: add edge from parent
				
				dfs(G, w);
			}
		}
	}
	
	public boolean hasPathTo(int v) { return marked[v]; }
	
	public Iterable<Integer> pathTo (int v) {
		if (!hasPathTo(v)) return null;
		
		Stack<Integer> path = new Stack<>();
		
		for (int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
}

class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstPaths(GraphUndirected_ListArray G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs (GraphUndirected_ListArray G, int s) {
		Queue<Integer> q = new LinkedList<>();
		marked[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int v = (int)(q.remove());
			for (int w: G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;		// do something, add parent
					marked[w] = true;
					q.add(w);			// keep the queue rolling
				}
			}
		}
	}
		
	public boolean hasPathTo(int v) { return marked[v]; }
		
	public Iterable<Integer> PathTo(int v) {	// same as DFS
			if (!hasPathTo(v)) return null;
			Stack<Integer> path = new Stack<>();
			for (int x = v; x != s; x = edgeTo[x]) {
				path.push(x);
			}
			path.push(s);
			return path;
	}
}

// is G acylic ? assuming no self-loops or parallel edges
class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	
	public Cycle(GraphUndirected_ListArray G) {
		marked = new boolean[G.V()];
		
		for (int s=0; s < G.V(); s++) {
			if (!marked[s]) {
				dfscycle (G, s, s);
			}
		}
	}
	
	private void dfscycle (GraphUndirected_ListArray G, int v, int u) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w])
				dfscycle (G, w, v);
			else if (w != u)
				hasCycle = true;		
		}
	}
	
	public boolean hasCycle() { return hasCycle; }
}
