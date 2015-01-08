package interview.java;

public class Maximum {
    public static int max(int a, int b) {
        int c = a - b;
        int k = (c >> 31) & 0x1;
        int max = a - k * c;
        return max;
    }
    
    public static void main(String[] args) {
        System.out.println(max(5, 10));
        
        String s = " Hello ";
        s += " World ";
        s.trim( );
        System.out.println(s);
    }
}