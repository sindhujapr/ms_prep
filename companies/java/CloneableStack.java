package interview.java;

import java.util.Arrays;

/*
 * Avoid implementing Cloneable!!!
 */
public class CloneableStack implements Cloneable {
    private Object[] elements;
    private int size = 0;
    private static final int INIT_CAPACITY = 2;

    public CloneableStack() {
        this.elements = new Object[INIT_CAPACITY];
        size = INIT_CAPACITY;
        for (int i = 0; i < size; i++)
            elements[i] = new Object();
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new RuntimeException();
        Object result = elements[--size];
        /*
         * reduce memory footprint
         */
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < INIT_CAPACITY; i++)
            sb.append(elements[i].toString() + ", ");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof CloneableStack))
            return false;

        CloneableStack cs = (CloneableStack) o;
        /*
         * The two instances share the same inner array, of course including the
         * elements within the array
         */
        for (int i = 0; i < size; i++)
            if (!elements[i].equals(cs.elements[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < elements.length; i++) {
            hashCode += 31 * hashCode + elements[i].hashCode();
        }
        return hashCode;
    }

    /**
     * Same as constructor, we shouldn't invoke non-final methods in clone().
     */
    @Override
    public CloneableStack clone() {
        CloneableStack ret = null;
        /*
         * Since this method will be for public use, it's better we don't throw
         * checked exception
         */
        try {
            ret = (CloneableStack) super.clone();
            /*
             * Without deep copy, two Cloneable objects will refer to the same
             * objects. That means, one object can change the state of the
             * other. This is dangerous. We have to implement deep copy so that
             * each element within the array is reassigned. Also, if elements is
             * final, we cannot re-assign value to it!!
             */
            ret.elements = new Object[size]; // this.elements.clone();
            for (int i = 0; i < size; i++) {
                if (elements[i] != null) {
                    ret.elements[i] = new Object();
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static void main(String[] args) {
        try {
            CloneableStack i1 = new CloneableStack();
            CloneableStack i2 = i1.clone();

            System.out.println(i1);
            System.out.println(i2);
            System.out.println(i1.equals(i2));

            /*
             * We can even create another instance even if the constructor is
             * private!!!
             */
            System.out.println("####################################");
            Base1 b1 = Base1.getInstance();
            Base1 b2 = b1.clone();
            System.out.println(b1 + "\t" + b2);
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
    }
}

class Base1 implements Cloneable {
    private int i = 1;
    private static Base1 singleton = new Base1(1);

    private Base1() {
    }

    private Base1(int i) {
        this.i = i;
    }

    public static Base1 getInstance() {
        return singleton;
    }

    @Override
    public Base1 clone() throws CloneNotSupportedException {
        Base1 ret = (Base1) super.clone();
        ret.i = 2;
        return ret;
    }
}