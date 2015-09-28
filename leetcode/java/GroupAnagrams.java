package leetcode;

import java.util.*;

public class GroupAnagrams {
	/*
	 * Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
] 

For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.

	*/
    public List<List<String>> anagrams(String[] strs) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<Integer>> hm = new HashMap<>();
        
        for (int i = 0; i < strs.length; i++) {
            List<Integer> temp = new ArrayList<>();
            String hashkey = anagramsHashWordHelper(strs[i]);
            if (hm.containsKey(hashkey)) {
                temp = hm.get(hashkey);
            }
            temp.add(i);    // add string at index i of strs[] array
            hm.put(hashkey, temp);
        }
        
        // check hashmap to get size > 1 ones
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry mp = (Map.Entry)it.next();
            ArrayList<Integer> idx = (ArrayList<Integer>) mp.getValue();
            if (idx.size() > 1) {
            	List<String> sublist = new ArrayList<>();
                for (int j = 0; j < idx.size(); j++) {
                    sublist.add(strs[idx.get(j)]);
                }
                res.add(sublist);
            }
        }
        
        return res;
    }
    
    // !! for each string, count letterfrequence, concatinate them to form a hashkey
    // because of all lowcase letter
    public String anagramsHashWordHelper(String str) {
        int[] letters =  new int[26];
        for (int j = 0; j < 26; j++) { letters[j] = 0; }
        
        for (int j = 0; j < str.length(); j++) {
            letters[str.charAt(j) - 97]++;
        }
        
        String tmp = "";
        for (int j = 0; j < 26; j++) {
            tmp += letters[j] + "/";
        }
            
        return tmp;
    }
}
