package test;

import java.util.Arrays;

/**
 * Created by qingcunz on 9/27/14.
 */
public class AtoI {
    public static void main(String[] args) {
        int val = new AtoI().atoi("-1");
        System.out.println(val);
    }

    public int atoi(String str) {
        if(str == null || str.length() == 0)
            return 0;

        boolean sign = true;
        if(str.charAt(0) == '-')
            sign = false;
        if(str.charAt(0) == '+' || str.charAt(0) == '-')
            str = str.substring(1);

        long res = 0;
        for(int i = 0; i < str.length(); i++) {
            if(!isDigit(str.charAt(i)))
                break;

            res = res * 10 + (str.charAt(i)-'0');
            if(res >= Math.abs(Integer.MIN_VALUE))
                break;
        }

        if(sign && res >= Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if(!sign && res >= Math.abs((long) Integer.MIN_VALUE))
            return Integer.MIN_VALUE;
        else
            return sign ? (int) res : (int) -res;
    }

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
