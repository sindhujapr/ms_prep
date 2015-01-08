package careercup.symantec;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.careercup.com/question?id=14578829
 */
public class SortOddEven {
    public static void main(String[] args) {
        int[] a = new int[10];
        String[] b = new String[10];
        System.out.println(a.getClass());
        System.out.println(b.getClass());
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(10));
        System.out.println(System.identityHashCode(10));
        
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++)
            list.add(i);
        System.out.println(list.subList(3, 5).toString());
        
        list.subList(3, 5).clear();
        System.out.println(list.toString());
    }
    
    public static void sort(int[] array, int start, int end) {
        assert array != null;
        
        if(start >= end)
            return;
        
        int mid = (start+end) / 2;
        sort(array, start, mid);
        sort(array, mid+1, end);
        
        //... this implementation doesn't work
    }
}