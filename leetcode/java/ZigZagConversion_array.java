package leetcode;

public class ZigZagConversion_array {
	
	/*
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 *
	 */
    public String convert(String s, int numRows) {
        int skip_below, skip_above, next;
        StringBuilder res = new StringBuilder();
        int len = s.length();
        if (len == 0) return res.toString();
        
        for (int i = 0; i < numRows; i++) {         
            next = i;
            if (next < len)
                res.append(s.charAt(next));
            else
                break;
            
            while (true) {
                skip_below = (numRows - 1 - i) * 2 - 1;
                skip_above = i * 2 -1;
 
                if (skip_below > 0) {
                    next += skip_below + 1;
                    if (next < len) {
                        res.append(s.charAt(next));
                    } else
                        break;
                }
                
                if (skip_above > 0) {
                    next += skip_above + 1;
                    if (next < len) {
                        res.append(s.charAt(next));
                    } else
                        break;
                }
                
                // special one line case
                if (skip_below < 1 && skip_above < 1) {
                    next++;
                    if (next< len) {
                        res.append(s.charAt(next));
                    } else {
                        break;
                    }
                }
            }
        }
        return res.toString();
    }
}
