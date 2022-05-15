/**
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, 
 * convert it to the simplified canonical path.
 * 
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, 
 * and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'. 
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 * 
 * The canonical path should have the following format:
 * 
 * The path starts with a single slash '/'.
 * Any two directories are separated by a single slash '/'.
 * The path does not end with a trailing '/'.
 * The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * Return the simplified canonical path.
 */
package Problems;

import java.util.*;

public class SimplifyPath {
    // time complexity O(n), where n = path.length()
    // space complexity O(n)
    public static String simplifyPath(String path) {
        if (path.length() == 1)
            return "/";

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int firstLetterIndex = 0;
        if (path.charAt(firstLetterIndex) == '/') {
            while (firstLetterIndex < path.length() && path.charAt(firstLetterIndex) == '/')
                firstLetterIndex++;
        }

        for (int i = firstLetterIndex + 1; i <= path.length(); i++) {
            if (i < path.length() && path.charAt(i) == '/') {
                helper(list, path.substring(firstLetterIndex, i));
                while (i < path.length() && path.charAt(i) == '/')
                    i++;
                firstLetterIndex = i;
            } else if (i >= path.length() - 1) {
                helper(list, path.substring(firstLetterIndex, path.length()));
                break;
            }
        }

        for (String s : list) {
            sb.append("/" + s);
        }

        return sb.length() == 0 ? sb.append('/').toString() : sb.toString();
    }

    private static void helper(List<String> list, String s) {
        if (s.equals("..") && list.size() > 0) {
            list.remove(list.size() - 1);
        } else if (s.equals("..") && list.size() == 0) {
            return;
        } else if (s.equals(".")) {
            return;
        } else {
            list.add(s);
        }
    }

    // another solution using a stack
    // time complexity O(n)
    // space complexity O(n)
    public String anotherSimplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String s : path.split("/")) {
            if (s.equals(".."))
                stack.poll();
            else if (!s.equals("") && !s.equals("."))
                stack.push(s);
        }

        StringBuilder sb = new StringBuilder();
        if (stack.size() == 0)
            return "/";
        while (stack.size() != 0)
            sb.append("/").append(stack.pollLast());
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/a//b////c/d//././/..";
        System.out.println(simplifyPath(path));
    }
}
