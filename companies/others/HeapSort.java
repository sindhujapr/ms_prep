package google;

import java.util.Arrays;

/**
 * Created by qingcunz on 12/28/14.
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[] {3, 9, 1, 8, 7, 6, 4, 5, 0, 2};
        new HeapSort().sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        heapify(arr);

        for(int i = arr.length-1; i > 0; i--) {
            swap(arr, i, 0);
            adjust(arr, 0, i);
        }
    }


    private void heapify(int[] arr) {
        for(int i = arr.length-1; i >= 0; i--)
            adjust(arr, i, arr.length);
    }

    private void adjust(int[] arr, int i, int limit) {
        int index = i;
        index = (2*i+1) < limit && arr[2*i+1] > arr[index] ? 2*i+1 : index;
        index = (2*i+2) < limit && arr[2*i+2] > arr[index] ? 2*i+2 : index;

        if(index != i) {
            swap(arr, index, i);
            adjust(arr, index, limit);
        }
    }

    private int left(int i) { return 2*i+1; }
    private int right(int i) { return 2*i+2; }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
