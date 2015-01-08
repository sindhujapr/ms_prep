package interview.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {
	private static ThreadLocal<Integer> tl = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Thread() {
			/* how to catch exception in this thread */
			public void run() {
				tl.set(100);
				while(tl.get() < 150) {
					System.out.println("thread local #2");
					tl.set(tl.get() + new Integer(2));
				}
				System.out.println("spawn thread " + tl.get());
			}
		});
		
//		ThreadLocal<Integer> tl1 = new ThreadLocal<Integer>();
		tl.set(1);
		while(tl.get() < 40) {
			System.out.println("thread local #1");
			Integer i = tl.get();
			tl.set(i + new Integer(1));
		}
		
		System.out.println("main thread " + tl.get());
//		executor.shutdown();
	}
}