package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionEvaluation {
    private static Map<Character, Integer> operators = new HashMap<Character, Integer>();
    static {
    /*
     * define the precedences of all operators
     */
    operators.put('#', -1);
    operators.put('(', 0);
    operators.put('+', 1);
    operators.put('-', 1);
    operators.put(')', 2);
    operators.put('*', 2);
    operators.put('/', 2);
    }

    public boolean precede(char c1, char c2) {
    int p1 = operators.get(c1);
    int p2 = operators.get(c2);
    return p1 > p2;
    }

    public List<Character> transform(String exp) {
    System.out.println("input\t" + exp);
    List<Character> suffix = new ArrayList<Character>();
    List<Character> stack = new ArrayList<Character>();
    stack.add('#');
    int i = 0;

    while (!stack.isEmpty() && i < exp.length()) {
        char ch = exp.charAt(i++);
        if (ch >= '0' && ch <= '9') {
        suffix.add(ch);
        } else {
        switch (ch) {
        case '(': {
            stack.add(ch);
            break;
        }
        case ')': {
            char op = stack.remove(stack.size() - 1);
            while (op != '(') {
            suffix.add(op);
            op = stack.remove(stack.size() - 1);
            }
            break;
        }
        default: {
            char op = stack.get(stack.size() - 1);
            /*
             * append the operator of the highest priority
             */
            while (precede(op, ch)) {
            suffix.add(op);
            stack.remove(stack.size() - 1);
            op = stack.get(stack.size() - 1);
            }
            if (ch != '#')
            stack.add(ch);
        }
        }
        }
    }
    
    char op = stack.remove(stack.size() - 1);
    while(op != '#') {
        suffix.add(op);
        op = stack.remove(stack.size()-1);
    }

    return suffix;
    }

    public int evaluation(List<Character> suffix) {
    int i = 0;
    List<Character> stack = new ArrayList<Character>();

    while (i < suffix.size()) {
        char ch = suffix.get(i++);
        if (ch >= '0' && ch <= '9') {
        stack.add(ch);
        } else {
        /*
         * take care of the precedence of the operands
         */
        int p2 = Character.getNumericValue(stack.remove(stack.size() - 1));
        int p1 = Character.getNumericValue(stack.remove(stack.size() - 1));
        stack.add(operate(p1, ch, p2));
        }
    }
    int result = stack.remove(stack.size() - 1);
    return result;
    }

    public char operate(int v1, char op, int v2) {
    int result = 0;

    switch (op) {
    case '+':
        result = v1 + v2;
        break;
    case '-':
        result = v1 - v2;
        break;
    case '*':
        result = v1 * v2;
        break;
    case '/':
        result = v1 / v2;
        break;
    }
    return Character.forDigit(result, 10);
    }

    public static void main(String[] args) {
    ExpressionEvaluation t = new ExpressionEvaluation();
    /*
     * make sure any input number or intermediate result is less than 10 
     */
    List<Character> suffix = t.transform("1*2+(5-(1+4/2))*2");
    System.out.print("suffix\t");
    for (Character ch : suffix)
        System.out.print(ch);
    System.out.println();
    System.out.println((char) t.evaluation(suffix));
    }
}