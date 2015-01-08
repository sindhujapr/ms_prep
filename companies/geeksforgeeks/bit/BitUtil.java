package geeksforgeeks.bit;

public class BitUtil {
    private static int INT_BIT_COUNT = 1 << 5;

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
    
    public static String convertIntToBitsWithPadding(int num) {
        StringBuilder builder = new StringBuilder(convertIntToBits(num));
        int length = INT_BIT_COUNT-builder.length();
        for(int i = 0; i < length; i++)
            builder.insert(0, '0');

        return builder.toString();
    }

    /*
     * return the right most part that has the highest bit set
     */
    public static int rightMostSetBit(int num) {
        return num & -num;
    }
    
    public static int unsetRightMostSetBit(int num) {
        return num & (num-1);
    }
    
    // num % mod
    public static int modulus(int num, int mod) {
        return num & (mod-1);
    }
    
    public static void main(String[] args) {
        System.out.println(convertIntToBitsWithPadding(10));
        System.out.println(convertIntToBitsWithPadding(~10));
        System.out.println(convertIntToBitsWithPadding(-10));
    }
}