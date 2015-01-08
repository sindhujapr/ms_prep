package geeksforgeeks.bit;

public class MinMax {
    public static void main(String[] args) {
        System.out.println(min(3, 10));
        System.out.println(max(3, 10));
        
        System.out.println(min(-3, -5));
        System.out.println(max(-3, -5));
    }
    
    public static int min(int x, int y) {
        return y + ((x-y) & ((x-y) >> 31));
    }
    
    public static int max(int x, int y) {
        return (x+y) - min(x, y);
    }
}