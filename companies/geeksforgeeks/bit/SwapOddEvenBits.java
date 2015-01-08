package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/swap-all-odd-and-even-bits/
 */
public class SwapOddEvenBits {
    public static void main(String[] args) {
        System.out.println(BitUtil.convertIntToBitsWithPadding(17));
        System.out.println(BitUtil.convertIntToBitsWithPadding(swap2(17)));
    }
    
    public static int swap(int num) {
        int offset = 0;
        int res = 0;
        while(num != 0) {
            int b1 = num & 1;
            int b2 = num & 2;
            
            res |= (b2 << offset) | (b1 << (offset+1));
            offset += 2;
            num >>>= 2;
        }
        
        return res;
    }
    
    public static int swap2(int num) {
        int oddBits = num & 0xAAAAAAAA;
        int evenBits = num & 0x55555555; 
        
        return (oddBits >>> 1) | (evenBits << 1);
    }
}