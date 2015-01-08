package interview.java;

public class WhichConstructor {
    private WhichConstructor(Object o) {
        System.out.println("object");
    }
    
    private WhichConstructor(double[] arry) {
        System.out.println("array");
    }
    
    public static void main(String[] args) {
        new WhichConstructor(null);
    }
}