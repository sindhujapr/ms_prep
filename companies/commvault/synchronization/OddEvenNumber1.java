package interview.commvault.synchronization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/*
 * This program prints odd and even numbers in alternate way
 */
public class OddEvenNumber1 {
    /* Change the below value if you want more numbers to be printed */
    private static final int max = 100000;

    /* The file will used by the two threads for output */
    private static final String outputFile = "OddEvenOutput.txt";

    /* Object used for synchronization between two threads */
    private static Object synchronizationObject = new Object();

    /*
     * The below two variables are used for the case of lost "signal". See
     * comment in OddNumberGenerator. Since the read/write of there are
     * synchronized, they don't need to be "volatile".
     */
    private static boolean isOddThreadNotified = false;
    private static boolean isEvenThreadNotified = false;

    static class OddNumberGenerator implements Runnable {
	private PrintStream printStream;
	private int value = 1;

	public OddNumberGenerator(PrintStream printStream) {
	    this.printStream = printStream;
	}

	@Override
	public void run() {
	    while (value < max) {
		if (value % 2 != 0) {
		    printStream.print(value + "\t");

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
			    /* avoid spurious wakeup in Linux */
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
	private PrintStream printStream;
	private int value = 1;

	public EvenNumberGenerator(PrintStream printStream) {
	    this.printStream = printStream;
	}

	@Override
	public void run() {
	    while (value < max) {
		if (value % 2 == 0) {
		    printStream.println(value);

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
     * If the variable max is even, the last line is "Odd <max-1>\t". All other
     * lines should be like "Odd <odd number>\tEven <odd number + 1>".
     * Otherwise, all lines should be like
     * "Odd <odd number>\tEven <odd number + 1>".
     */
    public static boolean checkResult(String file) {
	BufferedReader bufReader = null;

	try {
	    bufReader = new BufferedReader(new FileReader(new File(file)));

	    String line;
	    /*
	     * If the last output is an odd, then it should has the below
	     * pattern
	     */
	    String possibleLastLine = new Integer(max - 1).toString() + "\t";

	    while ((line = bufReader.readLine()) != null) {
		/* Last line */
		if (line.equals(possibleLastLine))
		    return true;

		/*
		 * Make sure each line contains an odd number followed by an
		 * adjacent even number
		 */
		if (line.matches("[0-9]*\t[0-9]*")) {
		    int index = line.indexOf('\t');
		    int odd = Integer.parseInt(line.substring(0, index));

		    int even = Integer.parseInt(line.substring(index + 1));
		    if (odd + 1 != even) {
			System.out.println("Unexpected numbers: " + odd
				+ " != " + even);
			return false;
		    }
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    try {
		if (bufReader != null)
		    bufReader.close();
	    } catch (IOException e2) {
		System.err.println("Error closing buffer reader");
	    }
	}

	return true;
    }

    public static void main(String[] args) {
	PrintStream printStream = null;

	long start = System.currentTimeMillis();

	try {
	    /* Enable auto flush to prevent the output out of order */
	    printStream = new PrintStream(new FileOutputStream(new File(
		    outputFile)), true);

	    Thread oddThread = new Thread(new OddNumberGenerator(printStream));
	    Thread evenThread = new Thread(new EvenNumberGenerator(printStream));
	    oddThread.start();
	    evenThread.start();

	    /* Wait for the two threads to finish and then verify the result */
	    oddThread.join();
	    evenThread.join();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    if (printStream != null)
		printStream.close();
	}

	if (checkResult(outputFile))
	    System.out.println("The two threads work as expected");
	else
	    System.err.println("The two threads don't work as expected");

	System.out.println("Total: " + (System.currentTimeMillis() - start));
    }
}