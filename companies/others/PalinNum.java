package test;

/**
 * Created by qingcunz on 9/20/14.
 */
public class PalinNum {
    public static void main(String[] args) {
        boolean ret = new PalinNum().isPalindrome(1001);
        System.out.println(ret);
    }

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;

        int loop = bitCount(x);
        int cnt = loop;
        for(int i = 0; i < loop/2; i++) {
            int bit1 = x % 10;
            int bit2 = (x / pow(1, cnt-1)) % 10;
            if(bit1 != bit2)
                return false;

            cnt--;
            x /= 10;
        }

        return true;
    }

    private int pow(int x, int cnt) {
        while(cnt-- > 0)
            x *=10;
        return x;
    }

    private int bitCount(int x) {
        int res = 0;
        while(x != 0) {
            x /= 10;
            res++;
        }
        return res;
    }
}
