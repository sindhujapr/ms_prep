package javautils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class ExternalizationTest implements Externalizable {
    private int id;
    private String username;

    /*
     * default constructor will be invoked when deserialization for
     * Externalizable only. Deserialization of Serializable doesn't need this.
     */
    public ExternalizationTest() {
    System.out.println("default constructor");
    }

    public ExternalizationTest(String username, int id) {
    System.out.println("non-default constructor");
    this.username = username;
    this.id = id;
    }

    @Override
    public boolean equals(Object o) {
    if (!(o instanceof ExternalizationTest))
        return false;

    ExternalizationTest account = (ExternalizationTest) o;
    return account.username.equals(username) && account.id == id;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
        ClassNotFoundException {
    username = (String) in.readObject();
    id = in.read();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(username);
    /*
     * we should use readInt/writeInt or read/write pair. Otherwise the data
     * cannot be deserialized
     */
    out.write(id);
    }
    
    /*
     * We need to implement writeReplace/readResolve if we want ensure singleton.
     */

    private static final int N = 10;

    public static void main(String[] args) throws IOException,
        ClassNotFoundException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream out = new ObjectOutputStream(bos);

    Externalizable[] array = new ExternalizationTest[N];
    for (int i = 0; i < N; i++) {
        array[i] = new ExternalizationTest("hello", i);
    }

    /*
     * we can also serialize the array as a whole but not the elements one
     * by one.
     */
    out.writeObject(array);
    out.close();

    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream in = new ObjectInputStream(bis);

    Externalizable[] array1 = (Externalizable[]) in.readObject();

    if (array1.length != N)
        System.out.println("error when deserialization");

    for (int i = 0; i < N; i++) {
        if (array1[i].equals(array[i]))
        System.out.println("good");
        else
        System.out.println("bad");
    }

    in.close();
    }
}