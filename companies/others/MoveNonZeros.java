package test;

import java.util.Arrays;

/**
 * Write an algorithm that brings all nonzero elements to the left of the array, and returns the number of nonzero elements.

 Example input: [ 1, 0, 2, 0, 0, 3, 4 ]
 Example output: 4
 */
public class MoveNonZeros {
    public static int move(int[] arr) {
        int size = 0;
        for(int i = 0; i < arr.length; i++)
            if(arr[i] != 0)
                swap(arr, i, size++);

        System.out.println(Arrays.toString(arr));
        return size;
    }

    private static void swap(int[] arr, int i, int j) {
        if(i == j)
            return;
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void main(String[] args) {
        move(new int[] {1, 0, 2, 0, 0, 3, 4});
    }
}
