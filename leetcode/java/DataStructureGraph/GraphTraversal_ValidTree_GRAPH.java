package DataStructureGraph;

import java.util.*;

public class GraphTraversal_ValidTree_GRAPH {

    /**
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
     * write a function to check whether these edges make up a valid tree.
     * For example:
     * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
     * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
     * Hint:
     * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
     * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices
     * are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
     * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1]
     * is the same as [1, 0] and thus will not appear together in edges.
     */


    // DFS w/ stack
    //  1. every vertex should only be visited once, no loop
    //  2. not a forest(no isolated trees)

    private boolean validTree_I_DFS_nonrecursive(int n, int[][] edges) {

        // I. build the graph using adjacent list
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // check no cycle using DFS with stack
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);  // graph starts from vertex == 0(simple integer)

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (visited[v]) { // case 1. if already visited, found cycle, abort
                return false;
            }

            visited[v] = true;

            for (int w : graph.get(v)) {    // push v's children, remove v itself from them
                stack.push(w);
                graph.get(w).remove(v);
            }
        }

        // case2. if there is non-connected trees, then the graph is a forest, not a single tree
        for (boolean vis : visited) {
            if (!vis) {
                return false;
            }
        }

        return true;
    }

    // BFS w/ queue, similar to DFS w/ stack
    private boolean validTree_II_BFS(int n, int[][] edges) {
        // build the graph using adjacent list
        List<Set<Integer>> graph = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++)
            graph.add(new HashSet<Integer>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(0);   // graph starts from vertex == 0(simple integer)

        while (!q.isEmpty()) {
            int v = q.poll();

            if (visited[v]) {
                return false;
            }

            visited[v] = true;

            for (int w : graph.get(v)) {    // queue v's children, remove v itself from them
                q.offer(w);
                graph.get(w).remove(v);
            }
        }

        // check fully connected
        for (boolean result : visited) {
            if (!result)
                return false;
        }

        return true;
    }

    // Algorithm: Union-find
    /*
            build a tree with parent link(HashMap), no need to have left/right pointer as we only concern parent-link
            initialize the tree with parent link to itself
            read in each edges list
                if not in the same set, union the two end nodes (find the roots, assign to same root)
                otherwise, return false
     */
    public class solutionUnionFind {
        class Node {
            int val;
            Node parent;

            public Node(int val) {
                this.val = val;
            }
        }

        Map<Integer, Node> forest;

        public boolean validTree_III_UF(int n, int[][] edges) {
            return unionFind(n, edges);
        }

        private boolean unionFind(int n, int[][] edges) {
            if (edges.length != n - 1) return false;

            // make set for each node
            forest = new HashMap<Integer, Node>();
            for (int i = 0; i < n; i++)
                forest.put(i, makeSet(i));

            // for the two vertice of each edge, find if they are in the same set,
            // If so, there is a cycle, return false.
            for (int[] edge : edges) {
                if (find(edge[0]) == find(edge[1]))
                    return false;

                union(edge[0], edge[1]);
            }

            return true;   // processed all edges without abort
        }

        private Node makeSet(int val) {
            Node node = new Node(val);
            node.parent = node;
            return node;
        }

        private Node find(int node) {
            if (forest.get(node).parent.val == node)
                return forest.get(node);

            return find(forest.get(node).parent.val);
        }

        private void union(int node1, int node2) {
            Node set1 = find(node1);
            Node set2 = find(node2);
            set1.parent = set2;
        }
    }
}
