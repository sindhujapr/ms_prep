package javautils;

import miscellaneous.util.concurrent.ThreadLocalRandom;

public class Randoms {
    public static void testThreadLocalRandom() {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    System.out.println(rand.nextInt());
    System.out.println(rand.nextInt(100));
    System.out.println(rand.nextInt(100, 1000));
    }
    
    public static void testMathRandom() {
    for (int i = 0; i < 10; i++) {
        System.out.println(Math.random());
    }
    }
    
    public static void main(String[] args) {
    testThreadLocalRandom();
    testMathRandom();
    }
}