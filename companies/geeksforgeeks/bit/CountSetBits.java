package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/program-to-count-number-of-set-bits-in-an-big-array/
 * http://www.geeksforgeeks.org/count-set-bits-in-an-integer/
 * 
 * This seems not divide and conquer
 */
public class CountSetBits {
    public static void main(String[] args) {
        System.out.println(countBits(8));
        System.out.println(countBits(3));
        
        System.out.println(countBits(23));
    }
    
    public static int countBits(int value) {
        int count = 0;
        while(value != 0) {
            value &= value-1;
            count++;
        }
        
        return count;
    }
}
