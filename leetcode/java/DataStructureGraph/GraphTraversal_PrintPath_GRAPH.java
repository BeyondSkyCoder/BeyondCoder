package DataStructureGraph;


import java.util.ArrayList;
import java.util.List;

class GraphTraversal_PrintPath_GRAPH {
    /**
     * http://www.geeksforgeeks.org/find-paths-given-source-destination/
     *
     * Given a directed graph, a source vertex ‘s’ and a destination vertex ‘d’,
     * print all paths from given ‘s’ to ‘d’.
     *
     * The graph adj list is below
     * 0 1 1 1
     * 0 0 0 1
     * 1 1 0 0
     * 0 0 0 0
     *
     * 2->1->3
     * 2->0->3
     * 2->0->1->3
     *
     */

    // Algorithm: BT + DFS
    static void graphBT_DFS(DAGList g, int s, int d, boolean[] visited, List<Integer> path, int pos) {
        visited[s] = true;
        path.add(s);

        if (s == d) {   // print the one output chain
            for (int i : path) {
                System.out.print(i + "->");
            }
            System.out.println();
        }

        // standard Graph DFS
        for (int n : g.adj.get(s)) {
            if (!visited[n]) {
                graphBT_DFS(g, n, d, visited, path, pos + 1);
            }
        }

        path.remove(path.size() - 1); // back tracking
        visited[s] = false;
    }

    public static void main(String[] args) {
        DAGList g;
        int [][] edges = { {0, 1, 1, 1}, {0, 0, 0, 1}, {1, 1, 0, 0}, {0, 0, 0, 0}};

        g = new DAGList(4, edges);

        /*
        g = new DAGList(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);
        */

        boolean[] visited = new boolean[4];
        graphBT_DFS(g, 2, 3, visited, new ArrayList<Integer>(), 0);
    }
}
