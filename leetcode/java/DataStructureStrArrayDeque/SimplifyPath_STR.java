package DataStructureStrArrayDeque;

import java.util.Deque;
import java.util.LinkedList;


public class SimplifyPath_STR {
    /*
        Given an absolute path for a file (Unix-style), simplify it.

        For example,
        path = "/home/", => "/home"
        path = "/a/./b/../../c/", => "/c"
        click to show corner cases.

        Corner Cases:
        Did you consider the case where path = "/../"?
        In this case, you should return "/".
        Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
        In this case, you should ignore redundant slashes and return "/home/foo".
	 */

    // Algorithm:
    //      split string with delimiter "/" to tokens and use Dequeue to operate two scans
    //          1st scan, skip // and /./ cases and handle /../ case. other, just push into stack
    //          2nd scan, reverse the stack(pop the bottom first like queue), reappend /. fixup the empty corner case

    public static String simplifyPath(String path) {
        if (path.length() == 0) return path;

        String[] tokens = path.split("/");
        Deque<String> st = new LinkedList<>();

        for (String s : tokens) {
            if (s.length() == 0 || s.equals(".")) {
                // handle cases of "//" and "/./" -> skip
                continue;
            } else if (s.equals("..")) {
                // handle case of /../, pop one before
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            // use the stack as queue, pop bottom first
            sb.append("/").append(st.removeLast());
        }
        if (sb.length() == 0) { // fixup the empty case
            sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] arg) {
        String t1 = "/home///.///..//abc//../def//.hidden/hij/";
        String t2 = "/.hidden/";
        String t3 = "/";
        String t4 = "///eHx/..";
        String t5 = "/";
        System.out.println(simplifyPath(t1));
    }
}
