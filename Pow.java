package lc;

import java.util.HashMap;
import java.util.Map;

public class Pow {
    public static void main(String[] args) {
        System.out.println(new Pow().pow1(2, Integer.MIN_VALUE));
    }

    /*
     * It seems that the test cases in Leetcode don't cover this:
     * x = non 1
     * n = Integer.MIN_VALUE
     * Actually both pow() and pow1() will fail for this case
     */
    public double pow(double x, int n) {
        if (n == 0)
            return 1.;
        if (n < 0) {
            // if n is Integer.MIN_VALUE, -n is also Integer.MIN_VALUE
            n = -n;
            x = 1. / x;
        }
        double temp = pow(x, n / 2);
        return n % 2 == 0 ? temp * temp : temp * temp * x;
    }

    public double pow1(double x, int n) {
        if (n == 0)
            return 1.;
        if (x == 0)
            return 0.;
        // this will not work if n is Integer.MIN_VALUE and x is not 1
        if (n < 0) {
            n = -n;
            x = 1. / x;
        }

        double res = 1.;
        while (n > 0) {
            if (n % 2 == 1)
                res *= x;
            x *= x;
            n >>= 1;
        }
        return res;
    }

    public double pow2(double x, int n) {
        if (n == 0)
            return 1.0000;
        boolean flag = n > 0 ? true : false;
        n = Math.abs(n);

        long i = 1;
        Map<Long, Double> map = new HashMap<Long, Double>();
        double value = x;
        while (i <= (long) n) {
            map.put(i, value);
            value = value * value;
            i <<= 1;
        }

        double result = 1;
        for (i = i >>> 1; n > 0; i >>>= 1) {
            if (i <= n) {
                result *= map.get(i);
                n -= i;
            }
        }

        if (flag)
            return result;
        else
            return 1 / result;
    }
}