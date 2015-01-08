package interview.daiziguizhong;

/*
 * http://chuansongme.com/n/139720
 */
public class ReverseBinary {
    public int reverse(int v) {
        int res = 0;
        for(int i = 0; i < 32; i++) {
            if((v & (1 << i)) != 0)
                res |= 1 << (31-i);
        }
        
        return res;
    }
    
    public static String toString(int v) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 32; i++) {
            if((v & (1 << i)) != 0)
                builder.insert(0, '1');
            else
                builder.insert(0, '0');
        }
        
        return builder.toString();
    }
    
    public static void main(String[] args) {
        int v = 20;
        System.out.println(toString(v));
        System.out.println(toString(new ReverseBinary().reverse(v)));
    }
}