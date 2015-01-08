package interview.skt;

/* CountDownLatch edition */

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* Lock edition */
public class SingleProducerMultipleConsumers3 {
    /* List that will be used by producer to keep generated numbers and consumers to retrieve numbers */
    private static List<Integer> list = new LinkedList<Integer>();
    private static int consumerCnt = 10;
    private static CountDownLatch latch = new CountDownLatch(1);
    private static PrintWriter out = null;

    static {
        //out = new PrintWriter(new FileOutputStream(new File("C:/playground/test2.txt")), true);
        out = new PrintWriter(new BufferedOutputStream(System.out), true);
    }

    // Nested class
    static class Producer3 implements Runnable {
        private final Random rand = new Random();
        public Producer3() { out.println("Producer thread"); }

        public void run() {
            while(true) {
                try {
                    latch.await();
                    while(list.size() < 10) {
                        Integer value = rand.nextInt();
                        list.add(value);
                        out.println(this + " produced " + value + " " + list.size());
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }
        }
        
        public String toString() { return "producer"; } 
    }

    static class Consumer3 implements Runnable {
        private static int taskCnt = 0;
        private final int id = taskCnt++;
        static ThreadLocal<Integer> hitCnts = new ThreadLocal<Integer>();
        public Consumer3() {
            out.println("Consumer thread #" + id);
        }
        
        public void run() {
            /* Thread local must be initialized in the run() method.
             * If we initialize it in the constructor, the value will not be visible in run()
             * and results in NPE when trying to issue method call on the variable
             */
            hitCnts.set(new Integer(0));

            while(true) {
                try {
                    latch.await();
                    if(list.size() == 10) {
                        Integer i = list.remove(0);
                        out.print(this + " consumed " + i);
                        hitCnts.set(hitCnts.get() + 1);

                        if((i % 2) == 0 || (i % 3) == 0 || (i % 15) == 0)
                            out.println(" matched! " + hitCnts.get());
                        else
                            out.println(" " + hitCnts.get());
                        
                    }
                } catch(InterruptedException e){ 
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            }
        }
        public String toString() { return "consumer thread #" + id; }
    }
    
    /* This thread will be used to display statistics of the consumer threads */
    static class HookThread extends Thread {
        @Override
        public void run() {
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(consumerCnt+1);
        executor.execute(new Producer3());
        
        for (int i = 0; i < consumerCnt; i++) {
            executor.execute(new Consumer3());
        }
        
//      TimeUnit.SECONDS.sleep(2);
//      System.exit(0);
//      executor.shutdownNow();
    }
}