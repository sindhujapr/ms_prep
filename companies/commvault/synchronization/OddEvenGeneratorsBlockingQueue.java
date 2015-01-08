package interview.commvault.synchronization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class OddEvenGeneratorsBlockingQueue {
	private static BlockingQueue<Integer> oddNumbers = new LinkedBlockingDeque<Integer>(1);
	private static BlockingQueue<Integer> evenNumbers = new LinkedBlockingDeque<Integer>(1);

	static class OddGenerator implements Runnable {
		private ByteBuffer bb;
		private final Integer limit;
		public OddGenerator(ByteBuffer bb, Integer limit) {
			this.bb = bb;
			this.limit = limit;
		}

		@Override
		public void run() {
			Integer value = 2;
			
			while(!Thread.interrupted() && value < limit) {
				try {
					Integer i = oddNumbers.take();
					System.out.println(i);
					bb.put(Integer.toString(i).getBytes());
					bb.put(Character.toString('\n').getBytes());

					boolean successful = evenNumbers.offer(value);
					if(!successful)
						System.out.println("failed to add next number");
					
					value += 2;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			evenNumbers.offer(limit);
			System.out.println("odd thread exits");
		}		
	}
	
	static class EvenGenerator implements Runnable {
		private ByteBuffer bb;
		private final Integer limit;
		public EvenGenerator(ByteBuffer bb, Integer limit) {
			this.bb = bb;
			this.limit = limit;
		}

		@Override
		public void run() {
			Integer value = 1;

			while(!Thread.interrupted() && value < limit-1) {
				try {
					boolean successful = oddNumbers.offer(value);
					if(!successful)
						System.out.println("failed to add next number");
					
					value += 2;

					Integer i = evenNumbers.take();
					System.out.println(i);
					bb.put(Integer.toString(i).getBytes());
					bb.put(Character.toString('\n').getBytes());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			oddNumbers.offer(limit);
			System.out.println("even thread exits");
		}
	}

	public static void checkResult(String file) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File(file)));
			int previous = 0;
			String line;
			while((line = br.readLine()) != null) {
				int current = Integer.parseInt(line);
				if(current != previous+1)
					System.out.println("error " + current);
				previous = current;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("finished checking");
	}
	
	private static final Integer count = 10000;
	private static final String output = "blocking.txt";

	public static void main(String[] args) throws InterruptedException {
		ByteBuffer bb = ByteBuffer.allocate(count*8);

		Thread oddThread = new Thread(new OddGenerator(bb, count));
		Thread evenThread = new Thread(new EvenGenerator(bb, count));
		
		oddThread.start();
		evenThread.start();
		
		oddThread.join();
		evenThread.join();
		
		FileChannel fc = null;
		try {
			fc = new FileOutputStream(new File(output)).getChannel();
			bb.flip();
			fc.write(bb);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fc != null)
					fc.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		checkResult(output);
	}
}
