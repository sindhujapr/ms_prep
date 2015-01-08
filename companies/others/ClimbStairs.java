package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by qingcunz on 11/16/14.
 */
public class ClimbStairs {
    private static Object lock = new Object();

    public static void f() {
        synchronized (lock) {
            lock = new Object();

            System.out.println("entered f() and updated object reference");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            System.out.println("exiting f()");
        }
    }

    public static void g() {
        try {
            // wait for a while so that the other thread can get the lock
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        synchronized (lock) {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                f();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                g();
            }
        }).start();
    }

    // O(lgn)
    public int climbStairs(int n) {
        if(n == 1 || n == 2 || n == 3)
            return n;

        int low, high;
        if(n % 2 == 0){
            low = n / 2;
            high = n / 2;
        } else {
            low = (n-1)/2;
            high = (n+1)/2;
        }

        return climbStairs(low) * climbStairs(high) + climbStairs(low-1) * climbStairs(high-1);
    }

    // O(n);
    public int climb(int n) {
        if(n == 1 || n == 0)
            return 1;

        int hist[] = new int[2];
        hist[0] = hist[1] = 1;
        for(int i = 2; i <= n; i++) {
            int temp = hist[0] + hist[1];
            hist[0] = hist[1];
            hist[1] = temp;
        }

        return hist[1];
    }
}
