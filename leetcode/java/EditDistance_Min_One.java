package leetcode;

public class EditDistance_Min_One {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EditDistance_Min_One outer = new EditDistance_Min_One();
		System.out.println("ed[1] " + outer.isOneEditDistance("junk", "jun"));
		
		System.out.println("ed[1] " + outer.EditDistance("junkie", "jun"));
	}

	// STANDARD EditDistance algorithm, find minimum of insert/delete/substitution
	// DP, O(n*m) memory
    public int EditDistance(String word1, String word2) {
    	int i, j;

    	int slen = word1.length();
    	int tlen = word2.length();
    	
    	if (slen == 0) return (tlen);
    	if (tlen == 0) return (slen);
    	
    	int[][] ed = new int[slen+1][tlen+1];
    	
    	for (i=0; i < slen + 1; i++) ed[i][0] = i;
    	for (j=0; j < tlen + 1; j++) ed[0][j] = j;
    	
    	for (i=1; i < slen+1; i++) {
    		for (j=1; j< tlen+1; j++) {
    				
    			int insert = ed[i-1][j] + 1;
    			int delete = ed[i][j-1] + 1;
    			
    			// std definition for substitude, 0 for match, 1 for no-match
    			int temp = (word1.charAt(i-1) == word2.charAt(j-1)) ? 0 : 1;
    			int substitution = ed[i-1][j-1] + temp;
    			
    			// min of (insert, delete, substitute)
    			int min = Math.min(insert, delete);
    			min = Math.min(min, substitution);
    			
    			ed[i][j] = min;    	
    		}
    	}
    			    			
    	return ed[slen][tlen];    	
    }  
    
    // O(1) memory, ACCEPTED by OJ
    // for special requirement of OneEditDistance, this is the optimized solution
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        
        // always make sure first len < second len to simplify below logic
        if (m>n) return isOneEditDistance(t, s);
        
        // return false if length diff is more than 1
        if (n-m>1) return false;
        
        // start the game
        int i=0, shift = n-m; // shift must be either 0 or 1
        
        while (i<m && s.charAt(i)==t.charAt(i)) ++i;
        
        if (i==m) return shift > 0; // if two string are the same (shift==0), return false
        if (shift==0) i++; // if n==m skip current char in s (modify operation in s)
        
        // continue compare
        while (i<m && s.charAt(i)==t.charAt(i+shift)) i++; // use shift to skip one char in t
        
        return i == m;    	
    }
}