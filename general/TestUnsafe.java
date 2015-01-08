package general;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/*
 * http://javadetails.com/2012/03/21/java-array-memory-allocation.html
 */
public class TestUnsafe {
    public static void main(String[] args) {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);

            int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
            /*
             * hash code, class reference and size
             * The output seems weird because the array[0] appears as unsafe.getInt(array, 4)
             */
            long numberOfWords = 3 + 9;

            for (long i = 0; i < numberOfWords; i++) {
                int cur = unsafe.getInt(array, i * 4);
                System.out.println(cur);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
