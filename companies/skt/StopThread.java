package interview.skt;

import java.util.concurrent.TimeUnit;

/* Loop-invariant code motion - http://en.wikipedia.org/wiki/Loop-invariant_code_motion
 * http://en.wikipedia.org/wiki/Strength_reduction
 * http://en.wikipedia.org/wiki/Induction_variable
 */

// Try to run the application with/without "-server" mode, and compare results
public class StopThread {
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested)
                    i++;
                System.out.println(i);
            }
        });

        backgroundThread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        stopRequested = true;
    }
}