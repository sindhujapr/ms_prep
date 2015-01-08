package Sort;

/*
 * heap sort can be used to find the median of an unsorted array:
 * http://stackoverflow.com/questions/10657503/find-running-median-from-a-stream-of-integers
 * 
 * The basic idea is to maintain two heaps, one min heap and one max heap.
 * 
 * Think about another problem http://www.careercup.com/question?id=7779665
 * Given 1000 servers each of which as 1 billion random numbers (unsorted and my duplicate)
 * find the median of these 1000*1 billion numbers.
 * 
 * My thought:
 * 1. for each server, find the median numbers (even not odd).
 * 2. for each two servers, we have four median numbers in total. Drop the smallest one and
 * its entire heap (max heap). Also drop the biggest one and its entire heap (min heap).
 * Here the two medians of these 2 billion numbers must be in the remaining numbers.
 * 3. merge the remaining numbers and find their median numbers.
 * 4. Do this recursively until we have only 1 billion numbers and find their median numbers.
 * 
 * By using max/min heap, it takes no additional space but the time complexity is O(nlgn)
 * because we need to iterate over the entire array (1 billion number) and for each element,
 * we need to heapify the max or min heap.
 * An alternative is to use quick selection. Thus for each array (1 billion number), we're
 * able to find the median in O(n) time complexity also without additional space. Use the same
 * idea to drop the smaller part and bigger part, then merge the remaining part.
 */
public class HeapSort {
    public static int parent(int index) {
        return (index - 1) / 2;
    }

    public static int left(int index) {
        return 2 * index + 1;
    }

    public static int right(int index) {
        return 2 * index + 2;
    }

    public static void swap(int arr[], int i, int j) {
        // ^= doesn't work if i and j point to the same location
        if (arr[i] == arr[j])
            return;

        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void maxHeapify(int arr[], int i, int limit) {
        int l = left(i);
        int r = right(i);
        
        int largest = (l < limit && arr[l] > arr[i]) ? l : i;
        largest = (r < limit && arr[r] > arr[largest]) ? r : largest;

        if (largest != i) {
            swap(arr, largest, i);
            // use the same boundary as passed
            maxHeapify(arr, largest, limit);
        }
    }

    public static void buildMaxHeap(int arr[]) {
        // it's ok to start from the last element arr.length-1;
        int end = (arr.length - 2) / 2;
        for (int i = end; i >= 0; i--) {
            // boundary is arr.length!
            maxHeapify(arr, i, arr.length);
        }
    }

    public static void heapSort(int arr[]) {
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            // boundary is i!
            maxHeapify(arr, 0, i);
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 6, 5, 12, 11};
        heapSort(arr);
        for (int i : arr)
            System.out.print(i + " ");
    }
}