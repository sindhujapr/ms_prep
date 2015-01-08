package javautils;

public class Exponents {
    public static void main(String[] args) {
    float expFloat1 = 1.39e-43f;
    float expFloat2 = 1.39e-43f;
    System.out.println(expFloat1 == expFloat2);
    
    double expDouble = 47e47d;
    System.out.println(expDouble);
    
    /*
     * exponent is treated as double. so we need to append 'f' to indicate
     * this is a float number.
     */
    float f = 1e-43f;
    System.out.println(f);
    }
}