package programming.beauty;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CountOfOne {
    private static Map<Integer, Integer> counter = new HashMap<Integer, Integer>();
    static int N = 1202;

    static {
    N = 1000;
    System.out.println(N);
    }

    CountOfOne() {
    this(100);
    }

    CountOfOne(int i) {
    N = i;
    }

    public String toString() {
    return "hello";
    }

    public static void main(String[] args) {
    System.out.println(countOne(1202));

    /*
     * Integer i = new Integer(10); Integer j = new Integer(5);
     * System.out.println(Integer.toBinaryString(i));
     * System.out.println(Integer.toBinaryString(j)); i ^= j;
     * System.out.println(Integer.toBinaryString(i)); i |= j;
     * System.out.println(Integer.toBinaryString(i << 4));
     * System.out.println(Integer.toBinaryString(i >> 2));
     */
    /*
     * String[] strings = new String[] { "foo", "bar" };
     * changeReference(strings);
     * System.out.println(Arrays.toString(strings)); // still [foo, bar]
     * changeValue(strings); System.out.println(Arrays.toString(strings));
     * // [foo, foo]
     */

    // for(int i = 1; i <= N; i++)
    // System.out.println(i + "\t" + counter.get(i));

    // match(Integer.MAX_VALUE);
    }

    public static void changeReference(String[] strings) {
    strings = new String[] { "foo", "foo" };
    }

    public static void changeValue(String[] strings) {
    strings[1] = "foo";
    }

    public static int countOne(int n) {
    if (n == 1) {
        counter.put(1, 1);
        return 1;
    } else {
        int count = 0;
        int value = n;
        while (value > 0) {
        if (value % 10 == 1)
            count++;
        value /= 10;
        }

        int val = counter.get(n - 1) == null ? countOne(n - 1) + count
            : counter.get(n - 1) + count;

        counter.put(n, val);
        return val;
    }
    }

    public static void match(int n) {
    while (n > 0) {
        if (n == countOne(n)) {
        System.out.println(n);
        break;
        }
        n--;
    }
    }
}
