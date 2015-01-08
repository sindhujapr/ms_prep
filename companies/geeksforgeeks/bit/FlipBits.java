package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/count-number-of-bits-to-be-flipped-to-convert-a-to-b/
 */
public class FlipBits {
    public static void main(String[] args) {
        System.out.println(flip(10, 8));
    }
    
    public static int flip(int x, int y) {
        int z = x ^ y;
        return count(z);
    }
    
    /*
     * see CountsSetBits.java
     */
    private static int count(int z) {
        int n = 0;
        while(z > 0) {
            z &= z-1;
            n++;
        }
        return n;
    }
}
