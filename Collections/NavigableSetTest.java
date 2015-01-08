package Collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class NavigableSetTest {
    private static Integer arr[] = new Integer[20];
    private static Random rand = new Random();
    static {
    for (int i = 0; i < arr.length; i++) {
        arr[i] = rand.nextInt(50);
    }
    }

    public static void main(String[] args) {
    NavigableSet<Integer> nSet = new ConcurrentSkipListSet<Integer>();
    for (int i = 0; i < arr.length; i++) {
        nSet.add(rand.nextInt(100));
        System.out.println(nSet);
    }
    System.out.println(nSet);

    System.out.println("Less than 20: " + nSet.lower(20));
    System.out.println("Less than or equal to 30: " + nSet.floor(30));
    System.out.println("Greater than or equal to 40: " + nSet.ceiling(40));
    System.out.println("Greater than 50: " + nSet.higher(50));

    System.out.println("Elements less than 30: " + nSet.headSet(30, true));
    System.out.println("First element: " + nSet.first());
    System.out.println("Last element: " + nSet.last());
    
    System.out.println(Math.round((double)5/3));
    }
}
