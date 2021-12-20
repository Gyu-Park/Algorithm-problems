/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 */

package Problems;

public class ValidParentheses {

    public static boolean isValid(String s) {

        if (s.length() == 0 || s.length() == 1)
            return false;

        StringBuilder store = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                store.append(')');
            } else if (s.charAt(i) == '[') {
                store.append(']');
            } else if (s.charAt(i) == '{') {
                store.append('}');
            } else {
                if (store.length() > 0 && store.charAt(store.length() - 1) == s.charAt(i)) {
                    store.replace(store.length() - 1, store.length(), "");
                } else {
                    return false;
                }
            }
        }
        return store.length() == 0 ? true : false;
    }

    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(isValid(s));
        String a = "([)]";
        System.out.println(isValid(a));
    }

}
