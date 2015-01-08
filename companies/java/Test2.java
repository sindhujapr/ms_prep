package interview.java;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Test3 {
    public void f() { System.out.println("3"); }
}

public class Test2 extends Test3 implements Runnable {
    private int count = 1;
    
    public synchronized void doSomething() {
        for (int i = 0; i < 10; i++) {
            System.out.println(count++);
        }
    }
    
    @Override
    public synchronized void f() {
        System.out.println("2");
    }
    
    public void run() {
        doSomething();
    }
    
    public static void main(String[] args) {
        Test2 demo1 = new Test2();
        Test2 demo2 = new Test2();
        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);
        t1.start();
        t2.start();
        
        int $34 = 10;
        System.out.println($34);
    }
    
    private String fn = "c:\\hello";
//  PrintWriter wr = new BufferedWriter(new PrintWriter(new FileWriter(fn)));
//  OutputStreamWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(fn)));
    
}
