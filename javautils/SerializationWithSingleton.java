package javautils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * http://www.javalobby.org/java/forums/t17491.html
 */
class Singleton implements Serializable {
    private static final long serialVersionUID = -3536445165231074332L;

    private String str;
    private int value;
    private static final Singleton INSTANCE = new Singleton();

    public Singleton() {
    str = "hello";
    value = 10;
    }

    @Override
    public String toString() {
    return str + "\t" + value;
    }

    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException {
    System.out.println("readObject");
    in.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
    System.out.println("writeObject");
    out.defaultWriteObject();
    }

    /*
     * Classes that need to designate a replacement when an instance of it is
     * read from the stream should implement this special method with the exact
     * signature.
     * 
     * ANY-ACCESS-MODIFIER Object readResolve() throws ObjectStreamException;
     * 
     * This readResolve method follows the same invocation rules and
     * accessibility rules as writeReplace.
     */
    public Object readResolve() {
    System.out.println("readResolve");
    return INSTANCE;
    }

    /*
     * Serializable classes that need to designate an alternative object to be
     * used when writing an object to the stream should implement this special
     * method with the exact signature:
     * 
     * ANY-ACCESS-MODIFIER Object writeReplace() throws ObjectStreamException;
     * 
     * This writeReplace method is invoked by serialization if the method exists
     * and it would be accessible from a method defined within the class of the
     * object being serialized. Thus, the method can have private, protected and
     * package-private access. Subclass access to this method follows java
     * accessibility rules.
     */
    private Object writeReplace() {
    System.out.println("writeReplace");
    return INSTANCE;
    }

    public static Singleton getInstance() {
    return INSTANCE;
    }
}

public class SerializationWithSingleton {
    public static void main(String[] args) {
//  Singleton s = new Singleton(); //Singleton.getInstance();

    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    try {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        out = new ObjectOutputStream(bos);
        out.writeObject(new Singleton());
        out.writeObject(new Singleton());
        out.close();

        in = new ObjectInputStream(new ByteArrayInputStream(
            bos.toByteArray()));
        Singleton s1 = (Singleton) in.readObject();
        Singleton s2 = (Singleton) in.readObject();
        
        /*
         * we wrote two objects so we can only read two objects.
         * however, readObject and readResolve are only called once.
         * also, writeObject is only called once, while writeReplace
         * is called twice
         */
//      Singleton s3 = (Singleton) in.readObject();
        System.out.println(s1 + "\t" + s2);
        /*
         * without writeReplace and readResolve, the below result is false
         */
        System.out.println(s1 == s2);
        in.close();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
        if (out != null)
            out.close();
        if (in != null) {
            in.close();
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    }
}