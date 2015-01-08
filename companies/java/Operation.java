package interview.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public enum Operation {
    /*
     * @param 
     * @throws
     * @since 
     */
    PLUS("+") {
        double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        double apply(double x, double y) { return x / y; }
    };
    
    private final String symbol;
    Operation(String symbol) { this.symbol = symbol; }
    
    @Override
    public String toString() { return symbol; }
    abstract double apply(double x, double y);
    
    public static void main(String[] args) {
        double x = Double.parseDouble("10.0");
        double y = Double.parseDouble("10.2");
        for(Operation op : Operation.values())
            System.out.println(op.apply(x, y));
        
        Collections.synchronizedList(new LinkedList<String>());
        ConcurrentHashMap<Integer, String> hashMap = new ConcurrentHashMap<Integer, String>();
    }
}