package interview.java;

public class InTheLoop {
    public static final Integer END = Integer.MAX_VALUE;
    public static final Integer START = END - 2;
    
    public static void main(String[] args) {
        byte b = -128;
        b >>>= 2;
        System.out.println(b);
//      double d = 0.0 / 0.0;
//      while(d != d)
//          System.out.println("sssd");

        for (long i = START; i <= END; i++) {
            System.out.println(i);
        }       
    }
}