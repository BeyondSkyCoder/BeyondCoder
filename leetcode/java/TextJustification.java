package leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.

 */
/*
 * Time ~ O(N), Space ~ O(N) 
逐个读入 words 中的元素，同时累计行的长度 strLen（注意从每行第二个 word 起都要加一个 space 的距离），如果一旦超过 L，则 call formLine(words, start, end, strLen, L)，其中 start 和 end 为该行的开头和结尾单词号。
注意 edge cases:
对 extra spaces 的处理：要求 M 个 extra space 均分到 N 个 interval 中，如果不能 evenly distribute 则多余的先给左边的 intervals。处理方法是现将每个 interval 中依次放入 M / N 个 spaces，然后在第 1 到 第 M % N 个 interval 里再分别放入一个 space。
最后一行 formLine() 不要漏，因为设置读到 character 的数目超过 L 才 formLine()，所以不会重复处理最后一行。
如果一行中只有一个单词，或是最后一行，都要（left-adjusted）。
 */

public class TextJustification {
	
	// pass OJ
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        
        if (words == null) return res;
        
        int length = 0;
        int wordlen = 0;
        int start = 0, end = 0;
        
        // count word to form a line
        for (int i = 0; i < words.length; i++) {
        	wordlen = words[i].length();
        	      	
        	if (length == 0 || length + wordlen <= maxWidth - 1) {
        		if (length > 0) length++;	// need a space after first word
        		length += wordlen;
        		end = i;
        	} else {	// form a line
        		
        		fulljustify_formline(words, res, start, end, length, maxWidth);
        		start = i; end = i; // update end when start moves
        		length = wordlen;	// start with one word
        	}
        }
        
        // last line
        fulljustify_formline(words, res, start, end, length, maxWidth);
        
        return res;
    }
    
    public void fulljustify_formline(String[] words, List<String> res, int start, int end, int length, int maxWidth) {
    	int N = end - start;
    	int M = maxWidth - length;	// extra space beyond the one space between words
    	StringBuilder line = new StringBuilder();
    	
    	if (length == 0) {  // special case I: original words is empty
    		for (int j = 0; j< maxWidth; j++) line.append(" ");
    		res.add(line.toString());
    		return;
    	}
    	
    	if (N == 0 || end == words.length - 1) {
    	    int rlen = 0;
    		for (int i = start; i<= end; i++) {
    			if (i > start) { line.append(" "); rlen++; }
    			
    			line.append(words[i]);
    			rlen += words[i].length();
    			if (i == end) {	// special case II, fill in space after last word
    				for (int j = 0; j< maxWidth - rlen; j++)
    					line.append(" ");
    			}
    		}
    	} else {
    		for (int i = start; i <= end; i++) {
    			
    			if (i == start)	line.append(words[i]);
    			else {
    				// add the original one space between word and extra M/N space
    				for (int j = 0; j< 1 + M/N; j++) line.append(" ");
    				
    				// add require space for first M%N gaps
    				if (i <= start  + M % N) line.append(" ");
    				line.append(words[i]);
    			}
    		}    		
    	}
    	
    	res.add(line.toString());
    }
    
    
    public static void main(String[] args) {
    	TextJustification outer = new TextJustification();

    	String[] test = new String[] {"This", "is", "an", "example", "of", "text", "justification. "};
    	String[] t0 = new String[] {""};
    	String[] t1 = new String[] {"a"};
    	
    	List<String> res = outer.fullJustify(t0, 2);
    	for (String s : res) {
    		System.out.println( s );
    	}
    }
}
