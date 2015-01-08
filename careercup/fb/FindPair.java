package careercup.fb;

import java.util.HashMap;
import java.util.Map;

/*
 * http://www.careercup.com/question?id=5761467236220928
 */
public class FindPair {
    public static void main(String[] args) {
        int[] array = {3, 7, 4, 2, 5, 1, 6};
        findPair2(array, 13);
//      findPair1(array, 10);
    }
    
    // O(nlgn)
    public static void findPair1(int[] array, int k) {
        qsort(array, 0, array.length-1);
        
        for(int i = 0, j = array.length-1; i < j;) {
            if(array[i] + array[j] > k)
                j--;
            else if(array[i] + array[j] == k) {
                System.out.println(array[i] + "\t" + array[j]);
                j--;
                i++;
            } else
                i++;
        }
    }
    
    private static void qsort(int[] array, int low, int high) {
        if(low >= high)
            return;

        int k = low;
        for(int i = low+1; i <= high; i++) {
            if(array[i] < array[low])
                swap(array, i, ++k);
        }
        swap(array, low, k);
        
        qsort(array, low, k-1);
        qsort(array, k+1, high);
    }
    
    public static void swap(int[] array, int i, int j) {
        if(i == j)
            return;
        array[i] ^= array[j];
        array[j] ^= array[i];
        array[i] ^= array[j];
    }

    public static void findPair2(int[] array, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < array.length; i++) {
            int other = k - array[i];
            if(map.containsKey(other))
                System.out.println(array[map.get(other)] + "\t" + array[i]);
            
            map.put(array[i], i);
        }
    }
}