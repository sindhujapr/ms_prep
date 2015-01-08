package javautils;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

enum Planet {
    MERCURY(3.3e+23, 2.43), VENUS(4.86 + 24, 6.25), EARTH(5.95 + 24, 6.37);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    Planet(double mass, double radius) {
    this.mass = mass;
    this.radius = radius;
    surfaceGravity = 0.11d;
    }

    public double mass() {
    return mass;
    }

    public double radius() {
    return radius;
    }

    public double surfaceGravity() {
    return surfaceGravity;
    }
}

enum Fruit {
    /*
     * these instances are static final.
     * we can associate any numbers and any types of values to these instances.
     */
    APPLE(3) {
    @Override
    void f() {}
    }, 
    PEAR(2) {
    @Override
    void f() {
    }
    }, 
    BANANA(4) {
    @Override
    void f() {
    }
    };

    private int kind;

    /*
     * since we are not allowed to create objects of this type, 
     * the constructor cannot be public or protected.
     */
    Fruit(int kind) {
    this.kind = kind;
    }

    public int kind() {
    return kind;
    }
    
    /*
     * for enum type, we can also define abstract method thus each enum instance
     * has to implement this method.
     */
    abstract void f();
}

/*
 * enum has already implemented Comparable interface. See Enum.java.
 */
enum Operation {
    PLUS("+") {
    double apply(double x, double y) { return x+y; }
    },
    MINUS("-") {
    double apply(double x, double y) { return x-y; }
    },
    TIMES("*") {
    double apply(double x, double y) { return x*y; }
    },
    DIVIDE("/") {
    double apply(double x, double y) { return x/y; }
    };
    private final String symbol;
    Operation(String symbol) {
    this.symbol = symbol;
    }
    @Override
    public String toString() { return symbol; }

    abstract double apply(double x, double y);
}

public class EnumTest {
    public static void main(String[] args) {
    /*
     * we should avoid using ordinal(). See Effective Java item #31.
     */
    for (Fruit f : Fruit.values())
        System.out.println(f.name() + "\t" + f.ordinal() + "\t" + f.kind());
    
    System.out.println("compare: " + Operation.PLUS.compareTo(Operation.MINUS));
    double x = 0.12;
    double y = 0.34;
    for(Operation op : Operation.values()) {
        System.out.format("%f %s %f = %f\n", x, op, y, op.apply(x, y));
    }
    }
}