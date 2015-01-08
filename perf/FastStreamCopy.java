package perf;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.*;
import java.text.NumberFormat;

import nio.sample.*;

public class FastStreamCopy {
    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Usage: java FastStreamCopy filename");
            System.exit(1);
        }

        NumberFormat digits = NumberFormat.getInstance();
        digits.setMaximumFractionDigits(3);

        long before;
        long after;
        double slowTime;
        double fastTime;
        double speedUp;

        String filename = args[0];
        String contents;

        // Slow method
        System.out.println("Reading file " + args[0] + " using slow method");
        before = System.currentTimeMillis(); // Start timing
        // contents = slowStreamCopy(filename);
        contents = readFile(filename);
        after = System.currentTimeMillis(); // End timing
        slowTime = after - before;
        // System.out.println("File's contents:\n" + contents);

        // Fast method
        System.out.println("Reading file " + args[0] + " using fast method");
        before = System.currentTimeMillis(); // Start timing
        contents = fastStreamCopy(filename);
        after = System.currentTimeMillis(); // End timing
        fastTime = after - before;
        // System.out.println("File's contents:\n" + contents);

        // Comparison
        speedUp = 100d * slowTime / fastTime;
        System.out.println("Slow method required " + slowTime + " ms.");
        System.out.println("Fast method required " + fastTime + " ms.");
        System.out.print("Speed up = " + digits.format(speedUp) + "% ");
        System.out.println(speedUp > 100 ? "Good!" : "Bad!");
    }

    /**
     * Read the contents of a text file using a memory-mapped byte buffer.
     * 
     * A MappedByteBuffer, is simply a special ByteBuffer. MappedByteBuffer maps
     * a region of a file directly in memory. Typically, that region comprises
     * the entire file, although it could map a portion. You must, therefore,
     * specify what part of the file to map. Moreover, as with the other Buffer
     * objects, no constructor exists; you must ask the
     * java.nio.channels.FileChannel for its map() method to get a
     * MappedByteBuffer.
     * 
     * Direct buffers allocate their data directly in the runtime environment
     * memory, bypassing the JVM|OS boundary, usually doubling file copy speed.
     * However, they generally cost more to allocate.
     */
    private static String fastStreamCopy(String filename) {
        String s = "";
        FileChannel fc = null;
        try {
            fc = new FileInputStream(filename).getChannel();

            // int length = (int)fc.size();

            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY,
                    0, fc.size());
            // CharBuffer charBuffer =
            // Charset.forName("ISO-8859-1").newDecoder().decode(byteBuffer);

            // ByteBuffer byteBuffer = ByteBuffer.allocate(length);
            // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(length);
            // CharBuffer charBuffer = byteBuffer.asCharBuffer();

            // CharBuffer charBuffer =
            // ByteBuffer.allocateDirect(length).asCharBuffer();
            /*
             * int size = charBuffer.length(); if (size > 0) { StringBuffer sb =
             * new StringBuffer(size); for (int count=0; count<size; count++)
             * sb.append(charBuffer.get()); s = sb.toString(); }
             * 
             * if (length > 0) { StringBuffer sb = new StringBuffer(length); for
             * (int count=0; count<length; count++) {
             * sb.append(byteBuffer.get()); } s = sb.toString(); }
             */
            int size = byteBuffer.capacity();
            if (size > 0) {
                // Retrieve all bytes in the buffer
                byteBuffer.clear();
                byte[] bytes = new byte[size];
                byteBuffer.get(bytes, 0, bytes.length);
                s = new String(bytes);
            }

            fc.close();
        } catch (FileNotFoundException fnfx) {
            System.err.println("File not found: " + fnfx);
        } catch (IOException iox) {
            System.err.println("I/O problems: " + iox);
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException ignore) {
                    // ignore
                }
            }
        }
        return s;
    }

    private static String slowStreamCopy(String filename) {
        String s = "";

        FileReader in = null;
        try {
            File file = new File(filename);

            int size = (int) file.length();

            char[] buffer = new char[size];
            in = new FileReader(file);

            // int count = in.read(buffer, 0, size);
            // if (count != -1)

            int count;
            while ((count = in.read(buffer, 0, size)) >= 0) {
                s = new String(buffer, 0, count);
            }
            in.close();
        }
        /*
         * String line; BufferedReader in = null; try { File file = new
         * File(filename); int size = (int)file.length(); if (size > 0) {
         * StringBuffer sb = new StringBuffer(size); in = new BufferedReader(new
         * InputStreamReader(new FileInputStream(filename),"ISO-8859-1")); while
         * ((line = in.readLine()) != null) { sb.append(line); } in.close(); s =
         * sb.toString(); } }
         */
        catch (FileNotFoundException fnfx) {
            System.err.println("File not found: " + fnfx);
        } catch (IOException iox) {
            System.err.println("I/O problems: " + iox);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                    // ignore
                }
            }
        }
        return s;
    }

    public static String readFile(String filename) {
        String s = "";
        FileInputStream in = null;
        // FileReader in = null;
        try {
            File file = new File(filename);
            byte[] buffer = new byte[(int) file.length()];
            // char[] buffer = new char[(int) file.length()];
            in = new FileInputStream(file);
            // in = new FileReader(file);
            in.read(buffer);
            s = new String(buffer);
            in.close();
        } catch (FileNotFoundException fnfx) {
            System.err.println("File not found: " + fnfx);
        } catch (IOException iox) {
            System.err.println("I/O problems: " + iox);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignore) {
                    // ignore
                }
            }
        }
        return s;
    }
}