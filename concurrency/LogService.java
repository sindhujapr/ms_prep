package concurrency;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * Java Concurrency in Practice section 7.2.1
 * 
 * Multiple producers and single consumer using BlockingQueue
 * 
 * Another implementation would be using ExecutorSerivce to manage a single-thread
 * thread pool, which makes sure at any given time there is only one writer thread.
 * Thus we don't need to manage the thread pool and are be able to take advantage 
 * of the shutdown() alike methods.
 * 
 */
public class LogService {
    public static void main(String[] args) {
        ExecutorService exeService = Executors.newCachedThreadPool();
        exeService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(2);
                    int i = 1;
                    // test whether BlockingQueue.put() is interruptible
                    while(true)
                        queue.put(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
        });
        
        exeService.shutdownNow();
    }
    
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final Writer writer;
    
    /*
     * Can we make isShutdown volatile and reservations atomic
     * so that we can remove "synchronized"?
     */
    private boolean isShutdown;
    private int reservations;

    private static final int CAPACITY = 100;
    
    public LogService(Writer writer) {
        queue = new LinkedBlockingQueue<String>(CAPACITY);
        this.writer = writer;
        loggerThread = new LoggerThread(); 
    }
    
    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }

        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException();
            ++reservations;
        }

        /*
         * Though write() should be synchronized, we shouldn't include the below
         * line in the synchronized block since it may block and thus other threads
         * cannot make any progress.
         */
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (this) {
                            if (isShutdown && reservations == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (this) {
                            --reservations;
                        }
                        writer.write(msg);
                    } catch (InterruptedException e) {
                        /* retry */
                    } catch (IOException e) {
                        // TODO
                    }
                }
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}