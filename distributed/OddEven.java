package distributed;

public class OddEven {
    private final static Object synObj = new Object();
    private static boolean isOdd = true;
    private static int value = 1;
    private static int limit = 100;

    static class Odd implements Runnable {
        @Override
        public void run() {
            while(value < limit) {
                System.out.print(value++ + "\t");
                
                synchronized(synObj) {
                    isOdd = false;
                    synObj.notifyAll();
                }

                synchronized(synObj) {
                    try {
                        while(!isOdd)
                            synObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    static class Even implements Runnable {
        @Override
        public void run() {
            while(value <= limit) {
                synchronized(synObj) {
                    try {
                        while(isOdd)
                            synObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                System.out.println(value++);
    
                synchronized(synObj) {
                    isOdd = true;
                    synObj.notifyAll();
                }
            }
        }
    }   
    
    public static void main(String[] args) throws InterruptedException {
        Thread odd = new Thread(new Odd());
        Thread even = new Thread(new Even());
        
        odd.start();
        even.start();
        
        odd.join();
        even.join();
    }
}