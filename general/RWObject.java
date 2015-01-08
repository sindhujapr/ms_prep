package general;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class MyObject implements Serializable {
    private static final long serialVersionUID = 12342334523452345L;

    private static int count = 0;

    private final int id = count++;
    private long l;
    private boolean b;
    private String str;
    private List<String> list;

    public MyObject(long l, boolean b, String str, List<String> list) {
    this.l = l;
    this.b = b;
    this.str = str;
    this.list = list;
    }

    @Override
    public boolean equals(Object object) {
    if (object == this)
        return true;

    if (!(object instanceof MyObject))
        return false;

    MyObject obj = (MyObject) object;
    return obj.id == id && obj.l == l && obj.str.equals(str)
        && obj.list.equals(list);
    }

    @Override
    public int hashCode() {
    int result = 17;

    result = 31 * result + (int) (l ^ (l >>> 32));
    result = 31 * result + (b ? 1 : 0);
    result = 31 * result + str.hashCode();
    result = 31 * result + list.hashCode();

    return result;
    }

    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(id);
    sb.append("\t");
    sb.append(l);
    sb.append("\t");
    sb.append(str);
    sb.append("\t");
    sb.append(list.toString());
    return sb.toString();
    }
}

public class RWObject {
    public List<MyObject> generateObjects(int count, String file) {
    RandomAccessFile raFile = null;
    List<MyObject> objList = new ArrayList<MyObject>();

    try {
        raFile = new RandomAccessFile(new File(file), "rw");
        raFile.writeInt(count);

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
        raFile.seek(4 * (i + 1));
        raFile.writeInt(bytes.length);

        raFile.seek(objOffset);
        raFile.write(bytes);
        // start point for next object data
        objOffset += bytes.length;
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
        if (raFile != null)
            raFile.close();
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
    RandomAccessFile raFile = null;

    List<MyObject> objects = new ArrayList<MyObject>();
    List<Integer> objSizes = new ArrayList<Integer>();

    try {
        raFile = new RandomAccessFile(new File(file), "r");

        // First 4 bytes is the number of objects
        int count = raFile.readInt();
        long objOffset = (count + 1) * 4;
        for (int i = 0; i < count; i++) {
        long sizeOffset = (i + 1) * 4;
        raFile.seek(sizeOffset);
        int size = raFile.readInt();

        byte[] bytes = new byte[size];
        raFile.seek(objOffset);
        int length = raFile.read(bytes);

        MyObject object = toObject(bytes);
        objects.add(object);

        objOffset += size;
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
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
        } catch (IOException e) {
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
        } catch (IOException e) {
        System.err.println("Error closing the input stream");
        }
    }
    return (MyObject) obj;
    }

    // load the object [start, end)
    public void loadObject(List<MyObject> list, String fileName, int start,
        int end) {
    RandomAccessFile raFile = null;

    try {
        raFile = new RandomAccessFile(new File(fileName), "r");

        int count = raFile.readInt();
        if (start <= 0 || end >= count - 1) {
        System.out.println("Error parameters");
        return;
        }

        List<Integer> objSizes = new ArrayList<Integer>();
        int objOffset = 4 * (count + 1);
        for (int i = 0; i <= end; i++) {
        int size = raFile.readInt();
        objSizes.add(size);
        if (i < start)
            objOffset += size;
        }

        raFile.seek(objOffset);
        for (int i = start; i < end; i++) {
        int size = objSizes.get(i);
        byte[] bytes = new byte[size];
        int length = raFile.read(bytes);
        if (length != bytes.length)
            System.out.println("error length");

        MyObject object = toObject(bytes);
        System.out.println(object);
        if (!object.equals(list.get(i)))
            System.out.println("doesn't match");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
        if (raFile != null)
            raFile.close();
        } catch (Exception e2) {
        e2.printStackTrace();
        }
    }
    }

    public static final int count = 1000;

    public static void main(String[] args) throws IOException {
    String fileName = "MyObject.dat";
    File file = new File(fileName);

    if (file.exists())
        file.delete();

    RWObject rw = new RWObject();
    long start = System.currentTimeMillis();
    List<MyObject> list = rw.generateObjects(count, fileName);
    System.out.println(rw.verifyObjects(list, fileName));
    System.out.println("total time: "
        + (System.currentTimeMillis() - start));

    rw.loadObject(list, fileName, 110, 220);
    }
}