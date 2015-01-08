package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qingcunz on 12/16/14.
 */
public class FractionToDecimal {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(7, -12));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0)
            return "0";

        boolean sign = (numerator > 0 && denominator > 0) || (numerator < 0 && denominator < 0);
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        Map<Long, Integer> map = new HashMap<Long, Integer>();
        String left = Long.toString(num/den);
        if (!sign)
            left = "-" + left;

        long rem = num % den;
        if (rem == 0)
            return left;

        left += ".";
        StringBuilder right = new StringBuilder();
        for (int pos = 0; rem != 0; pos++) {
            if (map.containsKey(rem)) {
                right.insert(map.get(rem), "(");
                right.append(')');
                break;
            }
            map.put(rem, pos);
            right.append(Long.toString((rem * 10) / den));
            rem = (rem * 10) % den;
        }
        return left + right;
    }
}
