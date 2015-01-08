package interview.java;

public class OverflowTest {
    private OverflowTest internalInstance = new OverflowTest();
    
    public OverflowTest() throws Exception {
        throw new Exception("I'm not coming out");
    }
    
    public static void main(String[] args) {
        try {
            OverflowTest b = new OverflowTest();
            System.out.println("surprise");
        } catch (Exception e) {
            System.out.println("I told you so");
        }
    }
}