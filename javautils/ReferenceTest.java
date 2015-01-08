package javautils;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReferenceTest {
    static ReferenceQueue<Object[]> refQue = new ReferenceQueue<>();
    static boolean stopped = false;
    
    public static void main(String[] args) {

    ExecutorService executor = Executors.newCachedThreadPool();
    executor.execute(new Runnable() {
        @Override
        public void run() {
        while(true) {
            @SuppressWarnings("unchecked")
            Reference<Object[]> ref1 = null;
            System.out.println("start...");
            try {
            ref1 = (Reference<Object[]>) refQue.remove();
            } catch (InterruptedException e) {
            e.printStackTrace();
            }
            System.out.println("released: " + ref1);
            stopped = true;
            break;
        }
        }
    });

    while (!stopped) {
        Reference<Object[]> ref = new PhantomReference<>(
            new Object[100 * 1024 * 1024], refQue);
        System.out.println("created: " + ref);
    }
    }
}
