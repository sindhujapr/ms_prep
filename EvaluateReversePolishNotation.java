package lc;

import java.util.ArrayList;
import java.util.List;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        // it's valid that the array has only one digital element
        assert tokens != null && tokens.length > 0;

        List<Integer> stack = new ArrayList<Integer>();
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];

            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                int v2 = stack.remove(stack.size() - 1);
                int v1 = stack.remove(stack.size() - 1);

                if (str.equals("+")) {
                    stack.add(v1 + v2);
                } else if (str.equals("-")) {
                    stack.add(v1 - v2);
                } else if (str.equals("*")) {
                    stack.add(v1 * v2);
                } else if (str.equals("/")) {
                    stack.add(v1 / v2);
                }
            } else {
                stack.add(Integer.valueOf(str));
            }
        }

        return stack.get(stack.size() - 1);
    }
}