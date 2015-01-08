package test;

/**
 * Created by qingcunz on 10/8/14.
 */
public class FlipBits {
    public static void main(String[] args) {
        System.out.println(Boolean.valueOf(true).hashCode());
        System.out.println(new Integer(5).equals(null));
        System.out.println(FlipBits.isWinner(10));
    }

    public static boolean isWinner(int n) {
        int sum = 0;
        int zeros = 0;
        while (n > 0) {
            if ((n & 1) != 0)
                sum += zeros;
            else
                zeros++;

            n >>= 1;
        }

        // true - first player win, false - second player win
        return (sum & 1) != 0 ? true : false;
    }
}
