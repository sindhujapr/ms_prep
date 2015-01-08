package careercup;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * Write a program to swap odd and even bits in an integer with as 
 * few instructions as possible (e.g., bit 0 and bit 1 are swapped,
 * bit 2 and bit 3 are swapped, and so on).
 */
public class SwapAdjcentBits {
    private static int value = 1000;
    
    public SwapAdjcentBits() {
        
    }
    
     class MyObject {
        int i = 10;
    }
     
     public void show(final int i) {
          new Runnable() {
            public void run() {
              System.out.println(i);
            }
          }.run();
        }
    
    public void f(Integer i) {
        i = 100;
        System.out.println(Integer.bitCount(i));
        System.out.println(i);
        
        BigInteger bi = new BigInteger("122");
        System.out.println(bi.bitLength());
        System.out.println(bi.bitCount());
        throw new RuntimeException();
    }
    
    public static void main(String[] args) {
        int bits = 0xffffffff;
        
        // How to manipulate bits in Java?
        // How to use enum in Java?
        
        SwapAdjcentBits instance = new SwapAdjcentBits();
        Integer i = 10;
        try {
            instance.f(i);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        System.out.println(i);
        
        instance.show(1);
    }
}