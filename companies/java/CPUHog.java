package interview.java;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Main class for control test. */
public class CPUHog {
   public static void main(String[] args) {
       ExecutorService executor = Executors.newCachedThreadPool();
       
       executor.submit(new Slouch());
       executor.submit(new WorkingStiff());
       executor.submit(new Workaholic());
   }
}

/** Low CPU utilization thread. */
class Slouch extends Thread {
  public Slouch() { super("Slouch"); }
  public void run() { BusyWork.slouch(); }
}

/** Medium CPU utilization thread. */
class WorkingStiff extends Thread {
  public WorkingStiff() { super("WorkingStiff"); }
  public void run() { BusyWork.workNormally(); }
}

/** High CPU utilization thread. */
class Workaholic extends Thread {
  public Workaholic() { super("Workaholic"); }
  public void run() { BusyWork.workTillYouDrop(); }
}

class BusyWork {
  public static int callCount = 0;

  public static void slouch() {
    int SLEEP_INTERVAL = 1000;
    computeAndSleepLoop(SLEEP_INTERVAL); 
  }

  public static void workNormally() {
    int SLEEP_INTERVAL = 100;
    computeAndSleepLoop(SLEEP_INTERVAL); 
  }

  public static void workTillYouDrop() {
    int SLEEP_INTERVAL = 10;
    computeAndSleepLoop(SLEEP_INTERVAL); 
  }
  
  private static void computeAndSleepLoop(int sleepInterval) {
     int MAX_CALLS = 10000;
     while (callCount < MAX_CALLS) { computeAndSleep(sleepInterval); }
  } 

  private static void computeAndSleep(int sleepInterval) {
        int    COMPUTATIONS = 1000;
        double result;
        
        callCount++;
        for (int i = 0; i < COMPUTATIONS; i++) {
           result = Math.atan(callCount * Math.random());         
        }

        try {
           Thread.currentThread().sleep(sleepInterval);
        } catch (InterruptedException ie) {
           // Do nothing.
        }
  } // End computeAndSleep.
} // End BusyWork.