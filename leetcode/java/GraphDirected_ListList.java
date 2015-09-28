package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraphDirected_ListList {

	public final int V;		// number of vertices
	public int E;			// number of edges
	public List<List<Integer>> adj; // adjancecy lists, use list of list as java doesn't allow Generic array List<Integer>[] adj
	public int[] indegree;

	
	GraphDirected_ListList(int V) {
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = new ArrayList<>();
		for (int v=0; v < V; v++) {
			adj.add(v, new LinkedList<>());
		}
	}

	// edges have format of [[1, 2], [2, 3] ...]
	public GraphDirected_ListList(int V, int[][] edges) {
		this(V);	// constructor calls constructor
		this.E = edges.length;		// E can't be final
		
		// popular edges to adjancecy list
		for (int i=0; i<E; i++) {
			addEdge(edges[i][1], edges[i][0]);
		}
		
		// calculate indegree for all nodes
		for (int i=0; i<V; i++) {
			for (Integer w : adj.get(i)) {
				indegree[w]++;
			}
		}
	}
	
	public void addEdge(int v, int w) {
		adj.get(v).add(w);
	}
}

