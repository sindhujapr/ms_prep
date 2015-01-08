package interview.commvault.synchronization;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * http://www.javaspecialists.eu/archive/Issue206.html
 * http://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Executor.html
 * Here is how it works. Let's say that SubmitterThread passes in Runnables R1, R2, R3 and R4, 
 * one after the other. When R1 is passed in, it is wrapped with a new Runnable that will call 
 * the R1.run() method and then allow the next Runnable in the tasks queue to be executed. 
 * Let's call the new Runnable R1*. Since there is no other Runnable currently active 
 * (thus active==null), the SubmitterThread also calls scheduleNext(), which pulls R1* out of 
 * the queue and submits it to the wrapped Executor. The SubmitterThread can now call execute() 
 * with R2, R3 and R4, which will wrap these Runnables also with our special Runnable and submit 
 * them to our tasks queue.
 */
public class SerialExecutor implements Executor {
    private final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
    private final Executor executor;
    private Runnable active;

    public SerialExecutor(Executor executor) {
    this.executor = executor;
    }

    /*
     * When this method is called with a Runnable parameter: 1) The task will be
     * encapsulated in another new task, which will be added to the queue 2) The
     * new task will run the child task and finally schedule the next task in
     * the queue. 3) If the new task is the only task (head) in the queue, it
     * will be scheduled
     */
    public synchronized void execute(final Runnable r) {
    tasks.add(new Runnable() {
        public void run() {
        try {
            /*
             * The child task will run in the same thread pool with the
             * parent thread! But, the child task will not be executed
             * until the parent task be executed
             */
            r.run();
        } finally {
            // This is executed in the new task
            scheduleNext();
        }
        }
    });

    if (active == null) {
        scheduleNext();
    }
    }

    protected synchronized void scheduleNext() {
    if ((active = tasks.poll()) != null) {
        executor.execute(active);
    }
    }
}