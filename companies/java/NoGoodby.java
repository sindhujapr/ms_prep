package interview.java;

public class NoGoodby {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {
			public void run() {
				System.out.println("goodbye");
			}
		});

		/*
		 * System.exit() will stop all threads and won't give finally clause a chance to finish
		 * But the registered hooks will be executed before stopping VM.
		 */
		try {
			System.out.println("hello world");
			System.exit(0);
		} finally {
			System.out.println("goodbye world");
		}
		
	}
}