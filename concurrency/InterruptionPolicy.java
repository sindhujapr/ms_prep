package concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task {

}

/*
 * See Java Concurrency in Practice section 7.1.5
 * 
 * In most cases, task and the thread who execute it should have different
 * interruption policies. That means, the task shouldn't swallow the interruption
 * to the running thread. Instead, the interrupt status should be preserved and
 * then thrown back to the thread owner.
 * 
 * Even, when we interrupt a running thread, we don't know which task it's running
 * for, especially there are heterogeneous tasks (which share the same thread pool).
 */

public class InterruptionPolicy {
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                    // fall through and retry
                }
            }
        } finally {
            if (interrupted)
                Thread.currentThread().interrupt();
        }
    }
    
    private static ExecutorService taskExec = Executors.newCachedThreadPool();
    public static void timedRun(Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        
        try {
            task.get(timeout, unit);
        } catch(TimeoutException te) {
            // task will be cancelled below
        } catch(ExecutionException ee) {
            throw new InterruptedException();
        } finally {
            /*
             * Only cancel the task execution but not the thread.
             * No effect if task already completed
             */
            task.cancel(true);
        }
    }
}