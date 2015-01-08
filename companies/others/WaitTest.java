package fk;

import java.util.*;

/**
 * Created by qingcunz on 11/12/14.
 */
public class WaitTest {
//    public Object sync = new Object();

    public synchronized void f() {
        System.out.println("f1");

        try {
                wait();
        } catch (InterruptedException e) {
            System.out.println("f interrupted");
        }
        System.out.println("f2");
    }

    public synchronized void g() {
        System.out.println("g1");

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("g interrupted");
        }

        System.out.println("g2");

            notifyAll();
    }

    public static void main(String[] args) {
//        final WaitTest instance = new WaitTest();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                instance.f();
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                instance.g();
//            }
//        }).start();

//        System.out.println(dist(new int[] {3,3,3,5,6,4}));
    }

    public static int dist(int[] A) {
        int max1 = A[0];
        int max = 2 * A[0];
        for(int i = 1; i < A.length; i++) {
            max = Math.max(Math.max(max, 2*A[i]), A[i]+i+max1);

            max1 = Math.max(max1, A[i]-i);
        }
        return max;
    }
}