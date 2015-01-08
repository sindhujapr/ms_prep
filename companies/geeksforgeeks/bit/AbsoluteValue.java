package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/compute-the-integer-absolute-value-abs-without-branching/
 */
public class AbsoluteValue {
    public static void main(String[] args) {
        for(int i = 0; i > -100; i--)
            if(abs(i) != Math.abs(i))
                System.out.println(i);
    }
    
    public static int abs(int num) {
        // not >>>
        int mask = num >> 31;
        return (num+mask) ^ mask;
    }
}