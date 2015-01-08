package interview.commvault.PrimeNumber;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierEdition {
    private static final int threadCnt = 2;
    private static final int N = 10;

    /*
     * we have to place these variables beyond the method. otherwise the 
     * threads will not be able to access them.
     */
    public volatile static int i = 0;
    public static boolean isPrremptive = false;
    public static boolean stop = false;
    public static boolean probablyPreemptive() throws InterruptedException {
	Thread t1 = new Thread() {
	    public void run() {
		while(!interrupted() && !stop) {
		    i += 2;
		    /*
		     * check if the other thread has chance to preempt this thread.
		     * if yes, then this thread is preempted.
		     */
		    if(i % 2 == 1) {
			isPrremptive = true;
			stop = true;
		    }
		}
	    }
	};
	
	Thread t2 = new Thread() {
	    public void run() {
		while(!isInterrupted() && !stop) {
		    i += 1;
		    if(i % 2 == 1) {
			isPrremptive = true;
			stop = true;
			System.out.println();
		    }
		}
	    }
	};
	
	t1.start();
	t2.start();

	// wait for a period of time
	t1.join(1000);
	t2.join(1000);
	return isPrremptive;
    }
    
    public static void main(String[] args) throws InterruptedException {
	System.out.println(probablyPreemptive());
//	ExecutorService executor = Executors.newCachedThreadPool();
//
//	final CyclicBarrier barrier = new CyclicBarrier(threadCnt);
//	executor.submit(new Runnable() {
//	    @Override
//	    public void run() {
//		int i = 1;
//		while(i < N) {
//		    System.out.print(i);
//		    
//		    try {
//			barrier.await();
//		    } catch (InterruptedException e) {
//			e.printStackTrace();
//		    } catch (BrokenBarrierException e) {
//			e.printStackTrace();
//		    }
//		    
//		    i += 2;
//		}
//	    }
//	});
//	
//	executor.submit(new Runnable() {
//	    @Override
//	    public void run() {
//		int i = 2;
//		while(i < N) {
//		    try {
//			barrier.await();
//		    } catch (InterruptedException e) {
//			e.printStackTrace();
//		    } catch (BrokenBarrierException e) {
//			e.printStackTrace();
//		    }
//		    
//		    System.out.println("\t" + i);
//		    i += 2;
//		    barrier.reset();
//		}
//	    }
//	});
//	
//	executor.shutdownNow();
//	
//	Thread t = new Thread(){
//	    public void run() {
//		System.out.println("hello");
//	    }
//	};
//	t.setPriority(Thread.MAX_PRIORITY);
//	t.start();
//	t.interrupt();
//	t.join();
    }
}