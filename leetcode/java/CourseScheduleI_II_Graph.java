package leetcode;

import java.util.LinkedList;
import java.util.Queue;


public class CourseScheduleI_II_Graph {
	// pretty much reuse TopologicalSort
	
	/*
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

	 */
	
    public int[] findOrder(int numCourses, int[][] PreReq) {

    	GraphDirected_ListList g = new GraphDirected_ListList(numCourses, PreReq);
	
	    Queue<Integer> q = new LinkedList<>();
	    
	    int[] res = new int[g.V];
    	int pos=0;
    	
    	// I. start with vertex of indegree == 0
        for (int i = 0; i < g.V; i++) {
            if (g.indegree[i] == 0)  q.add(i);
        }
        
        // II. loop through to deposit ordered node
        while (!q.isEmpty()) {
        	int node = q.poll();		// remove the node from queue, add to result q
        	res[pos++] = node;
        	
        	for (int w : g.adj.get(node)) {	 
        		g.indegree[w]--;			// subtract 1 for its children
        		if (g.indegree[w] == 0) {	// if any child has 0 indegree now, add to queue 
        			q.add(w);
        		}
        	}
        }
        
        // validate the results
        if (pos < g.V) return new int[0];	// fail to generate topological order
        else return res;
        
    }
    
    /* 
     * There are a total of n courses you have to take, labeled from 0 to n - 1.

    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    For example:

    2, [[1,0]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

    2, [[1,0],[0,1]]
    There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

    Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

     */
	public boolean canFinishI(int numCourses, int[][] prerequisites) {
	    
		GraphDirected_ListList g = new GraphDirected_ListList(numCourses, prerequisites);

	    Queue<Integer> q = new LinkedList<>();
	    
	    int[] res = new int[g.V];
		int pos=0;
		
		// I. start with vertex with indegree==0
	    for (int i = 0; i < g.V; i++) {
	        if (g.indegree[i] == 0)  q.add(i);
	    }
	    
	    // II. scan through the graph vertex, reduce indegree, queue new nodes with indegree==0
	    while (!q.isEmpty()) {
	    	int node = q.poll();		// remove the node from queue, add to result queue
	    	res[pos++] = node;
	    	
	    	for (int w : g.adj.get(node)) {	 
	    		g.indegree[w]--;			// subtract 1 for its children
	    		if (g.indegree[w] == 0) {	// if any child has 0 indegree now, add to queue 
	    			q.add(w);
	    		}
	    	}
	    }
	    
	    // if not equal, there is cycle and some nodes didn't become 0 indegree, can't sort, return false
	    return (pos == g.V);
	}
}
