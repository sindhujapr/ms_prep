package javautils;

public class Literals {
    /*
     * copied from Integer.java
     */
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
        'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
        'o' , 'p' , 'q' , 'r' , 's' , 't' ,
        'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };

    public static String toUnsignedString(int i, int shift) {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 1 << shift;
        int mask = radix - 1;
        do {
            buf[--charPos] = digits[i & mask];
            i >>>= shift;
        } while (i != 0);

        return new String(buf, charPos, (32 - charPos));
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
    System.out.println(toUnsignedString(4, 1));
    
    int i1 = 0x2f;
    System.out.println("i1: " + Integer.toBinaryString(i1));
    
    int i2 = 0X2F;
    System.out.println("i2: " + Integer.toBinaryString(i2));
    
    // octet
    int i3 = 0177;
    System.out.println("i3: " + Integer.toBinaryString(i3));
    
    char c = 0xffff;
    System.out.println("c: " + Integer.toBinaryString(c));
    
    byte b = 0x7f;
    System.out.println("b: " + Integer.toBinaryString(b));
    
    short s = 0x7fff;
    System.out.println("s: " + Integer.toBinaryString(s));
    
    long n1 = 200L;
    long n2 = 200l;
    long n3 = 200;
    float f1 = 1;
    float f2 = 1F;
    float f3 = 1f;
    double d1 = 1d;
    double d2 = 1D;
    }
}
