package geeksforgeeks.bit;

public class Multiply3Point5 {
    public static void main(String[] args) {
        System.out.println(multi(2) + "\t" + multi2(2));
        System.out.println(multi(5) + "\t" + multi2(5));
    }
    
    public static int multi(int num) {
        return ((num << 3) - num) >> 1;
    }
    
    public static int multi2(int num) {
        return (num << 1) + num + (num >> 1);
    }
}
