package interview.commvault.synchronization;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import miscellaneous.util.concurrent.Phaser;
import miscellaneous.util.concurrent.ThreadLocalRandom;

public class SerialExecutorExample {
    private static final int UPTO = 10;

    public static void main(String[] args) {
	/*
	 * Difference between Executor and ExecutorService: 1) Executor can only
	 * execute Runnable but not Callable 2) Executor doesn't have
	 * shutdown*() methods
	 */
	ExecutorService cached = Executors.newCachedThreadPool();

	test(new SerialExecutor(cached));
	test(cached);

	cached.shutdown();
    }

    private static void test(Executor executor) {
	final List<Integer> call_sequence = new ArrayList<>();
	final Phaser phaser = new Phaser(1);

	for (int i = 0; i < UPTO; i++) {
	    phaser.register();

	    final int tempI = i;
	    executor.execute(new Runnable() {
		@Override
		public void run() {
		    try {
			TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current()
				.nextInt(2, 10));
		    } catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		    }
		    call_sequence.add(tempI);
		    phaser.arrive();
		}
	    });
	}

	phaser.arriveAndAwaitAdvance();
	System.out.println(executor.toString() + ": " + call_sequence);
    }
}