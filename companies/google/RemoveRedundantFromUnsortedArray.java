package interview.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * http://blog.jobbole.com/31804/
 */
public class RemoveRedundantFromUnsortedArray {
    public static void removeRedundant1(int[] array) {
    Set<Integer> set = new TreeSet<>();
    for (int i = 0; i < array.length; i++) {
        set.add(array[i]);
    }
    
    for(Integer value : set)
        System.out.print(value + " ");
    System.out.println();
    }

    public static void removeRedundant2(int[] array) {
    // this is big enough
    int[] result = new int[array.length];
    Arrays.fill(result, Integer.MIN_VALUE);
    int index = 0;
    
    for (int i = 0; i < array.length; i++) {
        int value = array[i];
        int j;
        for (j = 0; j < result.length; j++) {
        if(result[j] == value)
            break;
        }
        if(j == result.length)
        result[index++] = value;
    }

    for (int i = 0; i < result.length; i++) {
        if(result[i] != Integer.MIN_VALUE)
        System.out.print(result[i] + " ");
    }
    }
    
    public static void main(String[] args) {
    int[] array = {4, 5, 6, 7, 8, 9, 10, 6, 4, 11, 12, 14, 15, 16};
    for (int i = 0; i < array.length; i++) {
        System.out.print(array[i] + " ");
    }
    System.out.println();

    removeRedundant1(array);
    removeRedundant2(array);
    }
}