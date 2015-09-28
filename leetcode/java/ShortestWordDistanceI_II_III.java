package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ShortestWordDistanceI_II_III {
	/*
	 * III 
	 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
	 */
    
    public int shortestWordDistanceIII(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1, lastsame = -1;
        boolean same = false;
        if (word1.equals(word2)) same = true;
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i< words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            }
            if (words[i].equals(word2)) {
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
	 * words and your method will be called repeatedly many times with different parameters. How would you optimize it?
	 */
    static Map<String, List<Integer>> hm = new HashMap<>();

    public void WordDistanceII(String[] words) {
        for (int i = 0; i<words.length; i++) {
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
        
        int p1 = 0, p2 = 0;
        
        for (; p1 < l1.size() && p2 < l2.size(); ) {
            int dist = Math.abs(l1.get(p1) - l2.get(p2));
            min = Math.min(min, dist);
            if (l1.get(p1) < l2.get(p2)) {	// compare the words set index
                p1++;
            } else {
                p2++;
            }
        }
        
        return min;
    }
    
	/* 
	 * I
	 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */
	
    public int shortestDistanceI(String[] words, String word1, String word2) {
        int idx1 = -1, idx2 = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i< words.length; i++) {
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
    	ShortestWordDistanceI_II_III outer = new ShortestWordDistanceI_II_III();
    	String[] words = new String[] {"a", "c", "a", "a"};
    	int res = outer.shortestWordDistanceIII(words, "a", "a");
    	System.out.println("min distance is " + res);
    }
}
