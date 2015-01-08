package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/program-to-count-number-of-set-bits-in-an-big-array/
 */
public class CountArraySetBits {
    public static void main(String[] args) {
        int[] array = {-1, 3};
        System.out.println(count(array));
    }

    public static int count(int[] array) {
        // each element represents the number of set bits for 4 bits
        int[] count = new int[1 << 8];
        for(int i = 0; i < 1 << 8; i++)
            count[i] = countBits(i);
        
        int cnt = 0;
        int complement = (1 << 8) - 1;
        for(int i = 0; i < array.length; i++) {
            int num = array[i];
            cnt += count[num & complement];
            /*
             * we should use >>> instead of >>. 
             * Otherwise the sign (leading 1) will be counted twice
             */
            cnt += count[(num >>> 8) & complement];
            cnt += count[(num >>> 16) & complement];
            cnt += count[(num >>> 24) & complement];
        }
        
        return cnt;
    }
    
    // i could be positive or negative
    private static int countBits(int i) {
        int count = 0;
        while(i != 0) {
            i &= i-1;
            count++;
        }
        return count;
    }
}