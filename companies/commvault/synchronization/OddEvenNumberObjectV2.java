package interview.commvault.synchronization;

/*
 * slightly improved based on OddEvenNumberObject
 * 
 * Semaphore isn't suitable for this problem because we want to control 
 * the way the two threads run. That is, they should run in an alternative
 * way. While Semaphore has no control over this, it's only use to control
 * the way resources are allocated among threads.
 */
public class OddEvenNumberObjectV2 {
    private static final int max = 200;

    private static int value = 1;
    private static Object synObj = new Object();

    // Must be volatile for the below performance optimization
    private static volatile boolean isOdd = true;

    static class OddNumberGenerator implements Runnable {
        @Override
        public void run() {
            while (value < max) {
                System.out.print(value++ + "\t");
                
                synchronized (synObj) {
                    isOdd = false;
                    synObj.notify();
                }

                // performance optimization to avoid synchronization
                if(!isOdd) {
                    synchronized (synObj) {
                        try {
                            while (!isOdd)
                                synObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class EvenNumberGenerator implements Runnable {
        @Override
        public void run() {
            while (value <= max) {
                // performance optimization
                if(isOdd) {
                    synchronized (synObj) {
                        try {
                            while (isOdd)
                                synObj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                System.out.println(value++);
                
                synchronized (synObj) {
                    isOdd = true;
                    synObj.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        try {
            Thread oddThread = new Thread(new OddNumberGenerator());
            Thread evenThread = new Thread(new EvenNumberGenerator());
            oddThread.start();
            evenThread.start();

            oddThread.join();
            evenThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("total time: " + (System.currentTimeMillis() - start));
    }
}