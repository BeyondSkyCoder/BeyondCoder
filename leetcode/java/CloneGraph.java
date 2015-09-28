package leetcode;

import java.util.HashMap;
import java.util.LinkedList;

/* 
 * very classical DFS/BFS algorithm for Graph
 * 
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 *
 * unlike those Graph definition in book, which simplifies the node as Integer only and build overall neighbor array
 * This real case keep the Node as class with its neighbor list
 * public class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
  }
 * 
 * Therefore, the boolean visited[] becomes Hashmap<Node>
 * 
 * Algorithm:
 * 		standard BFS(using queue)
 * 		DFS recursive
 * 		DFS non-recursive(using stack)
 * Special note: 
 * 			hashmap(oldnode->newnode)
    		use containsKey(oldnode) to achieve the functionality of visited[] in standard algorithm
    		also server the purpose of get newnode then add neighbor to it
 */

public class CloneGraph {
	
	// All three passed the OJ

    public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) {
    	if(node == null)
    		return null;

    	HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
    	UndirectedGraphNode head = new UndirectedGraphNode(node.label);
    	hm.put(node, head);

    	LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
    	queue.add(node);

    	while(!queue.isEmpty()){
    		UndirectedGraphNode curnode = queue.poll();
    		
    		for(UndirectedGraphNode aneighbor: curnode.neighbors){
    			if(!hm.containsKey(aneighbor)){
    				queue.add(aneighbor);		// keep queue rolling
    				
    				UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
    				hm.put(aneighbor, newneighbor);
    			}

    			hm.get(curnode).neighbors.add(hm.get(aneighbor));
    		}
    	}

    	return head;
    }   
    
    public UndirectedGraphNode cloneGraphDFSrecursive(UndirectedGraphNode node) {    
    	if(node == null) 
    		return null;
    	
    	HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
    	
    	// clone head
    	UndirectedGraphNode head = new UndirectedGraphNode(node.label);
    	hm.put(node, head);

    	DFSrecursive(hm, node);	//DFS
    	return head;
    }
    
    public void DFSrecursive(HashMap<UndirectedGraphNode, UndirectedGraphNode> hm, UndirectedGraphNode node){
    	if(node == null)
    		return;

    	for(UndirectedGraphNode aneighbor: node.neighbors){ 
    		if(!hm.containsKey(aneighbor)){
    			// clone node
    			UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
    			
    			// set visited flag
    			hm.put(aneighbor, newneighbor);
    			
    			DFSrecursive(hm, aneighbor);	// continue normal DFS
    		}
    		
    		// add each newnode to neighbor list of parent
    		hm.get(node).neighbors.add(hm.get(aneighbor));
    	}
    }
    
    // DFS iterative using stack
	public UndirectedGraphNode cloneGraphDFSiterative(UndirectedGraphNode node) {
		if(node == null)
			return null;

		HashMap<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
		UndirectedGraphNode head = new UndirectedGraphNode(node.label);
		hm.put(node, head);

		LinkedList<UndirectedGraphNode> stack = new LinkedList<>();
		stack.push(node);

		while(!stack.isEmpty()){
			UndirectedGraphNode curnode = stack.pop();
			for(UndirectedGraphNode aneighbor: curnode.neighbors){//check each neighbor
				if(!hm.containsKey(aneighbor)) {
					stack.push(aneighbor);		// keep the stack rolling
					UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
					hm.put(aneighbor, newneighbor);
				}

				hm.get(curnode).neighbors.add(hm.get(aneighbor));
			}
		}

		return head;
	}	
}