package oog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * tourament question:  input is players like（A,B,C,D）, print total rounds[[(A, B), (C, D)], [(A, C), (B, D)], [(A, D), (B, C)]]
 * 
 * Idea:
 * Fix the current element. For the next element, we have <n-i> choices, i is the index of the current element.
 * After that, skip to element at index <i+2>
 */
public class Tournament {
    public static void main(String[] args) {
        tour(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    }

    public static void tour(int[] arr) {
        List<int[]> res = new ArrayList<int[]>();

        tour(arr, 0, res);

        System.out.println(res.size());

        for(int[] A : res)
            System.out.println(Arrays.toString(A));
    }

    private static void tour(int[] arr, int start, List<int[]> res) {
        if(start == arr.length) {
            int[] copy = Arrays.copyOf(arr, arr.length);

            // make it easier for verification
            for(int i = 0; i < arr.length; i+=2)
                if(arr[i] > arr[i+1])
                    swap(copy, i, i+1);

            res.add(copy);
            return;
        }

        for(int i = start+1; i < arr.length; i++) {
            swap(arr, start+1, i);

            tour(arr, start+2, res);

            swap(arr, start+1, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
