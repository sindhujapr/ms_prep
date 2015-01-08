package programming.pearl;

import java.util.Arrays;

public class RotateArray2 {
    public static void main(String[] args) {
        char[] array = "abcdefghij".toCharArray();
        char[] arr = new char[array.length];
        for(int i = 0; i < array.length; i++)
            arr[i] = array[i];
        
        rotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }
    
    public static void rotate(char[] array, int i) {
        int n = array.length;
        assert i < n;
        
        reverse(array, 0, i-1);
        reverse(array, i, n-1);
        reverse(array, 0, n-1);
    }
    
    private static void reverse(char[] array, int i, int j) {
        int mid = (i+j) >>> 1;
        for(int k = i; k <= mid; k++)
            swap(array, k, j-k+i);
    }

    private static void swap(char[] array, int i, int j) {
        if(i == j)
            return;
        
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }
}
