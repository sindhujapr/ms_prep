package search.sorting;

import java.util.Arrays;

/*
 * Recursive and iterative implementation
 * http://www.geeksforgeeks.org/iterative-quick-sort/
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = {6, 5, 4, 3, 2, 1};
        recur_quickSort(arr1, 0, 5);
        System.out.println(Arrays.toString(arr1));
        
        int[] arr2 = {6, 5, 4, 3, 2, 1};
        iterative_quickSort(arr2, 0, 5);
        System.out.println(Arrays.toString(arr2));
        
    }

    /*
     * use first element as pivot
     */
    public static void qsort_first_pivot(int[] array, int l, int h) {
        if(l >= h)
            return;

        int j = l;
        for(int i = l+1; i <= h; i++)
            if(array[i] < array[l])
                swap(array, ++j, i);

        swap(array, j, l);
        
        qsort_first_pivot(array, l, j-1);
        qsort_first_pivot(array, j+1, h);
    }

    /*
     * use middle element as pivot
     */
    public static void qsort_mid_pivot(int[] array, int l, int h) {
        if(l >= h)
            return;
        
        int mid = (l+h) >>> 1;
        int i = mid-1, j = mid+1;
        while(i >= l && j <= h) {
            while(i >= l && array[i] < array[mid])
                i--;
            while(j <= h && array[j] > array[mid])
                j++;

            if(i >= l && j <= h)
                swap(array, i--, j++);
            else
                break;
        }
        
        // deal with remaining elements in either left or right half
        for(; i >= l; i--)
            if(array[i] > array[mid])
                swap(array, i, mid--);
        
        for(; j <= h; j++)
            if(array[j] < array[mid])
                swap(array, j, mid++);
        
        qsort_mid_pivot(array, l, mid-1);
        qsort_mid_pivot(array, mid+1, h);
    }
    
    public static void qsort_last_pivot(int[] array, int l, int h) {
        if(l >= h)
            return;

        int j = h;
        for(int i = h-1; i >= l; i--) {
            if(array[i] >= array[h])
                swap(array, i, --j);
        }
        
        swap(array, j, h);
        qsort_last_pivot(array, l, j-1);
        qsort_last_pivot(array, j+1, h);
    }
    
    public static void recur_quickSort(int[] array, int start, int end) {
        if(start >= end)
            return;
        
        int pos = partition1(array, start, end);
        recur_quickSort(array, start, pos-1);
        recur_quickSort(array, pos+1, end);
    }
    
    public static void iterative_quickSort(int[] array, int start, int end) {
        int[] stack = new int[end-start+1];
        int top = 0;
        
        stack[top++] = start;
        stack[top++] = end;
        while(top > 0) {
            int high = stack[--top];
            int low = stack[--top];
            
            int pos = partition1(array, low, high);
            
            if(pos > low+1) {
                stack[top++] = low;
                stack[top++] = pos-1;
            }
            
            if(pos+1 < high) {
                stack[top++] = pos+1;
                stack[top++] = high;
            }
        }
    }

    // CLR: pick last element as pivot
    @SuppressWarnings("unused")
    private static int partition1(int[] array, int start, int end) {
        int x = array[end];
        int i = start-1;
        // keep pivot untouched
        for(int j = start; j < end; j++) {
            if(array[j] < x) {
                i++;
                swap(array, i, j);
            }
        }
        
        swap(array, i+1, end);
        return i+1;
    }
    
    @SuppressWarnings("unused")
    private static int partition3(int[] array, int start, int end) {
        int x = array[end];
        while(start < end) {
            
            while(start < end && array[start] < x)
                start++;
            if(start < end)
                array[end--] = array[start];

            while(start < end && array[end] > x)
                end--;
            if(start < end)
                array[start++] = array[end];
        }
        array[start] = x;
        
        return start;
    }
    
    private static void swap(int[] array, int i, int j) {
        if(i == j)
            return;
        
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}