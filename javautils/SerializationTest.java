package javautils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Some useful links:
 * http://docs.oracle.com/javase/6/docs/platform/serialization/spec/serialTOC.html
 * https://groups.google.com/forum/#!msg/project-lombok/RQ6VQRRMY38/YAHsmFv28soJ
 */

public class SerializationTest {
    public static void main(String[] args) {
    Account[] accounts = new Account[10];
    for (int i = 0; i < accounts.length; i++) {
        accounts[i] = new Account("account" + i, i);
    }

    /*
     * ByteArrayOutputStream will be shared between the two try-with-resources
     * clauses, so we place it outside of the clauses.
     */
    ByteArrayOutputStream bos = new ByteArrayOutputStream();

    try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
        /*
         * read/write array as a whole
         */
        out.writeObject(accounts);
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
            bos.toByteArray()))) {
        Account[] accounts1 = (Account[]) in.readObject();
        for (int i = 0; i < accounts1.length; i++) {
        if (!accounts[i].equals(accounts1[i]))
            System.out.println("doesn't equal");
        else
            System.out.println("equal");
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }
}

class Account implements Serializable {
    private static final long serialVersionUID = 7314024421935901431L;

    private String username;
    transient String password;
    private int id;

    Account(String username, int id) {
    this.username = username;
    this.id = id;
    }

    @Override
    public boolean equals(Object o) {
    if (!(o instanceof Account))
        return false;

    Account account = (Account) o;
    return account.username.equals(username) && account.id == id;
    }

    /*
     * if the below two methods are defined with the EXACT signatures, they will
     * be invoked for serialization/deserialization and the standard routine
     * will be bypassed. This trick only works for Serializable.
     */
    private void readObject(ObjectInputStream in)
        throws ClassNotFoundException, IOException {
    in.defaultReadObject();
    System.out.println("readObject()");
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    System.out.println("writeObject()");
    }
}