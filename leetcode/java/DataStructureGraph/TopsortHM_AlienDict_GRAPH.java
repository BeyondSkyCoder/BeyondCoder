package DataStructureGraph;


import java.util.*;

public class TopsortHM_AlienDict_GRAPH {
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
     * Algorithm, graph, Topological shellSort
     *   KEY design: use HashMap to save vertex and its adjancecy list
     *				 here, the hashkey is char.
     *				can't use normal List<List<Integer>> because the vertex is not Integer and the size(V) is not known upfront
     */
    public String alienOrder(String[] words) {
        if (words == null) return null;


        Map<Character, Set<Character>> graph_hm = new HashMap<>();

        // build the graph
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!graph_hm.containsKey(c)) {
                    // create a new Set for each vertex
                    graph_hm.put(c, new HashSet<>());
                }
            }
            if (i > 0) {
                // order every two words, they must follow lexi order
                //  DAG dependencies are built here
                getOrder(words[i - 1], words[i], graph_hm);
            }
        }

        // shellSort the graph
        return topSort(graph_hm);

    }

    public void getOrder(String s, String t, Map<Character, Set<Character>> graph_hm) {

        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);

            if (c1 != c2) {
                if (!graph_hm.get(c1).contains(c2)) {
                    graph_hm.get(c1).add(c2);
                }
                break;        // stop here because after one char different, remaining chars doesn't matter
            }
        }
    }

    // standard top shellSort algorithm
    public String topSort(Map<Character, Set<Character>> graph_hm) {

        StringBuilder sb = new StringBuilder();

        // count initial inDegree for every char vertex
        Map<Character, Integer> indegree = new HashMap<>();
        for (char c : graph_hm.keySet()) {
            for (char a : graph_hm.get(c)) {
                int count = indegree.containsKey(a) ? indegree.get(a) + 1 : 1;
                indegree.put(a, count);
            }
        }

        // find inDegree==0, initialize the queue
        Queue<Character> q = new LinkedList<>();
        for (char c : graph_hm.keySet()) {
            if (!indegree.containsKey(c)) {
                q.offer(c);
            }
        }

        // topological shellSort
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            for (char a : graph_hm.get(c)) {
                indegree.put(a, indegree.get(a) - 1);
                if (indegree.get(a) == 0) {
                    q.offer(a);
                }
            }
        }

        for (int a : indegree.values()) {    // if there is any non sorted, this is not a DAG, return false
            if (a != 0) return "";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TopsortHM_AlienDict_GRAPH outer = new TopsortHM_AlienDict_GRAPH();

        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};

        System.out.println(outer.alienOrder(words));
    }
}
