package Collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsTest {
    private static Integer arr[] = new Integer[20];
    private static Random rand = new Random();
    static {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(50);
        }
    }
    
    public static void main(String[] args) {
        Set<Integer> treeSet = new TreeSet<Integer>(); 
        for (int i = 0; i < arr.length; i++) {
            treeSet.add(rand.nextInt(100));
        }
        
        Collections.sort(Arrays.asList(arr));
        System.out.println(Arrays.toString(arr));
//      System.out.println(treeSet);
        
        Set<Integer> set = Collections.checkedSet(new HashSet<Integer>(), Integer.class);
        set.add(10);
    }
}
