package interview.commvault.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenNumberCondition {
    private static final int max = 2000;

    private static final Lock lock = new ReentrantLock();
    private static final Condition oddCond = lock.newCondition();
    private static final Condition evenCond = lock.newCondition();

    /*
     * No need to be volatile since we only update them within lock!
     */
    private static boolean isOddSignaled = false;
    private static boolean isEvenSignaled = false;

    static class OddNumberGenerator implements Runnable {
    private int value = 1;

    @Override
    public void run() {
        lock.lock();
        try {
        while (value <= max - 1) {
            while (!isOddSignaled)
            oddCond.await();
            isOddSignaled = false;

            System.err.print(value + "\t");
            value += 2;

            evenCond.signal();
            isEvenSignaled = true;
        }
        } catch (InterruptedException e) {
        e.printStackTrace();
        } finally {
        lock.unlock();
        }
    }
    }

    static class EvenNumberGenerator implements Runnable {
    private int value = 2;

    @Override
    public void run() {
        lock.lock();
        try {
        while (value <= max) {
            /*
             * No additional synchronization is needed for the below two
             * calls since we have lock in place!
             */
            oddCond.signal();
            /*
             * Something I cannot understand here: if the above signal()
             * call returns, the Odd thread will also return from
             * await(). So at this moment there seems to be two threads
             * holding the lock, which seems impossible! In that case,
             * both threads will have chance updating isOddSignal flag.
             */
            isOddSignaled = true;

            /*
             * Avoid spurious wakeup
             */
            while (!isEvenSignaled)
            evenCond.await();
            isEvenSignaled = false;

            System.err.println(value);
            value += 2;
        }
        } catch (InterruptedException e) {
        e.printStackTrace();
        } finally {
        lock.unlock();
        }
    }
    }

    public static void main(String[] args) throws InterruptedException {
    Thread oddThread = new Thread(new OddNumberGenerator());
    Thread evenThread = new Thread(new EvenNumberGenerator());
    oddThread.start();
    evenThread.start();

    oddThread.join();
    evenThread.join();

    System.out.println("Finished");
    }
}