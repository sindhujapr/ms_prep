package miscellaneous;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * http://www.careercup.com/question?id=6085804375932928
 */
public class GenericComparator<T> implements Comparator<T> {

    private String property;

    public GenericComparator(String property) {
        this.property = property;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(Object o1, Object o2) {
        @SuppressWarnings({"rawtypes"})
        Comparable o1Comparable = getComparable(o1);
        @SuppressWarnings({"rawtypes"})
        Comparable o2Comparable = getComparable(o2);
        return o1Comparable.compareTo(o2Comparable);
    }

    @SuppressWarnings("rawtypes")
    private Comparable getComparable(Object o) {
        try {
            Object invoke = o.getClass().getMethod(property, new Class[] {}).invoke(o, new Class[] {});
            return ((Comparable)invoke);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String s1 = "david";
        String s2 = "adam";
        List<String> list = new ArrayList<String>();
        list.add(s1);
        list.add(s2);
        System.out.println(list);
        java.util.Collections.sort(list, new GenericComparator<String>("getName"));
        System.out.println("After sort :" + list);
    }
}
