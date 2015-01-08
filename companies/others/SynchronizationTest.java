package fk;

/**
 * Created by qingcunz on 11/20/14.
 */
public class SynchronizationTest {
    private static Object lock = new Object();

    public static void f() {
        synchronized (lock) {
            lock = new Object();

            System.out.println("entered f() and updated object reference");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            System.out.println("exiting f()");
        }
    }

    public static void g() {
        try {
            // wait for a while so that the other thread can get the lock
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

        synchronized (lock) {
            System.out.println("hello");
        }
    }

    private static class DeviceInfo {
        private int v1 = 0;
        private DeviceInfo() {}
        private static DeviceInfo INSTANCE = new DeviceInfo();
        public static DeviceInfo getInstance() {
            return INSTANCE;
        }

        public synchronized void setf() {
            v1 = 10;
        }
    }

    private DeviceInfo deviceInfo = DeviceInfo.getInstance();
    public void func1() {
        synchronized (deviceInfo) {
            deviceInfo.setf();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("func1");
        }
    }

    public void func2() {
        synchronized (deviceInfo) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("func2");
        }
    }

    public static void main(String[] args) {
        final SynchronizationTest instance = new SynchronizationTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                f();
                instance.func1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                g();
                instance.func2();
            }
        }).start();
    }
}
