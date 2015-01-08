package test;

import java.util.Arrays;

/**
 * http://www.careercup.com/question?id=4909367207919616
 *
 * WAP to modify the array such that arr[I] = arr[arr[I]].
 Do this in place i.e. with out using additional memory.

 example : if a = {2,3,1,0}
 o/p = a = {1,0,3,2}

 Note : The array contains 0 to n-1 integers.
 * Created by qingcunz on 11/9/14.
 */
public class RelocateElements {
    public static void main(String[] args) {
        int[] arr = new int[] {2, 3, 1, 0};
        relocate(arr);

        System.out.print(Arrays.toString(arr));
    }

    public static void relocate(int[] arr) {
        int size = arr.length;
        for(int i = 0; i < size; i++) {
            arr[i] += (arr[arr[i]] % size) * size;
            System.out.print(arr[i] + "\t");
        }

        for(int i = 0; i < size; i++)
            arr[i] /= size;
    }
}
