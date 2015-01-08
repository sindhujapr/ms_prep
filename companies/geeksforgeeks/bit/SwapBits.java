package geeksforgeeks.bit;

import java.util.BitSet;

/*
 * http://www.geeksforgeeks.org/swap-bits-in-a-given-number/
 */
public class SwapBits {
    public static void main(String[] args) {
        int num = 210002323;
//      System.out.println(convertIntToBits(num) + "\t" + convertIntToBitSet(num));
//      System.out.println(convertIntToBits(-7) + "\t" + convertIntToBitSet(-7));
//      System.out.println(convertIntToBits(-36) + "\t" + convertIntToBitSet(-36));
//      System.out.println(convertIntToBits(100) + "\t" + convertIntToBitSet(100));
//      num = swapBits(num, 0, 4, 2);

//      System.out.println(convertIntToBits(num) + "\t" + convertIntToBitSet(num));
        
        System.out.println(convertIntToBits(47));
        num = swapBits(47, 1, 5, 3);
        System.out.println(convertIntToBits(num) + "\t" + convertIntToBitSet(num));
        num = swapBits2(47, 1, 5, 3);
        System.out.println(convertIntToBits(num) + "\t" + convertIntToBitSet(num));
    }

    public static int swapBits(int num, int p1, int p2, int n) {
        assert p1 + n < p2 && p2 < 32;

        for (int i = 0; i < n; i++) {
            int n1 = num & (1 << p1 + i);
            int n2 = num & (1 << p2 + i);
            if (n1 > 0 && n2 == 0) {
                num = unset(num, p1 + i);
                num = set(num, p2 + i);
            } else if (n1 == 0 && n2 > 0) {
                num = unset(num, p2 + i);
                num = set(num, p1 + i);
            }
        }

        return num;
    }
    
    public static int swapBits2(int num, int p1, int p2, int n) {
        assert p1 + n < p2 && p2 < 32;

        // we have to use parenthesis here due to operator precedence!!
        int complement = (1 << n) -1;
        int n1 = (num >> p1) & complement;
        int n2 = (num >> p2) & complement;
        
        int mask = n1 ^ n2;
        
        num ^= mask << p1;
        num ^= mask << p2;
        return num;
//      mask = (mask << p1) | (mask << p2);
//      return num ^ mask;
    }
    
    public static int swapBits3(int num, int p1, int p2, int n) {
        // keep only the lower n bits after XOR operation
        int mask = ((num >> p1) ^ (num >> p2)) & ((1 << n) - 1);
        return num ^ ((mask << p1) | (mask << p2));
    }

    private static int unset(int num, int n) {
        int i = ~0;
        return num & (i ^ (1 << n));
    }

    private static int set(int num, int n) {
        return num | (1 << n);
    }

    public static String convertIntToBits(int num) {
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            if ((num & 0x1) == 1)
                builder.insert(0, '1');
            else
                builder.insert(0, '0');
            num >>>= 1;
        }
        return builder.toString();
    }

    public static String convertIntToBitSet(int num) {
        BitSet bset = new BitSet();
        int index = 0;
        /*
         * we shouldn't use "num != 0" instead of "num > 0" to 
         * support both positive and negative numbers
         */
        while (num != 0) {
            if ((num & 0x1) == 1)
                bset.set(index);

            index++;
            // we should use >>> instead of >>
            num >>>= 1;
        }
        return bset.toString();
    }
    
    public static int convertBitSetToInt(BitSet bset) {
        int result = 0;
        
        return result;
    }
}