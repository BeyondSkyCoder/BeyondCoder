package DataStructureGraph;

import java.util.LinkedList;
import java.util.Queue;

public class TopsortList_CourseScheduleI_II_GRAPH {

	/*
     * II. on top of I. return the ordering of courses you should take to finish all courses.
	 */

    // Algorithm: TopologicalSort with result saved to array

    public int[] findOrder_TopsortBFS(int numCourses, int[][] PreReq) {
        DAGList g = new DAGList(numCourses, PreReq);
        Queue<Integer> q = new LinkedList<>();
        int pos = 0;

        int[] res = new int[g.V];   // only difference with problem I

        // I. queuing vertex of inDegree == 0
        for (int i = 0; i < g.V; i++) {
            if (g.inDegree[i] == 0) {
                q.add(i);
            }
        }

        // II. loop through to deposit ordered node
        while (!q.isEmpty()) {

            int v = q.poll();
            res[pos++] = v;

            for (int w : g.adj.get(v)) {
                g.inDegree[w]--;            // subtract 1 for its child
                if (g.inDegree[w] == 0) {    // if any child has 0 inDegree now, add to queue to process next
                    q.add(w);
                }
            }
        }

        if (pos < g.V) {
            return new int[0];    // fail to generate topological order
        } else {
            return res; // only difference with problem I
        }

    }

    /* 
     * I. There are a total of n courses you have to take, labeled from 0 to n - 1.
        Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
        which is expressed as a pair: [0,1]

        Given the total number of courses and a list of prerequisite pairs,
        is it possible for you to finish all courses?

        For example:

        2, [[1,0]]
        There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

        2, [[1,0],[0,1]]
        There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

        Note:
        The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

     */
    // Algorithm: simple Topological shellSort

    public boolean canFinishI_Topsort_BFS(int numCourses, int[][] prerequisites) {

        DAGList g = new DAGList(numCourses, prerequisites);
        Queue<Integer> q = new LinkedList<>();

        int pos = 0;

        // I. queuing vertex with inDegree == 0
        for (int i = 0; i < g.V; i++) {
            if (g.inDegree[i] == 0) {
                q.add(i);
            }
        }

        // II. scan through the graph vertex, reduce inDegree, queue new nodes with inDegree==0
        while (!q.isEmpty()) {

            int v = q.poll();        // remove the node from queue, add to result queue
            pos++;

            for (int w : g.adj.get(v)) {
                g.inDegree[w]--;            // subtract 1 for its child
                if (g.inDegree[w] == 0) {    // if any child has 0 inDegree now, add to queue
                    q.add(w);
                }
            }
        }

        // if not equal, there is cycle and some nodes didn't become 0 inDegree, can't shellSort, return false
        return (pos == g.V);
    }
}
