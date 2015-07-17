import java.util.Stack;

public class BracketsChecker {

    public boolean finder(String input) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{') {
                s.push(cur);
            }
            if (cur == ')' || cur == ']' || cur == '}') {
                if (s.isEmpty()) {
                    return false;
                }
                char compare = s.pop();
                if ((compare == '(' && cur != ')') ||
                        (compare == '[' && cur != ']') ||
                        (compare == '{' && cur != '}')) {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }

}
