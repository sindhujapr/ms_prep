package test;

/**
 * Created by qingcunz on 9/15/14.
 */
public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(!(new RuntimeException("hello") instanceof RuntimeException));
        System.out.println(Integer.MAX_VALUE);
        int root = new SquareRoot().sqrt(2147395599);
        System.out.println(root);
    }

    public int sqrt(int x) {
        if(x == 0 || x == 1)
            return x;

        int start = 1, end = (1 << 16)-1;
        while(start <= end) {
            int mid = (start+end) >> 1;
            int val = mid * mid;
            int val1 = (mid+1) * (mid+1);

            if(val <= x && val1 > x)
                return mid;
            else if(val <= x)
                start = mid+1;
            else
                end = mid-1;
        }

        return 1;
    }
}
