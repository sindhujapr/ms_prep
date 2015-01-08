package Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Differences between Comparable and Comparator:
 * 1) Comparable is implemented by class that needs to be sorted by Arrays or Collections
 * 2) Comparator can be passed to Arrays or Collections when sorting
 */
public class ComparableElement implements Comparable<ComparableElement> {
    private int v1;
    private int v2;

    public ComparableElement(int v1, int v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    /*
     * For int values, we can use > or < operator. For double and float, we
     * should use Double.compare and Float.compare
     */
    @Override
    public int compareTo(ComparableElement o) {
        /*
         * Caution!! Be sure there is no overflow or underflow. Otherwise, we
         * should use the commented code.
         */
        return v1 - o.v1;
        // if (v1 > o.v1)
        // return 1;
        // else if (v1 == o.v1)
        // return 0;
        // else
        // return -1;
    }

    /**
     * As part of the Comparable contract: It is strongly recommended, but not
     * strictly required that (x.compareTo(y)==0) == (x.equals(y)). Generally
     * speaking, any class that implements the Comparable interface and violates
     * this condition should clearly indicate this fact. The recommended
     * language is
     * "Note: this class has a natural ordering that is inconsistent with equals."
     */
    @Override
    public boolean equals(Object o) {
        return (o instanceof ComparableElement) && (((ComparableElement) o).v1 == v1);
    }

    @Override
    public int hashCode() {
        return 31 * v1;
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", v1, v2);
    }

    public static void main(String[] args) {
        ComparableElement[] array = new ComparableElement[10];
        List<ComparableElement> list = new LinkedList<ComparableElement>();

//      ThreadLocalRandom rand = ThreadLocalRandom.current();
//      for (int i = 0; i < array.length; i++) {
//          ComparableElement e = new ComparableElement(rand.nextInt(1, 100), rand.nextInt(1000));
//          array[i] = e;
//          list.add(e);
//      }

        /*
         * if the class of the elements that in the array or collection don't
         * implement Comparable interface, we can also pass a Comparator
         * instance to sort() method.
         */
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        Collections.sort(list);
        System.out.println(list);
    }
}