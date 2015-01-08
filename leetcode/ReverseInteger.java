package lc2;

public class ReverseInteger {
    public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        long res = 0;
        boolean sign = x >= 0 ? true : false;
        int y = Math.abs(x);
        while(y > 0) {
            res = res * 10 + y % 10;
            y /= 10;
        }
        
        res = res * (sign ? 1 : -1);
        if(res > Integer.MAX_VALUE)
            res = Integer.MAX_VALUE;
        if(res < Integer.MIN_VALUE)
            res = Integer.MIN_VALUE;
        return (int) res;
    }

    public int reverse(int x) {
        boolean sign = x > 0 ? true : false;
        
        x = Math.abs(x);
        long res = 0;
        while(x > 0) {
            int bit = x % 10;
            x /= 10;
            
            res = res * 10 + bit;
            
            if(sign && res >= Integer.MAX_VALUE)
                return 0;
            
            if(!sign && -res <= Integer.MIN_VALUE)
                return 0;
        }
        
        return sign ? (int) res : (int) -res;
    }
}
