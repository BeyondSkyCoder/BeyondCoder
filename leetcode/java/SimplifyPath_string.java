package leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;


public class SimplifyPath_string {
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

	// Algorithm: String
	/*
	路径简化的依据是：
当遇到"/../"则需要返回上级目录，需检查上级目录是否为空。
当遇到"/./"则表示是本级目录，无需做任何特殊操作。
当遇到"//"则表示是本级目录，无需做任何操作。
当遇到其他字符则表示是文件夹名，无需简化。
当字符串是空或者遇到"/../"，则需要返回一个"/"。
当遇见"/a//b"，则需要简化为"/a/b"。
	 */

	public static void main(String[] arg) {
		String t1 = "/home///.///..//abc//../def//.hidden/hij/";
		String t2 = "/.hidden/";
		String t3 = "/";
		String t4 = "///eHx/..";
		String t5 = "/..";
		System.out.println(simplifyPath(t1));
	}

	public static String simplifyPath(String path) {
		if (path.length() == 0) return path;

		String[] splits = path.split("/");
		Deque<String> stack = new LinkedList<>();
		for (String s : splits) {
			if (s.equals("..")) {
				if (!stack.isEmpty())   stack.removeFirst();
			} else if (s.length() == 0 || s.equals(".")) { // "//" and "/./"
				continue;
			} else {
				stack.addFirst(s);
			}
		}

		StringBuilder str = new StringBuilder();
		if (stack.isEmpty())    stack.addFirst("");
		while (!stack.isEmpty())
			str.append("/").append(stack.removeLast());	// this is not pop/removeFirst, but remove the last
		return str.toString();
	}
}
