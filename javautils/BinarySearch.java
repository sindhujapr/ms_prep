package javautils;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
    int[] array = {3, 9, 8, 7, 4, 5, 6, 2, 1, 10};
    Arrays.sort(array);
    System.out.println(Arrays.toString(array));
    for (int i = 0; i < array.length; i++) {
        System.out.println((i+1) + "\t index " + binarySearch(array, 0, array.length-1, i+1));
    }
    }
    
    public static int binarySearch(int[] array, int from, int to, int key) {
    int low = from;
    int high = to;
    int index;
    /*
     * we shouldn't use < here. otherwise we will miss one element.
     */
    while(low <= high) {
        /*
         * this will prevent overflow. if low = high = Integer.MAX_VALUE,
         * (low+high) >> 1 return -1.
         */
        index = (low+high) >>> 1;
        if(array[index] > key)
        high = index-1;
        else if(array[index] < key)
        low = index+1;
        else {
        return index;
        }
    }
    
    return -1;
    }
}