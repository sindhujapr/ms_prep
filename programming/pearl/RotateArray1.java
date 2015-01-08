package programming.pearl;

import java.util.Arrays;

/*
 * Column 2 page 14
 */
public class RotateArray1 {
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
        
        char val = array[0];

        int j = 1;
        while(true) {
            if(j*i % n == 0) {
                array[i * (j-1) % n] = val;
                break;
            }
            
            array[i * (j-1) % n] = array[i * j % n];
            j++;
        }
    }
}