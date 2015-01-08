package interview.commvault.PrimeNumber;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VolatileTest {
    private int i = 0;
    public void set() {
        i++;
    }
    
    class InnerThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                if(i != 0)
                    System.out.println("changed " + i);
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        VolatileTest instance = new VolatileTest();

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(instance.new InnerThread());

        while(true) {
            instance.set();
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}