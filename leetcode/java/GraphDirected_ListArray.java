package leetcode;
import java.util.LinkedList;
import java.util.List;

// Directed Graph, only one way adjancecy list
public class GraphDirected_ListArray {
	public final int V;		// number of vertices
	private int E;			// number of edges
	private List<Integer>[] adj;	// adjancecy lists

	
	public GraphDirected_ListArray(int V) {
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
		// adj[w].add(v);		// remove this from Graph implementation as this is directed edge
		E++;
	}
	
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public GraphDirected_ListArray reverse() {
		GraphDirected_ListArray R = new GraphDirected_ListArray(V);
		for (int v=0; v < V; v++) {
			for (int w : adj(v))
				R.addEdge(w, v);
		}
		return R;
	}
}