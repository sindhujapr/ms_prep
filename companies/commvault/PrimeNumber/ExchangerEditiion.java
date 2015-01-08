package interview.commvault.PrimeNumber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangerEditiion {
//    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
//	final CountDownLatch ready = new CountDownLatch(concurrency);
//	final CountDownLatch start = new CountDownLatch(1);
//	final CountDownLatch done = new CountDownLatch(concurrency);
//	for (int i = 0; i < concurrency; i++) {
//	    executor.execute(new Runnable() {
//
//		@Override
//		public void run() {
//		    ready.countDown();
//		    try {
//			start.await();
//			action.run();
//		    } catch (InterruptedException e) {
//			Thread.currentThread().interrupt();
//		    } finally {
//			done.countDown();
//		    }
//		}
//		
//	    });
//	}
//	
//	ready.await();
//	long startNanos = System.nanoTime();
//	start.countDown();
//	done.await();
//	return System.nanoTime() - startNanos;
//    }
//    
//    public static void main(String[] args) throws InterruptedException {
//	ExecutorService executor = Executors.newFixedThreadPool(10);
//	time(executor, 2, new Runnable() {
//	    private final AtomicInteger i = new AtomicInteger(1);
//	    @Override
//	    public void run() {
//		System.out.println("hello");
//	    }
//	});
//	executor.shutdownNow();
//    }
    
    public static void main(String[] args) {
	
    }
}