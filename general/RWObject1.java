package general;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* serialize/deserialize using file channel */
public class RWObject1 {
    public List<MyObject> generateObjects(int count, String file) {
        FileChannel fc = null;
        List<MyObject> objList = new ArrayList<MyObject>();

        try {
            fc = new RandomAccessFile(new File(file), "rw").getChannel();
            // allocate enough buffer
            ByteBuffer bb = ByteBuffer.allocateDirect(250*count);
            bb.putInt(count);

            Random rand = new Random();
            String str = "helloworldthisissimoniwanttogototheus";
            
            int objOffset = (count + 1) * 4;
            for (int i = 0; i < count; i++) {

                long l = rand.nextLong();
                boolean b = rand.nextBoolean();
                int pos = rand.nextInt(str.length());
                List<String> list = new ArrayList<String>();
                list.add(str.substring(pos));
                list.add("helo");
                MyObject object = new MyObject(l, b, str.substring(pos), list);

                objList.add(object);

                byte[] bytes = toByteArray(object);
                bb.position(4*(i+1));
                bb.putInt(bytes.length);

                bb.position(objOffset);
                bb.put(bytes);
                // start point for next object data
                objOffset += bytes.length;
            }
            
            bb.flip();
            fc.write(bb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fc != null)
                    fc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }       

        return objList;
    }

    /*
     * Compare the objects in the list with the ones written in the file
     */
    public boolean verifyObjects(List<MyObject> objList, String file) {
        FileChannel fc = null;

        List<MyObject> objects = new ArrayList<MyObject>();

        try {
            fc = new RandomAccessFile(new File(file), "rw").getChannel();
            MappedByteBuffer mbb = fc.map(MapMode.READ_ONLY, 0, fc.size());

            int count = mbb.getInt();
            System.out.println("count: " + count);

            int objOffset = (count + 1) * 4;
            for (int i = 0; i < count; i++) {
                int sizeOffset = (i+1) * 4;
                mbb.position(sizeOffset);
                
                int size = mbb.getInt();

                byte[] bytes = new byte[size];
                mbb.position(objOffset);
                mbb.get(bytes);
                
                MyObject object = toObject(bytes);
                objects.add(object);

                objOffset += size;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

        return objList.equals(objects);
    }

    // toByteArray and toObject are taken from: http://tinyurl.com/69h8l7x
    // we cannot share the objectoutputstream!!!
    private byte[] toByteArray(MyObject obj) throws IOException {
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch(IOException e) {
                System.err.println("Error closing the output stream");
            }
        }
        return bytes;
    }

    private MyObject toObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null)
                    ois.close();
            } catch(IOException e) {
                System.err.println("Error closing the input stream");
            }
        }
        return (MyObject) obj;
    }

    // load the object [start, end)
    public void loadObject(List<MyObject> list, String fileName, int start, int end) {
        FileChannel fc = null;
        
        try {
            fc = new RandomAccessFile(new File(fileName), "rw").getChannel();
            MappedByteBuffer mbb = fc.map(MapMode.READ_ONLY, 0, fc.size());
            
            int count = mbb.getInt();
            if(start <= 0 || end >= count-1) {
                System.out.println("Error parameters");
                return;
            }
            
            List<Integer> objSizes = new ArrayList<Integer>(); 
            int objOffset = 4*(count+1);
            for (int i = 0; i <= end; i++) {
                int size = mbb.getInt();
                objSizes.add(size);
                if(i < start)
                    objOffset += size;
            }

            mbb.position(objOffset);
            for (int i = start; i < end; i++) {
                int size = objSizes.get(i);
                byte[] bytes = new byte[size];
                mbb.get(bytes);
                
                MyObject object = toObject(bytes);
                System.out.println(object);
                if(!object.equals(list.get(i)))
                    System.out.println("doesn't match");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fc != null)
                    fc.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static final int count = 100;

    public static void main(String[] args) throws IOException {

        String fileName = "MyObject.dat";
        File file = new File(fileName);
        
        if(file.exists())
            file.delete();

        RWObject1 rw = new RWObject1();
        long start = System.currentTimeMillis();
        List<MyObject> list = rw.generateObjects(count, fileName);
        System.out.println(rw.verifyObjects(list, fileName));
        System.out.println("total time: " + (System.currentTimeMillis() - start));
        
        rw.loadObject(list, fileName, 10, 20);
    }
}