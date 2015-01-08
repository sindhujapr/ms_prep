package javautils;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class EnumMapTest {
    enum Hurb {
        ONE, TWO, THREE, FOUR, FIVE;
    }

    @SuppressWarnings("unused")
    private final Hurb type;
    private final int value;

    public EnumMapTest(Hurb type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
    /*
     * EnumSet provides some static methods
     */
    Set<Hurb> ops = EnumSet.allOf(Hurb.class);
    for(Hurb op : ops)
        System.out.print(op.ordinal() + " ");
    System.out.println();

    
    /*
     * EnumMap must be initialized with the type of the key. It's better
     * that we use enum along with map to ensure type checking. Index
     * along with array is considered unsafe and could easily results
     * in index out of bound.
     */
    Map<Hurb, Set<EnumMapTest>> map = new EnumMap<Hurb, Set<EnumMapTest>>(Hurb.class);
    for(Hurb op : Hurb.values())
        map.put(op, new HashSet<EnumMapTest>());
    
    Random rand = new Random();
    Hurb[] values = Hurb.values();
    for (int i = 0; i < 10; i++) {
        Hurb hb = values[rand.nextInt(values.length)];
        map.get(hb).add(new EnumMapTest(hb, i));
    }

    for(Hurb hb : values) {
        Set<EnumMapTest> set = map.get(hb);
        System.out.print(hb + "\t");
        for(EnumMapTest instance : set) {
        System.out.print(instance.getValue() + " ");
        }
        System.out.println();
    }
    }
}