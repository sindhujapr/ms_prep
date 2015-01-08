package general;

/*
 * My own code that doesn't work :(
 * Suppose the input is correct
 */
public class ExpressionEvaluation1 {
    public static void main(String[] args) {
    System.out.println(calculation("3*(5+2*4)-6+8"));
    }

    public static int calculation(String expression) {
    int[] result = new int[1024];
    int index = 0;

    for (int i = 0; i < expression.length(); i++) {
        int ch = nextOp(expression, i);
        if (ch == ' ')
        continue;

        if (ch == '*' || ch == '+' || ch == '/' || ch == '-') {
        int op1 = result[--index];
        i++;
        int op2 = nextOp(expression, i + 1);
        if (op2 == '(') {
            int start = ++i;
            while (i < expression.length()
                && expression.charAt(i) != ')')
            i++;
            op2 = calculation(expression.substring(start, i));
        }
        result[index] = calc(op1, op2, ch);
        }
        if (ch == '(') {
        int start = i++;
        while (i < expression.length() && expression.charAt(i) != ')')
            i++;
        result[index] = calculation(expression.substring(start, i));
        } else {
        // operand
        result[index++] = ch;
        }
    }

    return result[0];
    }

    public static int nextOp(String expression, int start) {
    int ch = expression.charAt(start);
    if (ch == '(' || ch == ')' || ch == '*' || ch == '/' || ch == '+'
        || ch == '-')
        return ch;

    int end = start;
    while (expression.charAt(end) >= '0' && expression.charAt(end) <= '9')
        end++;
    return Integer.parseInt(expression.substring(start, end));
    }

    public static int calc(int op1, int op2, int operator) {
    switch (operator) {
    case '+':
        return op1 + op2;
    case '-':
        return op1 - op2;
    case '*':
        return op1 * op2;
    case '/':
        if (op2 == 0) {
        System.out.println("error dividend");
        System.exit(0);
        }
        return (int) op1 / op2;
    default:
        System.out.println("Unknown operator");
        return 0;
    }
    }
}