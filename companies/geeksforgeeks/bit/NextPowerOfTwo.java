package geeksforgeeks.bit;

/*
 * http://www.geeksforgeeks.org/next-power-of-2/
 */
public class NextPowerOfTwo {
    public static void main(String[] args) {
        System.out.println(next(16));
        System.out.println(next2(16));
    }
    
    public static int next(int num) {
        assert num > 0;

        int res = 1;
        while(res < num)
            res <<= 1;
        return res;
    }
    
    public static int next2(int num) {
        assert num > 0;

        int count = 0;
        while(num != 0) {
            num >>= 1;
            count++;
        }
        
        return 1 << count;
    }
}