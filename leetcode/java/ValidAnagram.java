package leetcode;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> hm = new HashMap<>();
        for (int i=0; i< s.length(); i++) {
        	Character c = s.charAt(i);
            if (hm.containsKey(c))
            	hm.put(c, hm.get(c) + 1);
            else 
                hm.put(c, 1);
        }

        for (int i=0; i< t.length(); i++) {
        	Character c = t.charAt(i);
            if (hm.containsKey(c) && hm.get(c) >= 1)
                hm.put(c, hm.get(c) - 1);
            else
                return false;
        }
        
        return true;
    }
}
