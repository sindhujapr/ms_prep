package interview.java;

public class IsPalindrome {
    public static void main(String[] args) {
        int a = 100;
        
        int b = a >> 16;
        System.out.println(Integer.toHexString(b));
        
        int c = (a << 16) >> 16;
        while(b > 0 && c > 0) {
            System.out.println("unmatched");
        }
        
        System.out.println("matched");
    }
}