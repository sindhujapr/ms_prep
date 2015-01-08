package concurrency;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadExecution {
    public static void main(String[] args) throws InterruptedException, IOException {
        new ThreadExecution().test1();
    }

    public void test1() throws InterruptedException {
        FutureTask<Integer> future = new FutureTask<Integer>(new Callable<Integer>() {
            public Integer call() throws IOException {
                return 10;
//              throw new IOException("io");
            }
        });
        
        ExecutorService execService = Executors.newFixedThreadPool(4);
        /*
         * Check Javadoc for ExecutorSerivce. Method submit() returns a Future<?> object,
         * whose method get() returns null upon successful completion. That means, any exception
         * cannot be thrown from its get() method. This is different from Callable.get().
         * 
         * If we construct FutureTask with a Callable instance, its get() method will also
         * throw exception, which behaves the same as Callable.get().
         * 
         * Thus the below f shouldn't be used to retrieve result. It's only an indication
         * whether the invocation is successful.
         */
        Future<?> f = execService.submit(future);
        
        execService.shutdown();
        try {
            Integer i = (Integer) f.get();
//          Integer i = (Integer) future.get();
            System.out.println(i);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        
        execService.awaitTermination(100, TimeUnit.MILLISECONDS);
    }

    public void test2() throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("another thread");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        /*
         * It's interesting that the below code doesn't create a new thread but only
         * uses the current thread to execute Runnable.run():
         * new Thread(r).run();
         * This has the same effect as:
         * r.run();
         * 
         * Check Thread.run() implementation for details.
         * 
         * To run the task in a separate thread, we need to use:
         * new Thread(r).start();
         */
        Thread t = new Thread(r);
        t.start();

        System.out.println("main thread");
        t.join();
    }
}