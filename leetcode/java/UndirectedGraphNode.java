package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// complex node way
public class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<>();
	}
}

// simple node, complex graph way
class UndirectedGraphStd {
	public final int V;		// number of vertices
	private int E;			// number of edges
	private List<Integer>[] adj;	// adjancecy lists

	
	public UndirectedGraphStd(int V) {
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