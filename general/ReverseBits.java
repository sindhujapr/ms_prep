package general;

/*
 * http://leetcode.com/2011/08/reverse-bits.html
 */
public class ReverseBits {
    public static void print(int bValue) {
    /*
     * Integer.toString(i, radix) doesn't guarantee 32-bit output
     */
    //String bString = Integer.toString(bValue, 2);
    String bString = Integer.toBinaryString(bValue);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 32-bString.length(); i++) {
        sb.append('0');
    }
    sb.append(bString);
    bString = sb.toString();

    for (int i = 0; i < bString.length(); i += 4) {
        System.out.print(bString.substring(i, i+4));
        if(i != bString.length()-4)
        System.out.print('_');
    }
    System.out.println();
    }

    /*
     * this is more efficient according to perf test
     */
    public static int reverse1(int bValue) {
    int value = 0;
    int mask = 1;
    for (int i = 0; i < 32; i++) {
        if((bValue & (mask << i)) != 0)
        value |= mask << (31-i);
        /*
         * if the bit is 0, we don't need to take any action
         */
    }

    return value;
    }
    
    /*
     * use XOR operator. Totally there are 32 bits and we can exchange the values
     * for bit #<i> and bit #<31-i>. So we can finish that in 16 loops.
     */
    public static int reverse2(int bValue) {
    int value = bValue;
    for (int i = 0; i < 16; i++) {
        value = swapBits(value, i, 31-i);
    }
    
    return value;
    }
    
    private static int swapBits(int bValue, int i, int j) {
    int low = (bValue >> i) & 1;
    int high = (bValue >> j) & 1;
    
    if((low ^ high) != 0) {
        bValue ^= (1 << i) | (1 << j);
    }

    return bValue;
    }
    
    /*
     * set the specific bit to 1
     */
    public static void set(int value, int index) {
    print(value);
    value |= 1 << index;
    print(value);
    }
    
    /*
     * set the specific bit to 0
     */
    public static void unset(int value, int index) {
    print(value);
    /*
     * get 111...11011...111
     */
    int i = -1 ^ (1 << index);
    value &= i;
    print(value);
    }

    public static void main(String[] args) {
    int bValue = 0b1001_0010_0011_0100_0101_0110_0111_1001;
    print(bValue);
    
    int v1 = reverse1(bValue);
    int v2 = reverse2(bValue);
    if(v1 != 0 && v1 == v2) {
        print(v1);
    } else {
        System.out.println("error in either reverse1 or reverse2");
    }

    print(-1);
    print(-1 >>> 10);
    print(-1 >> 10);
    
    set(20000, 6);
    unset(20000, 10);
    }
}