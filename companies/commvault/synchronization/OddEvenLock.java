package interview.commvault.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenLock {
    private static final int NUM = 1000000;
    
    private static final Lock lock = new ReentrantLock();
    // Two threads need two conditions
    private static final Condition oddCond = lock.newCondition();
    private static final Condition evenCond = lock.newCondition();
    private volatile static boolean isOdd = true;
    
    private static int value = 1;
    
    static class Odd implements Runnable {

        @Override
        public void run() {
            while(value < NUM) {
                try {
                    lock.lock();
                    
                    // Avoid signal stealer problem
                    while(!isOdd) {
                        oddCond.await();
                    }
                    
                    System.out.print(value++  + "\t");
                    
                    isOdd = false;
                    evenCond.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    
    static class Even implements Runnable {
        @Override
        public void run() {
            while(value <= NUM) {
                try {
                    lock.lock();

                    while(isOdd) {
                        evenCond.await();
                    }

                    System.out.println(value++);
                
                    isOdd = true;
                    oddCond.signal();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Odd());
        Thread t2 = new Thread(new Even());
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.currentTimeMillis()-start);
        
//      ExecutorService execService = Executors.newFixedThreadPool(2);
//      execService.submit(new Odd());
//      execService.submit(new Even());
//
//      execService.shutdown();
//      execService.awaitTermination(100000, TimeUnit.NANOSECONDS);
    }
}