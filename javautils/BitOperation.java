package javautils;

import java.util.BitSet;

/*
 * we have the below bit operations:
 * &, ^, |, ~, >>, >>>, <<
 * 
 * BitSet is convenient for bit operation
 */
public class BitOperation {
    public static void main(String[] args) {
    int value = 4;
    System.out.println(setBit(value, 1));
    
    System.out.println(unsetBit(value, 2));
    
    System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >> 1));
    System.out.println(Integer.toBinaryString(Integer.MIN_VALUE >>> 10));
    
    BitSet bSet1 = new BitSet(20);
    bSet1.set(2, 10, true);
    System.out.println(bSet1);
    
    BitSet bSet2 = new BitSet(40);
    bSet2.set(5, 30);
    System.out.println(bSet2);
    
    bSet1.xor(bSet2);
    System.out.println(bSet1);
    
    System.out.println(bSet1.get(20));
    bSet1.clear(20, 40);
    System.out.println(bSet1);
    }
    
    public static void print(BitSet bSet) {
    System.out.print(bSet + "\t");
    for (int i = bSet.length()-1; i >=0; i--) {
        if(bSet.get(i))
        System.out.print('1');
        else
        System.out.print('0');
        if(i % 4 == 0)
        System.out.print(' ');
    }
    }
    
    public static int setBit(int value, int index) {
    value |= 1 << index;
    return value;
    }
    
    public static int unsetBit(int value, int index) {
    value &= ~(1 << index);
    return value;
    }
}