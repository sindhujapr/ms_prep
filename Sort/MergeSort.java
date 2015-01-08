package Sort;

import java.util.Arrays;

public class MergeSort {
    public static void mSort(int src[], int dst[], int low, int high) {
        int dst2[] = new int[high + 1];
        if(low == high) {
            dst[low] = src[low];
        } else {
            int m = (high + low) /2 ;
            mSort(src, dst2, low, m);
            mSort(src, dst2, m + 1, high);
            
            for(int i = low, j = m+1, o = low; i <= m || j <= high;) {
                if(i > m) {
                    while(j <= high)
                        dst[o++] = src[j++];
                    break;
                }
                if(j > high) {
                    while(i <= m)
                        dst[o++] = src[i++];
                    break;
                }
                
                if(src[i] < src[j])
                    dst[o++] = src[i++];
                else
                    dst[o++] = src[j++];
            }
            
            for(int i = low; i <= high; i++)
                src[i] = dst[i];
        }
    }
    
    public static void main(String[] args) {
        int arr1[] = {2, 5, 7, 9, 1, 3, 4, 6, 8, 10, 0, 11, 14, 12, 13};
        int arr2[] = new int[arr1.length];
        System.out.println(Arrays.toString(arr1));
        
        mSort(arr1, arr2, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr2));
    }
}
