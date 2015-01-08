package javaspecialists;

//class CacheTester {
//  private final int ARR_SIZE = 1 * 1024 * 1024;
//  private final int[] arr = new int[ARR_SIZE];
//
//  private void doLoop1() {
//      for (int i = 0; i < arr.length; i += 16)
//          arr[i]++;
//  }
//
//  private void doLoop2() {
//      for (int i = 0; i < arr.length; i++)
//          arr[i]++;
//  }
//
//  public void run() {
//      // warm up
//      for (int i = 0; i < 10000; i++) {
//          doLoop1();
//          doLoop2();
//      }
//      
//      for (int i = 0; i < 100; i++) {
//          long t0 = System.nanoTime();
//          doLoop1();
//      
//          long t1 = System.nanoTime();
//          doLoop2();
//          long t2 = System.nanoTime();
//          
//          long el = t1 - t0;
//          long el2 = t2 - t1;
//          System.out.println("Loop1: " + el + " nanos ; Loop2: " + el2);
//      }
//  }
//}

class CacheTester {
    private final int ARR_SIZE = 1 * 1024 * 1024;
    private final int[] arr = new int[ARR_SIZE];
    private static final int REPEATS = 1000;

    private void doLoop1() {
    for (int i = 0; i < arr.length; i += 16)
        arr[i]++;
    }

    private void doLoop2() {
    for (int i = 0; i < arr.length; i++)
        arr[i]++;
    }

    public void run() throws InterruptedException {
    for (int i = 0; i < 10000; i++) {
        doLoop1();
        doLoop2();
    }

    Thread.sleep(1000); // allow the hotspot compiler to work
    System.out.println("Loop1,Loop2");

    for (int i = 0; i < 100; i++) {
        long t0 = System.currentTimeMillis();
        for (int j = 0; j < REPEATS; j++)
        doLoop1();
        long t1 = System.currentTimeMillis();
        for (int j = 0; j < REPEATS; j++)
        doLoop2();
        long t2 = System.currentTimeMillis();
        long el = t1 - t0;
        long el2 = t2 - t1;
        System.out.println(el + "," + el2);
    }
    }

    public static void main(String[] args) throws InterruptedException {
    CacheTester ct = new CacheTester();
    ct.run();
    }
}

public class issue204 {
    public static void main(String[] args) throws InterruptedException {
    CacheTester ct = new CacheTester();
    ct.run();
    }
}