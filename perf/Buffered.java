package perf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * If the file doesn't exist or empty, FileChannel.asCharBuffer() cannot be used to
 * write any data because the capacity/limit is 0. In this situation, we can construct
 * a ByteBuffer with enough capacity and then put data into it. At last, call
 * FileChannel.write() to write the whole ByteBuffer into the file. 
 */
public class Buffered {
    private static final int count = 1000000;

    static class Thread1 implements Runnable {
        @Override
        public void run() {
            FileChannel fc = null;
            ByteBuffer bBuf = null;
            try {
                fc = new RandomAccessFile("t1.txt", "rw").getChannel();
//              fc = new FileOutputStream("t1.txt", "rw").getChannel();
                bBuf = ByteBuffer.allocate(count*6);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                long start = System.currentTimeMillis();
                byte[] bytes = "hello\n".getBytes();
                for (int i = 0; i < count; i++) {
                    bBuf.put(bytes);
                }
                /* we need to reset the position before flushing any data in the buffer */
                bBuf.flip();
                fc.write(bBuf);
                System.out.println("mapped writer: " + (System.currentTimeMillis() - start));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(fc != null)
                        fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class Thread2 implements Runnable {
        @Override
        public void run() {
            BufferedWriter bufWriter = null;
            try {
                bufWriter = new BufferedWriter(new FileWriter(new File("t2.txt")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                long start = System.currentTimeMillis();
                for (int i = 0; i < count; i++) {
                    bufWriter.write("hello\n");
                }
                System.out.println("buffered writer: " + (System.currentTimeMillis() - start));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(bufWriter != null)
                        bufWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class Thread3 implements Runnable {
        @Override
        public void run() {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new FileWriter(new File("t3.txt")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                long start = System.currentTimeMillis();
                for (int i = 0; i < count; i++) {
                    printWriter.write("hello\n");
                }
                System.out.println("print writer: " + (System.currentTimeMillis() - start));
            } finally {
                if(printWriter != null)
                    printWriter.close();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread t1 = new Thread(new Thread1());
        t1.start();
        t1.join();
        
        Thread t2 = new Thread(new Thread2());
        t2.start();
        t1.join();
        
        Thread t3 = new Thread(new Thread3());
        t3.start();
        t1.join();
    }
}