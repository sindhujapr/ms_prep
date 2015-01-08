package interview.ms;

import java.util.Arrays;
import java.util.List;

/*
 * Given an equation (Ex."2*5+1+3*6" or "3/6-1*4+1") as a string,calculate the equation.
 * So "2*5+1" becomes 10+1=11. I used divide and conquer, thus "2*5+1" becomes "2*5" and "1"; 
 */
public class Equation {
    // Should we consider parentheses?
    public static void main(String[] args) throws Exception {
        
    }
    
    public static void equation(String expr) throws IllegalArgumentException {
        if(expr == null)
            System.out.println("null expression");

        if(!isValidExpression(expr))
            throw new IllegalArgumentException("Illegal argument" + expr);
        
        int value;
    }
    
    public static boolean isValidExpression(String expr) {
        List<Character> operators = Arrays.asList('+', '-', '*', '/');
        for(char c : expr.toCharArray()) {
            if(!operators.contains(c) && !Character.isDigit(c))
                return false;
        }
        
        return true;
    }
}
