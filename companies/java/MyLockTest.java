package interview.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* Exception ! */
public class MyLockTest {
    private static Lock lock1 = new ReentrantLock();
    private static Lock lock2 = new ReentrantLock();
    private static Lock lock3 = new ReentrantLock();
    
    private static final CountDownLatch latch = new CountDownLatch(1);

    /*static {
        lock1.lock();
        lock2.lock();
        lock3.lock();
    }*/

    public static void main(String[] args) throws IOException {
//      Process p = new ProcessBuilder("javap -c ../bin/com.emc.zhous2.ch18.OSExecuteDemo".split(" ")).start();
        Process p = new ProcessBuilder("cmd /c dir c:\\".split(" ")).start();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String str = null;
        while((str = br.readLine()) != null)
            System.out.println(str);
                
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("thread 1");
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }   
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    latch.await();
                    System.out.println("thread 2");
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }   
        }).start();
        
/*      new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock1.unlock();
                    System.out.println("thread 1");
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                } {
                    lock2.unlock();
                }
            }   
        }).start();
        
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock2.lock();                   
                    System.out.println("thread 2");
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    lock2.unlock();
                    lock3.unlock();
                }
            }   
        }).start();
    
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    lock3.lock();
                    System.out.println("thread 3");
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                } finally {
                    lock3.unlock();
                }
            }   
        }).start();
*/
    }
}
