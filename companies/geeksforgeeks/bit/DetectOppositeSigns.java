package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/detect-if-two-integers-have-opposite-signs/
 */
public class DetectOppositeSigns {
    public static void main(String[] args) {
        System.out.println(oppositeSigns(10, -2));
        System.out.println(oppositeSigns(2, 5));
        System.out.println(oppositeSigns(-2, -3));
        
        System.out.println(Integer.MAX_VALUE);
        // Math.abs(Integer.MIN_VALUE) causes underflow
        System.out.println(Math.abs(Integer.MIN_VALUE+1));
    }
    
    public static boolean oppositeSigns(int i, int j) {
//      return (isNegative(i) && isPositive(j)) || (isNegative(j) && isPositive(i)); 
        return (i ^ j) >>> 31 == 1;
    }
    
    @SuppressWarnings("unused")
    private static boolean isNegative(int x) {
//      return ((x & (1 << 31)) >>> 31) == 1;
        return (x & (1 << 31)) == Integer.MIN_VALUE;
    }
    
    @SuppressWarnings("unused")
    private static boolean isPositive(int x) {
        return (x & (1 << 31)) == 0;
    }
}