package interview.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	private Semaphore semaphore = new Semaphore(2);
	private static ExecutorService executor = Executors.newCachedThreadPool();
	
	public void runThread1() {
		executor.execute(new Thread() {
			public void run() {
				try {
					while(true) {
						semaphore.acquire(2);
						System.out.println("thread 1");
						System.out.println("thread 1");
						Thread.sleep(50);
						semaphore.release(2);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void runThread2() {
		executor.execute(new Thread() {
			public void run() {
				try {
					while(true) {
						semaphore.acquire(2);
						System.out.println("thread 2");
						System.out.println("thread 2");
						Thread.sleep(50);
						semaphore.release(2);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void main(String[] args) throws InterruptedException {
		SemaphoreTest t = new SemaphoreTest();
		
		t.runThread1();
		t.runThread2();
		
		Thread.sleep(500);
//		executor.shutdown();
		executor.shutdownNow();
		
//		Thread.currentThread().join();
	}
}
