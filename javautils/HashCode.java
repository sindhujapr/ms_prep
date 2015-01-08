package javautils;

import java.util.ArrayList;
import java.util.List;

public class HashCode {
    private boolean v1;
    private byte v2;
    private char v3;
    private short v4;
    private int v5;
    private long v6;
    private float v7;
    private double v8;

    private Object o1;
    private Object o2;

    private List<Integer> list = new ArrayList<>();
    private int[] array = new int[1024];

    /**
     * Implemented based on suggestion from Effective Java Item #9. We need to
     * consider all the keys that are used for comparison in equals().
     * Also, we shouldn't consider any keys that aren't used in equals().
     */
    @Override
    public int hashCode() {
    int hashCode = 0;
    hashCode += v1 ? 1 : 0;
    hashCode += 17 * hashCode + (int) v2;
    hashCode += 17 * hashCode + (int) v3;
    hashCode += 17 * hashCode + (int) v4;
    hashCode += 17 * hashCode + v5;
    hashCode += 17 * hashCode + (int) (v6 ^ (v6 >>> 32));
    hashCode += 17 * hashCode + Float.floatToIntBits(v7);
    hashCode += 17 * hashCode + Double.doubleToLongBits(v8);

    hashCode += 17 * hashCode + list.hashCode();

    for (int element : array)
        hashCode += 17 * hashCode + element;

    return hashCode;
    }

    @Override
    public boolean equals(Object o) {
    if (o == this)
        return true;

    if (!(o instanceof HashCode))
        return false;

    HashCode obj = (HashCode) o;
    if (!(obj.v1 == v1 && obj.v2 == v2 && obj.v3 == v3 && obj.v4 == v4
        && obj.v5 == v5 && obj.v6 == v6 && obj.v7 == v7 && obj.v8 == v8
        && obj.o1.equals(o1) && obj.o2.equals(o2)))
        return false;

    if (!obj.list.equals(list))
        return false;

    for (int i = 0; i < array.length; i++) {
        if (obj.array[i] != array[i])
        return false;
    }

    return true;
    }
    
    @Override
    public String toString() {
    // always override toString() to provide readable information
    return null;
    }
}