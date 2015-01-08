package test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qingcunz on 9/23/14.
 */
public class PolishNotation {
    public static void main(String[] args) {
        System.out.println(new PolishNotation().evalRPN(new String[] {"4","-2","/","2","-3","-","-"}));
    }

    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0)
            return 0;

        List<Integer> stack = new ArrayList<Integer>();
        for(String token : tokens) {
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int v2 = stack.remove(stack.size()-1);
                int v1 = stack.remove(stack.size()-1);
                if(token.equals("+"))
                    stack.add(v1+v2);
                if(token.equals("-"))
                    stack.add(v1-v2);
                if(token.equals("*"))
                    stack.add(v1*v2);
                else
                    stack.add(v1/v2);
            } else {
                stack.add(Integer.valueOf(token));
            }
        }

        return stack.remove(stack.size()-1);
    }
}
