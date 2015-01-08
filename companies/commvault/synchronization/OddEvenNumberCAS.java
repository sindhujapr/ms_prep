package interview.commvault.synchronization;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * The performance is twice better than OddEvenLock
 * 
 * By employing lock-free implementation, there is no need for
 * context switch. The waiting thread just spins around. This is
 * something like a spin-lock
 */
public class OddEvenNumberCAS {
	private static AtomicInteger cur = new AtomicInteger(1);
	private static int limit = 1000;
	
	/*
	 * This single implementation is enough for both Odd and Even
	 * generator. The only difference is, they have different 
	 * condition (value) to proceed.
	 */
	static class OddEven implements Runnable {
		private final int value;
		public OddEven(int value) {
			this.value = value;
		}

		@Override
		public void run() {
			while(cur.get() < limit) {
				while(cur.get() % 2 != value);
				
				System.out.print(cur.get());
				if(cur.get() % 10 == 0)
					System.out.println();
				else
					System.out.print("\t");

				cur.set(cur.get()+1);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new OddEven(1));
		Thread t2 = new Thread(new OddEven(0));
		long start = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(System.currentTimeMillis()-start);
	}
}