package javautils;

public class CloneTest {
    public static void main(String[] args) {
    Integer[] ints = new Integer[1024];
    for (int i = 0; i < ints.length; i++) {
        ints[i] = new Integer(i+1);     
    }

//  List<Integer> list = Collections.unmodifiableList(Arrays.asList(ints));
//  System.out.println(list);
    Integer[] ints1 = ints.clone();
//  System.out.println(Arrays.toString(ints1));
    /*
     * Integer doesn't implement Cloneable and also doesn't provide
     * deep copy. So all the elements in the new array remain the same.
     */
    for (int i = 0; i < ints1.length; i++) {
        if(ints1[i] != ints[i])
        System.out.println("not equal");
    }

    System.out.println(new Integer(1) == new Integer(1));
    }
}
