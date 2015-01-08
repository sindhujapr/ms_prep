package search.sorting;

import java.util.Arrays;

/*
 * dutch national flag
 * 
 * http://www.careercup.com/question?id=18824667
 * http://blog.teamleadnet.com/search/label/Performance
 */
public class SortList {
    public static void main(String[] args) {
        int[] a = new int[] { 1, 3, 2, 2, 3, 1, 1, 1, 3, 2, 2, 1, 1 };
        dnf(a, 2, 2);
        System.out.println(Arrays.toString(a));
        
        a = new int[] { 1, 3, 2, 2, 3, 5, 4, 2, 3, 1, 3, 2, 2, 1, 1 };
        dnf(a, 2, 3);
        System.out.println(Arrays.toString(a));
    }

    public static void dnf(int[] a, int low, int high) {
        int startIndex = 0;
        int endIndex = a.length - 1;

        for (int i = 0; i <= endIndex;) {
            if (a[i] < low)
                swap(a, i++, startIndex++);
            else if (a[i] > high)
                // do not increment i. We have to revisit this again
                swap(a, i, endIndex--);
            else
                i++;
        }
    }
    
    private static void swap(int[] array, int i, int j) {
        if(i == j)
            return;

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}