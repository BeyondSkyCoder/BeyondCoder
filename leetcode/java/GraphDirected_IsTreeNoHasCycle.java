package leetcode;

public class GraphDirected_IsTreeNoHasCycle {
        
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges[0] == null) return true;
        
        GraphUndirected_ListList g = new GraphUndirected_ListList(n);

        for (int[] edge : edges) {
            // assume each pair has two items
            g.addEdge(edge[0], edge[1]);
        }

        boolean[] visited = new boolean[n];

        if (hasCycle(g, visited, 0, -1)) {
                return false;
        }
        for (int i=0; i < n; i++) {
        	// make sure all vertices are visited(connected)
        	if (!visited[i])
        		return false;
        }        
        return true;
    }
    
    public boolean hasCycle(GraphUndirected_ListList g, boolean[] visited, int v, int parent) {
            visited[v] = true;
        
            for (Integer w : g.adj.get(v)) {
                if (!visited[w])
                	return hasCycle(g, visited, w, v);
                
                else if (w != parent) {		// !! only difference here
                    return true;
                }
            }

            return false;
    }
}
