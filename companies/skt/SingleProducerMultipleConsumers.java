package interview.skt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* Sepmaphore edition */
class Producer implements Runnable {
	private final Random rand = new Random();
	public Producer() { System.out.println("Producer thread"); }

	public void run() {
		try {
			while(true) {
				// Must obtain the semaphore before updating the shared list
				SingleProducerMultipleConsumers.obtainSemaphore();
				while(SingleProducerMultipleConsumers.list.size() < 10) {
					Integer value = rand.nextInt();
					System.out.println(this + " produced " + value);
					SingleProducerMultipleConsumers.list.add(value);
				}
				SingleProducerMultipleConsumers.releaseSemaphore();
				/* One released the semaphore, we don't need to notify the consumers,
				 * because they are blocked by the semaphore
				 */
			}
		} catch(InterruptedException e) {
			System.out.println(e);
		}
	}

	public String toString() { return "producer"; }	
}

class Consumer implements Runnable {
	private int threadCnt;
	public Consumer(int i) {
		threadCnt = i;
		System.out.println("Consumer thread #" + i);
	}
	
	public void run() {
		try {
			while(true) {
				SingleProducerMultipleConsumers.obtainSemaphore();
				if(SingleProducerMultipleConsumers.list.size() == 10) {
					Integer i = SingleProducerMultipleConsumers.list.remove(0);
					System.out.print(this + " consumed " + i);
					if((i % 2) == 0 || (i % 3) == 0 || (i % 15) == 0)
						System.out.println(" matched!");
					else
						System.out.println();
				}
				SingleProducerMultipleConsumers.releaseSemaphore();
//				Thread.yield();
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public String toString() { return "consumer thread #" + threadCnt; }	
}

public class SingleProducerMultipleConsumers {
	/* volatile or concurrent list? */
	static List<Integer> list = new LinkedList<Integer>();
	private static int consumerCnt = 10;
	static Semaphore sema = new Semaphore(1);
	
	public static void obtainSemaphore() throws InterruptedException {
		sema.acquire();
	}
	public static void releaseSemaphore() throws InterruptedException {
		sema.release();
	}
	
	/*
	 * We have several choices to terminate threads:
	 * 1. Call System.exit(). In this case, daemon threads don't have chance to execute "finally"
	 * 2. Introduce a (volatile) flag for that thread and update this flag to false so that the thread can exit itself
	 * 3. Submit the task (Runnable) by ExecutorService.submit(). Then call cancel(true) on the returned Future<?>
	 * 3(contd). Check Interrupting.java and you will find not all threads can be cancelled (Future.cancel(true)), for
	 * example, IO blocked threads and threads that are waiting for synchronization lock.
	 * Be noted that executor.shutdown() or even executor.shutdownNow() doesn't necessarily terminate all threads
	 */
	public static void main(String[] args) throws InterruptedException {
		List<Future<?>> threads = new ArrayList<Future<?>>();

		ExecutorService executor = Executors.newFixedThreadPool(consumerCnt+1);
		threads.add(executor.submit(new Producer()));
//		executor.execute(new Producer());
		
		for (int i = 0; i < consumerCnt; i++) {
//			executor.execute(new Consumer(i));
			threads.add(executor.submit(new Consumer(i)));
		}
		
//		executor.shutdown();
		TimeUnit.SECONDS.sleep(3);
		for(Future<?> future : threads) {
			boolean canceld = future.cancel(true);
		}
	}
}