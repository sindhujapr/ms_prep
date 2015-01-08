package Collections;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetTest {
    private static int arr[] = new int[20];
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
        
        System.out.println(treeSet);
    }
}
