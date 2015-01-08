package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/
 */
public class CountBits {
    public static void main(String[] args) {
         for(int i = 1; i <= 10; i++)
         System.out.println(countBits(i));
    }

    /*
     * for 2^n-1, there are totally n * (2 ^ (n-1)) ones
     */
    public static int countBits(int n) {
        assert n > 0;

        int count = 0;
        while (n > 0) {
            int length = getLength(n);
            int closest = (1 << length - 1) - 1;
            count += (length - 1) * (1 << (length - 2)) + (n - closest);
            n -= closest + 1;
        }

        return count;
    }

    private static int getLength(int n) {
        // total length in binary
        int length = 0;
        while (n > 0) {
            length++;
            n = n >>> 1;
        }
        return length;
    }
}