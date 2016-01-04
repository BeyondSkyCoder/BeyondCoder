package DataStructureStrArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class TextJustification_STR {
    /*
     * Given an array of words and a length L, format the text such that each line has exactly L characters and is
     * fully (left and right) justified.
     * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.
     * Pad extra spaces ' ' when necessary so that each line has exactly L characters.
     *
     * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line
     * do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
     * For the last line of text, it should be left justified and no extra space is inserted between words.

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
    // Algorithm: [HARD] Time ~ O(N), Space ~ O(N)
    //  calculate line length(add space for each word)
    //      if > L, formLine(words, start, end, strLen, L)，where start and end are the first and last word offset for that line
    //  Corner case:
    //      1. space for each line: M goes into N words. If not evenly possible, extra space goes to left
    //          to do this, distribute required extra space M spaces beyond the original N-1 standard space between words
    //          there is a tricky math calculation there to keep uneven spaces at left words
    //      2. last line, left adjusted

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        if (words == null) {
            return res;
        }

        int len = 0;
        int wordlen = 0;
        int start = 0, end = 0;

        // count words to form a line
        for (int i = 0; i < words.length; i++) {
            wordlen = words[i].length();

            // if current word can be added(reserve one space for non-first word case)
            if (len == 0 || len + 1 + wordlen <= maxWidth) {
                if (len > 0) {
                    // for not-first-word case, add a space
                    len++;
                }
                len += wordlen;
                end = i;

            } else {    // if current word can't be added, form a line, then add cur word to next line

                fulljustify_formline(words, res, start, end, len, maxWidth);
                start = i;
                end = i;
                len = wordlen;
            }
        }

        // special case: form the last line
        fulljustify_formline(words, res, start, end, len, maxWidth);

        return res;
    }

    public void fulljustify_formline(String[] words, List<String> res, int start, int end, int length, int maxWidth) {
        StringBuilder line = new StringBuilder();

        int N = end - start;    // no. of words
        int M = maxWidth - length;    // no. of extra space required (beyond the one-space-between-word)

        if (length == 0) {
            // special case I: original words is empty, fill spaces for the while line
            for (int j = 0; j < maxWidth; j++) {
                line.append(" ");
            }
            res.add(line.toString());
            return;
        }

        // special case: LastLine: either just one word or reach the last word, left-justified, no need to do left-right justified
        if (N == 0 || end == words.length - 1) {
            int rlen = 0;
            for (int i = start; i <= end; i++) {
                if (i > start) {
                    line.append(" ");
                    rlen++;
                }

                line.append(words[i]);
                rlen += words[i].length();
                if (i == end) {
                    // last line, be left-adjusted. fill in space after last word
                    for (int j = 0; j < maxWidth - rlen; j++)
                        line.append(" ");
                }
            }
        } else {
            for (int i = start; i <= end; i++) {

                if (i == start) {
                    line.append(words[i]);
                } else {
                    // distribute the extra M to first N words except the last word
                    // e.g. if 11 extra space needs to be added to 5 words i.e. 11/5=2 and 3 spaces need to be added
                    //     interval after first and before last word (only 4 intervals)
                    //     this will overrun and need to be cut back from the standard space (cut off M%N = 1)
                    for (int j = 0; j < 1 + M / N; j++) line.append(" ");
                    // add standard one space
                    if (i - start <= M % N) line.append(" ");

                    line.append(words[i]);
                }
            }
        }

        res.add(line.toString());
    }


    public static void main(String[] args) {
        TextJustification_STR outer = new TextJustification_STR();

        String[] test = new String[]{"This", "is", "an", "example", "of", "text", "justification. "};
        String[] t0 = new String[]{""};
        String[] t1 = new String[]{"a"};

        List<String> res = outer.fullJustify(t0, 2);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
