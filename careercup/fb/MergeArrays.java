package careercup.fb;

import java.util.Arrays;

/*
 * http://www.careercup.com/question?id=5435439490007040
 */
public class MergeArrays {
    public static void main(String[] args) {
        int[] a = {1, 3, 7,8};
        int[] array = {2, 5, 6, 9};
        int[] b = new int[array.length * 2];
        for(int i = 0; i < array.length; i++)
            b[i] = array[i];
        
        merge(a, b);
        System.out.println(Arrays.toString(b));
    }

    public static void merge(int[] a, int[] b) {
        for(int i = b.length-1, j = a.length-1, k = a.length-1; j >= 0 || k >= 0;) {
            /*
             * this is not very good since it's possible that Integer.MIN_VALUE exists
             * in the array
             */
            int v1 = j >= 0 ? a[j] : Integer.MIN_VALUE;
            int v2 = k >= 0 ? b[k] : Integer.MIN_VALUE;
            
            if(v1 > v2)
                b[i--] = a[j--];
            else 
                b[i--] = b[k--];
        }
    }
}
