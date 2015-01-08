package perf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/* answer from stackoverflow http://stackoverflow.com/questions/1023200/memory-mapped-files-in-java
 * Did anyone actually check to see if ByteBuffers created by memory mapping support invoking .array() in the first place, regardless of readonly/readwrite?
 * From my poking around, as far as I can tell, the answer is NO. 
 * A ByteBuffer's ability to return a direct byte[] array via ByteBuffer.array() is goverened by the presence of ByteBuffer.hb (byte[]),
 * which is always set to null when a MappedByteBuffer is created.
 * Which kinda sucks for me, because I was hoping to do something similar to what the question author wanted to do.
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = byteBufferForFile("C:/playground/filedeletion.log");
        System.out.println(byteBuffer.slice());
    }
    
    public static ByteBuffer byteBufferForFile(String fname){
        FileChannel fileChannel;
        ByteBuffer byteBuffer;
        try {
            fileChannel = new FileInputStream(fname).getChannel();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            return null;
        }
        try {
            byteBuffer = fileChannel.map(MapMode.PRIVATE,0,fileChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return byteBuffer;
    }
}