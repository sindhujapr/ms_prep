package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;

/*
 * Deep copy inside
 */
public class Serializer {
    static void store(Serializable o, File f) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(o);
        out.close();
    }

    static Object load(File f) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
        return in.readObject();
    }

    /**
     * Use object serialization to make a "deep clone" of the object o. This
     * method serializes o and all objects it refers to, and then deserializes
     * that graph of objects, which means that everything is copied. This
     * differs from the clone() method of an object which is usually implemented
     * to produce a "shallow" clone that copies references to other objects,
     * instead of copying all referenced objects.
     */
    static Object deepclone(final Serializable o) throws IOException,
            ClassNotFoundException {
        final PipedOutputStream pipeout = new PipedOutputStream();

        // Now define an independent thread to serialize the object and write
        // its bytes to the PipedOutputStream
        Thread writer = new Thread() {
            public void run() {
                ObjectOutputStream out = null;
                try {
                    out = new ObjectOutputStream(pipeout);
                    out.writeObject(o);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                    } catch (Exception e) {
                    }
                }
            }
        };
        writer.start();

        // Meanwhile, in this thread, read and deserialize from the piped
        // input stream. The resulting object is a deep clone of the original.
        PipedInputStream pipein = new PipedInputStream(pipeout);
        ObjectInputStream in = new ObjectInputStream(pipein);
        return in.readObject();
    }


    public static void main(String[] args) throws IOException,
            ClassNotFoundException {
        // Create a simple object graph
        DataStructure ds = new DataStructure();
        ds.message = "hello world";
        ds.data = new int[] { 1, 2, 3, 4 };
        ds.other = new DataStructure();
        ds.other.message = "nested structure";
        ds.other.data = new int[] { 9, 8, 7 };

        // Display the original object graph
        System.out.println("Original data structure: " + ds);

        // Output it to a file
        File f = new File("datastructure.ser");
        System.out.println("Storing to a file...");
        Serializer.store(ds, f);

        // Read it back from the file, and display it again
        ds = (DataStructure) Serializer.load(f);
        System.out.println("Read from the file: " + ds);

        // Create a deep clone and display that. After making the copy
        // modify the original to prove that the clone is "deep".
        DataStructure ds2 = (DataStructure) Serializer.deepclone(ds);
        ds.other.message = null;
        ds.other.data = null; // Change original
        System.out.println("Deep clone: " + ds2);
    }
}

/**
 * This is a simple serializable data structure that we use below for
 * testing the methods above
 */
class DataStructure implements Serializable {
    private static final long serialVersionUID = 8923423343458304L;

    String message;
    int[] data;
    DataStructure other;

    public String toString() {
        String s = message;
        for (int i = 0; i < data.length; i++)
            s += " " + data[i];
        if (other != null)
            s += "\n\t" + other.toString();
        return s;
    }
}