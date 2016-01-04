package DataStructureGraph;


import java.util.*;

public class TopsortHM_MakefileOrder_GRAPH {

    /*
        Makefile has several targets with dependencies.
        Output a valid make target sequence
     */

    // Algorithm: Topological shellSort with Hash of adjacency list.
    //      Hash has the key as data, no need for node-class or graph-in-class
    //      Itself is complete graph adj list with value

    public static void main(String [] args) {
        Map<String, List<String>> dependencies = new HashMap<>();
        dependencies.put("a", Arrays.asList("b", "c"));
        dependencies.put("b", Arrays.asList("c"));
        dependencies.put("c", Arrays.asList("d", "e", "f"));
        dependencies.put("j", Arrays.asList("k"));

        List<String> result = MakefileOrder(dependencies);
        System.out.println(result);
    }

    public static List<String> MakefileOrder(Map<String, List<String>> deps) {
        List<String> res = new LinkedList<>();

        // I. initialize and calculcate inDegree
        Map<String, Integer> inDegree = new HashMap<>();
        for (String key : deps.keySet()) {
            List<String> v = deps.get(key);
            for (String w : v) {
                if (inDegree.containsKey(w)) {
                    inDegree.put(w, inDegree.get(w)+1);
                } else {
                    inDegree.put(w, 1);
                }
            }
        }

        // II. find inDegree == 0 and queue them
        Deque<String> q = new LinkedList<>();
        for (String key : deps.keySet()) {
            if (!inDegree.containsKey(key)) {
                q.offer(key);
                res.add(key);
            }
        }

        // III, scan and iterate the rest
        while (!q.isEmpty()) {
            String key = q.poll();
            System.out.println("find key is " + key);

            if (!deps.containsKey(key)) {   // check the hashmap as it may omit some adj lists
                continue;
            }

            List<String> v = deps.get(key);
            for (String w : v) {

                if (!inDegree.containsKey(w)) {   // check the hashmap as it may omit some adj lists
                    continue;
                }
                inDegree.put(w, inDegree.get(w) - 1);
                if (inDegree.get(w) == 0) {
                    System.out.println("queue " + w);
                    q.offer(w);
                    res.add(w);
                }
            }
        }

        return res;
    }
}
