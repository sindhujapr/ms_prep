package interview.java;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CountDownLatch;

public class ThreadCreationTest {
  public static void main(String[] args) throws InterruptedException {
    final AtomicInteger threads_created = new AtomicInteger(0);

    while (true) {
      final CountDownLatch latch = new CountDownLatch(1);

      new Thread() {
        { start(); }
        
        public void run() {
          // Allow another thread to start
          latch.countDown();
          synchronized (this) {
            System.out.println("threads created: " + threads_created.incrementAndGet());
            try {
              // Leave the current thread in WAIT state
              wait();
              System.out.println("never reached");
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }
        }
        
      }; //new Thread() finishes
      // Wait for the latch to be releases
      latch.await();
//      System.out.println("wait finished");
    } //while
  }
}