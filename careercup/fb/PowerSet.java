package careercup.fb;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=6189585818189824
 * see SubsetsII.java
 */
public class PowerSet {
    public static void main(String[] args) {
        int[] array = {1, 2, 2};
        powerSet(array);
    }

    public static void powerSet(int[] array) {
        qsort(array, 0, array.length-1);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<List<Integer>> prev = new ArrayList<List<Integer>>();

        for(int i = 0; i < array.length; i++) {
            for(List<Integer> set : result)
                set.add(array[i]);
            
            // avoid redundancy
            if(i == 0 || array[i] != array[i-1]) {
                List<Integer> single = new ArrayList<Integer>();
                single.add(array[i]);
                prev.add(single);
            }

            for(List<Integer> set : prev)
                result.add(new ArrayList<Integer>(set));
        }
        result.add(new ArrayList<Integer>());
        
        for(List<Integer> set : result)
            System.out.println(set);
    }

    private static void qsort(int[] array, int low, int high) {
        if(low >= high)
            return;

        int j = high-1;
        for(int i = high-2; i >= 0; i--)
            if(array[i] > array[high])
                swap(array, i, --j);
        swap(array, j, high);
        qsort(array, low, j-1);
        qsort(array, j+1, high);
    }
    
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}