package leetcode;

/*
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * For example,
 *   Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

 * be sure to understand the constaint: lexi order means *between* up and down words, not char inside words !
 */

/*
 * Algorithm, graph, topological sort
 *   KEY design: use HashMap to save vertex and its adjancecy list
 *				 here, the hashkey is char.
 *				can't use normal List<List<Integer>> because the vertex is not Integer and the size(V) is not known upfront
 */

import java.util.*;

public class AlienDictionary_graph {
    public String alienOrder(String[] words) {
    	if (words == null) return null;
    	
    	Map<Character, Set<Character>> graph_hm = new HashMap<>();	
    	
    	for (int i = 0; i < words.length; i++) {
    		for (int j = 0; j < words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if (!graph_hm.containsKey(c)) {
    				graph_hm.put(c, new HashSet<>());
    			}
    		}
    		if (i > 0) {	// order every two words
    			getOrder(words[i-1], words[i], graph_hm);
    		}
    	}
    	return topSort(graph_hm);
    	
    }
    
    public void getOrder(String s, String t, Map<Character, Set<Character>> graph_hm) {
        	
            for(int i = 0; i < Math.min(s.length(), t.length()); i++) {
                char c1 = s.charAt(i), c2 = t.charAt(i);
                
                if (c1 != c2) {
                    if (!graph_hm.get(c1).contains(c2)) {
                    	graph_hm.get(c1).add(c2);
                    }
                    break;		// stop here because after one char different, remaining chars doesn't matter
                }
            }
    }
 
    // standard top sort algorithm
    public String topSort(Map<Character, Set<Character>> graph_hm) {
    	
        StringBuilder sb = new StringBuilder();
        
        // count initial indegree for every char vertex
        Map<Character, Integer> indegree = new HashMap<>();
        for(char c : graph_hm.keySet()) {
            for(char a : graph_hm.get(c)) {
                int count = indegree.containsKey(a) ? indegree.get(a) + 1 : 1;
                indegree.put(a, count);
            }
        }
        
        // find indegree==0, initialize the queue
        Queue<Character> queue = new LinkedList<>();
        for(char c : graph_hm.keySet()) {
            if(!indegree.containsKey(c)) {
                queue.offer(c);
            }
        }
        
        // topological sort
        while(!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for(char a : graph_hm.get(c)) {
                indegree.put(a, indegree.get(a) - 1);
                if(indegree.get(a) == 0) {
                    queue.offer(a);
                }
            }
        }
        
        for (int a : indegree.values()) {	// if there is any non sorted, this is not a DAG, return false
            if (a != 0) return "";
        }
        return sb.toString();
    }
    
    public static void main(String [] args) {
    	AlienDictionary_graph outer = new AlienDictionary_graph();
    	
    	String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
    	
    	System.out.println(outer.alienOrder(words));
    }
}
