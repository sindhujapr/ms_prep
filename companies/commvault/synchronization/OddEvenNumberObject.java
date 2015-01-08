package interview.commvault.synchronization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/*
 * This program prints odd and even numbers in alternate way
 */
public class OddEvenNumberObject {
	/* Change the below value if you want more numbers to be printed */
	private static final int max = 100000000;

	/* The file will be used by the two threads for output */
	private static final String outputFile = "OddEvenOutput.txt";

	/* Object used for synchronization between two threads */
	private static Object synchronizationObject = new Object();

	/*
	 * The below two variables are used for the case of lost "signal". See
	 * comment in OddNumberGenerator. Since the read/write of them are
	 * synchronized, they don't need to be "volatile".
	 */
	private static boolean isOddThreadNotified = false;
	private static boolean isEvenThreadNotified = false;

	static class OddNumberGenerator implements Runnable {
		private ByteBuffer bBuf;
		private int value = 1;

		public OddNumberGenerator(ByteBuffer bBuf) {
			this.bBuf = bBuf;
		}

		@Override
		public void run() {
			while (value < max) {
				if (value % 2 != 0) {
					bBuf.put(Integer.toString(value).getBytes());
					bBuf.put(Character.toString('\t').getBytes());

					/*
					 * Notify the other thread and set flag indicating the
					 * "signal" has been sent. It's important to do these in a
					 * synchronized block.
					 */
					synchronized (synchronizationObject) {
						synchronizationObject.notify();
						isEvenThreadNotified = true;
					}
				} else {
					try {
						/*
						 * The below programming paradigm is used to avoid
						 * deadlock when using wait/notify. If the other thread
						 * issues notify() while this thread isn't in waiting
						 * state, the "signal" will be lost. Thus it's necessary
						 * to introduce another variable (isOddThreadNotified)
						 * for this thread.
						 */
						synchronized (synchronizationObject) {
							while (!isOddThreadNotified)
								synchronizationObject.wait();

							/* Restore the value!! */
							isOddThreadNotified = false;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				value++;
			}
			System.out.println("Odd number generator finished");
		}
	}

	/* See comments in OddNumberGenerator */
	static class EvenNumberGenerator implements Runnable {
		private ByteBuffer bBuf;
		private int value = 1;

		public EvenNumberGenerator(ByteBuffer bBuf) {
			this.bBuf = bBuf;
		}

		@Override
		public void run() {
			while (value < max) {
				if (value % 2 == 0) {
					bBuf.put(Integer.toString(value).getBytes());
					bBuf.put(Character.toString('\n').getBytes());

					synchronized (synchronizationObject) {
						synchronizationObject.notify();
						isOddThreadNotified = true;
					}
				} else {
					try {
						synchronized (synchronizationObject) {
							while (!isEvenThreadNotified)
								synchronizationObject.wait();
							isEvenThreadNotified = false;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				value++;
			}
			System.out.println("Even number generator finished");
		}
	}

	/*
	 * If the variable max is even, the last line is "<max-1>\t". All other
	 * lines should be like "<odd number>\t<odd number + 1>". Otherwise, all
	 * lines should be like "<odd number>\t<odd number + 1>".
	 */
	public static boolean checkResult(String file) {
		FileChannel fc = null;

		try {
			fc = new FileInputStream(new File(outputFile)).getChannel();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

			int length = Integer.toString(max).length();
			/*
			 * Each line may keep an additional '\t' but not the '\n' at the end
			 * of each line
			 */
			byte[] bytes = new byte[2 * length + 1];

			/* The even number starts from 2, so 0 is an invalid number */
			int prevEven = 0;
			while (true) {
				Arrays.fill(bytes, (byte) 0x00);

				int i = 0;
				byte b;
				for (b = mbb.get(); i < 2 * length + 1 && b != '\n' && mbb.position() < mbb.limit(); i++, b = mbb
						.get()) {
					bytes[i] = b;
				}

				/* Now all bytes are in the array and we need to parse them */

				StringBuilder odd = new StringBuilder(length);
				int j = 0;
				while (bytes[j] != '\t' && j < i)
					odd.append((char) bytes[j++]);
				/* skip the '\t' */
				j++;

				int oddNum = Integer.parseInt(odd.toString());
				if (oddNum != prevEven + 1 && prevEven != 0) {
					System.err.println("Inconsistent with previous even number " + oddNum);
				}

				StringBuilder even = new StringBuilder(length);
				while (j < i)
					even.append((char) bytes[j++]);

				if (even.length() != 0) {
					int evenNum = Integer.parseInt(even.toString());
					if (oddNum + 1 != evenNum) {
						System.err.println("Problematic line " + oddNum);
						return false;
					}
				} else {
					System.out.println("Last line: " + oddNum);
				}

				if (mbb.position() >= mbb.limit())
					break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fc != null)
					fc.close();
			} catch (IOException e2) {
				System.err.println("Error closing file channel");
			}
		}

		return true;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		FileChannel fc = null;
		try {
			/* Allocate enough buffer based on the length of the maximum number */
			int length = Integer.toString(max).getBytes().length;
			/* Direct memory provides much better performance */
			ByteBuffer bBuf = ByteBuffer.allocateDirect(max * (length + 2));

			Thread oddThread = new Thread(new OddNumberGenerator(bBuf));
			Thread evenThread = new Thread(new EvenNumberGenerator(bBuf));
			oddThread.start();
			evenThread.start();

			/*
			 * Wait for the two threads to finish and then verify the result. We
			 * can also use CylicBarrier for thread synchronization.
			 */
			oddThread.join();
			evenThread.join();

			fc = new FileOutputStream(new File(outputFile)).getChannel();
			bBuf.flip();
			fc.write(bBuf);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fc != null)
					fc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		if (checkResult(outputFile))
			System.out.println("The two threads work as expected");
		else
			System.err.println("The two threads don't work as expected");

		System.out.println("total time: " + (System.currentTimeMillis() - start));
	}
}