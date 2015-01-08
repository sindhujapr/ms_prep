package general;

import java.util.Arrays;
import java.util.Random;

/*
 * Given an array of integers, output the largest set of integers that are
 * consecutive. For example, output 5:[11, 12, 13, 14, 15] for array 
 * {15, 7, 12, 6, 14, 13, 9, 11}
 */
public class ConsecutiveNumbers {
    /*
     * use bitmap
     */
    public static void find1(int[] array, int limit) {
    int size = 1;
    while (size < limit) {
        size <<= 1;
    }

    System.out.println("number of integers needed for bit map: "
        + (size >> 5));
    int[] bitmap = new int[size >> 5];
    scan(bitmap, array);

    /*
     * check the bitmap to see which bits are set and then output the
     * corresponding numbers
     */
    int start = 0;
    int maxLength = 1;
    int maxEnd = -1;
    /*
     * set the last element to -2. if we set it to -1, the output for array
     * {0, 1, 2, 4, 5} would be {-1, 0, 1, 2}. In another words, lastElement
     * must be at least 2 lesser than the smallest number in the array.
     */
    int lastElement = Integer.MIN_VALUE;
    for (int i = 0, length = 1; i < bitmap.length; i++) {
        int bits = bitmap[i];
        for (int j = 0; j < 32; j++) {
        if ((bits & (1 << j)) == 0) {
            continue;
        }
        int value = start + j;

        if (value == lastElement + 1) {
            length++;
        } else {
            if (length > maxLength) {
            maxLength = length;
            maxEnd = lastElement;
            }
            length = 1;
        }
        lastElement = value;
        }
        start += 32;
    }

    System.out.println("max length: " + maxLength);
    for (int i = maxEnd - maxLength + 1; i <= maxEnd; i++) {
        System.out.print(i + " ");
    }
    }

    public static void scan(int[] bitmap, int[] array) {
    for (int i = 0; i < array.length; i++) {
        int value = array[i];
        /*
         * first locate the index of bit we need to set set the index-th bit
         * of bitmap
         */
        int i1 = value / 32;
        int i2 = value % 32;
        bitmap[i1] |= 1 << i2;
    }
    }

    /*
     * use an auxiliary array. similar to find1 but needs more memory
     */
    public static void find2(int[] array) {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < array.length; i++) {
        if (array[i] > max)
        max = array[i];

        if (array[i] < min)
        min = array[i];
    }

    int[] copy = new int[max - min + 1];
    for (int i = 0; i < array.length; i++) {
        int index = array[i] - min;
        copy[index] = 1;
    }

    int maxLength = 1;
    int maxEnd = -1;
    int lastElement = Integer.MIN_VALUE;
    for (int i = 0, length = 1; i < copy.length; i++) {
        if (copy[i] == 0)
        continue;

        int value = min + i;
        if (value == lastElement + 1) {
        length++;
        } else {
        if (length > maxLength) {
            maxLength = length;
            maxEnd = lastElement;
        }
        length = 1;
        }
        lastElement = value;
    }
    
    System.out.println("max length: " + maxLength);
    for (int i = maxEnd - maxLength + 1; i <= maxEnd; i++) {
        System.out.print(i + " ");
    }
    }

    public static void main(String[] args) {
    /*
     * make sure the largest integer in the array is less than N
     */
    int N = 1024;
    Random rand = new Random();
    int[] array = {15, 7, 12, 6, 14, 13, 9, 11, 34, 20, 17, 16, 30, 25};
//  for (int i = 0; i < 200; i++) {
//      int index = rand.nextInt(N);
//      array[index] = rand.nextInt(N);
//  }
    System.out.println(Arrays.toString(array));

//  find1(array, N);
    find2(array);
    }
}