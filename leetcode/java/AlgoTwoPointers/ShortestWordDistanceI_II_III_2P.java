package AlgoTwoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShortestWordDistanceI_II_III_2P {
    /*
	 * III 
	 * This is a follow up of Shortest Word Distance. The only difference is now
	 *  word1 could be the same as word2.
	 */

    // Algorithm: two pointers

    public int shortestWordDistanceIII(String[] words, String w1, String w2) {
        int idx1 = -1, idx2 = -1, lastsame = -1;
        boolean same = false;
        if (w1.equals(w2)) {
            same = true;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(w1)) {
                idx1 = i;
            }
            if (words[i].equals(w2)) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                if (!same) {
                    int dist = Math.abs(idx1 - idx2);
                    min = Math.min(min, dist);
                } else {
                    if (lastsame == -1) {       // first match
                        lastsame = idx1;
                    } else {
                        if (idx1 != lastsame) { // new match
                            min = Math.min(min, (idx1 - lastsame));
                            lastsame = idx1;
                        }
                    }
                }
            }
        }
        return min;
    }

    /* II
     * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of
     * words and your method will be called repeatedly many times with different parameters.
     * How would you optimize it?
     *      Your WordDistance object will be instantiated and called as such:
            WordDistance wordDistance = new WordDistance(words);
            wordDistance.shortest("word1", "word2");
            wordDistance.shortest("anotherWord1", "anotherWord2");
     */
    // Algorithm: two pointers with HashMap[word] -> index_list

    static Map<String, List<Integer>> hm = new HashMap<>();
    public void WordDistanceII(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (hm.containsKey(words[i])) {
                hm.get(words[i]).add(i);
            } else {    // first time, create the list
                List<Integer> l = new ArrayList<>();
                l.add(i);
                hm.put(words[i], l);
            }
        }
    }
    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;
        List<Integer> l1 = hm.get(word1);
        List<Integer> l2 = hm.get(word2);
        if (l1 == null || l2 == null) return -1;

        int l = 0, r = 0;

        for (; l < l1.size() && r < l2.size(); ) {
            int dist = Math.abs(l1.get(l) - l2.get(r));
            min = Math.min(min, dist);
            if (l1.get(l) < l2.get(r)) {    // compare the words set index
                l++;
            } else {
                r++;
            }
        }

        return min;
    }
    
	/* 
	 * I
	 * Given a list of words and two words word1 and word2, return the shortest distance between
	 * these two words in the list.

        For example,
        Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

        Given word1 = “coding”, word2 = “practice”, return 3.
        Given word1 = "makes", word2 = "coding", return 1.

        Note:
        You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */

    // Algorithm: two pointers
    public int shortestDistanceI(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            }
            if (words[i].equals(word2)) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                int dist = Math.abs(idx1 - idx2);
                min = Math.min(min, dist);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShortestWordDistanceI_II_III_2P outer = new ShortestWordDistanceI_II_III_2P();
        String[] words = new String[]{"a", "c", "a", "a"};
        int res = outer.shortestWordDistanceIII(words, "a", "a");
        System.out.println("min distance is " + res);
    }
}
