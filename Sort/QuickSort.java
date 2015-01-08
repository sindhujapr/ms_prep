package Sort;

import java.util.Random;

public class QuickSort {
    public static int partition(int arr[], int low, int high) {
        int pivot = arr[low]; // pivot value
        while (low < high) {
            while (low < high && arr[high] >= pivot)
                high--;
            if (low < high)
                arr[low++] = arr[high];

            while (low < high && arr[low] <= pivot)
                low++;
            if (low < high)
                arr[high--] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static int partitionCLR(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                // i++;
                swap(arr, ++i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int arr[], int i, int j) {
        // ^= doesn't work if i and j point to the same location
        if (arr[i] == arr[j])
            return;

        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void qsort(int arr[], int low, int high) {
        if (low < high) {
            int pivotLoc = partitionCLR(arr, low, high);

            // System.out.print("[");
            // for(int i = low; i < pivotLoc; i++)
            // System.out.print(arr[i] + "(" + i + ") ");
            // System.out.print("]\t[" + arr[pivotLoc] + "(" + pivotLoc +
            // ")]\t[");
            // for(int i = pivotLoc + 1; i <= high; i++)
            // System.out.print(arr[i] + "(" + i + ") ");
            // System.out.println("]");

            qsort(arr, low, pivotLoc - 1);
            qsort(arr, pivotLoc + 1, high);
        }
    }

    public static void checkSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("wrong " + arr[i] + " " + arr[i + 1]);
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int arr[] = new int[100];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(arr.length << 1);
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // int arr[] = {2, 8, 7, 1, 3, 5, 6, 4};
        qsort(arr, 0, arr.length - 1);
        checkSort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
}